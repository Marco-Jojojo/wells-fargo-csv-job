package com.peiwc.billing.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.dao.mappers.SrcFileMapper;
import com.peiwc.billing.dao.mappers.SrcFileMapperForTwoYearsPolicies;
import com.peiwc.billing.domain.WFMamSrcFile;

@Repository("processPoliciesLessOrEqual2YearsOldDAOImpl")
public class ProcessPoliciesLessOrEqual2YearsOldDAOImpl implements ProcessPoliciesLessOrEqual2YearsOldDAO {

	private static final Logger LOGGER = Logger.getLogger(ProcessPoliciesLessOrEqual2YearsOldDAO.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String FIND_ALL = " SELECT p.SUBMISSION_NUMBER as SECONDARY_AUTH, p.POLICY_NUMBER as REFERENCE_NUMBER "
			+ " FROM POLICY_MASTER p, SPR_FINANCIAL_FILE s "
			+ " WHERE p.SUBMISSION_NUMBER = s.SUBMISSION_NUMBER AND p.WINNING_QUOTE_NUMBER = s.QUOTE_NUMBER "
			+ " AND s.AGYDIRECT_BILL_CODE ='D' AND EFFECTIVE_DATE >= :twoYearsBefore ";

	private static final String FIND_ONE_IN_WF_MAM_SRC_FILE = "SELECT * FROM WF_MAM_SRC_FILE "
			+ " WHERE CYCLE_NUMBER =:cycleNumber AND SECONDARY_AUTH =:secondaryAuth";

	private static final String SAVE_RECORD = "INSERT INTO WF_MAM_SRC_FILE (CYCLE_NUMBER, SEQUENCE_NUMBER, SECONDARY_AUTH, REFERENCE_NUMBER, CONSOLIDATED_NAME, AMOUNT_DUE, INVOICE_NUMBER, INVOICE_DATE)"
			+ " VALUES(:cycleNumber, :sequenceNumber, :secondaryAuth, :referenceNumber, '',0.00,'0', :invoiceDate)";

	private static final String GET_MAX_SEQUENCE_NUMBER = "SELECT MAX(SEQUENCE_NUMBER) FROM WF_MAM_SRC_FILE WHERE CYCLE_NUMBER = :cycleNumber";

	@Override
	public List<WFMamSrcFile> findAll(final String twoYearsBefore) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("twoYearsBefore", twoYearsBefore);
		parameters.addValue("agyDirectBillCode", 'D');
		return this.namedParameterJdbcTemplate.query(ProcessPoliciesLessOrEqual2YearsOldDAOImpl.FIND_ALL, parameters,
				new SrcFileMapperForTwoYearsPolicies());
	}

	@Override
	public List<WFMamSrcFile> findOneInWFSrcFile(final int cycleNumber, final String secondaryAuth) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("secondaryAuth", secondaryAuth);
		return this.namedParameterJdbcTemplate.query(
				ProcessPoliciesLessOrEqual2YearsOldDAOImpl.FIND_ONE_IN_WF_MAM_SRC_FILE, parameters,
				new SrcFileMapper());
	}

	@Override
	public void create(final WFMamSrcFile wfMamSrcFile) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", wfMamSrcFile.getId().getCycleNumber());
		parameters.addValue("sequenceNumber", wfMamSrcFile.getId().getSequenceNumber());
		parameters.addValue("secondaryAuth", wfMamSrcFile.getSecondaryAuth());
		parameters.addValue("referenceNumber", wfMamSrcFile.getReferenceNumber());
		parameters.addValue("invoiceDate", wfMamSrcFile.getInvoiceDate());
		final int rowsAffected = this.namedParameterJdbcTemplate
				.update(ProcessPoliciesLessOrEqual2YearsOldDAOImpl.SAVE_RECORD, parameters);
		ProcessPoliciesLessOrEqual2YearsOldDAOImpl.LOGGER
				.info("NUMBER OF ROWS AFFECTED IN THIS INSERT: " + rowsAffected);
	}

	@Override
	public int getMaxSequenceNumber(final int cycleNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		return this.namedParameterJdbcTemplate.queryForObject(
				ProcessPoliciesLessOrEqual2YearsOldDAOImpl.GET_MAX_SEQUENCE_NUMBER, parameters, Integer.class);
	}

}
