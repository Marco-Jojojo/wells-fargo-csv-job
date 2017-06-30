package com.peiwc.billing.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.dao.mappers.SrcFileMapperForTwoYearsPolicies;
import com.peiwc.billing.domain.WFMamSrcFile;

@Repository("processPoliciesLessOrEqual2YearsOldDAOImpl")
public class ProcessPoliciesLessOrEqual2YearsOldDAOImpl implements ProcessPoliciesLessOrEqual2YearsOldDAO {

	private static final Logger LOGGER = Logger.getLogger(ProcessPoliciesLessOrEqual2YearsOldDAO.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String FIND_ALL = "SELECT p.SUBMISSION_NUMBER as SUBMISSION_NUMBER , p.POLICY_NUMBER as REFERENCE_NUMBER, sum(c.NET_PREMIUM_AMOUNT) as AMOUNT_DUE, "
			+ "CONCAT(LTRIM(RTRIM(c.POLICY_PREFIX_1)), LTRIM(RTRIM(c.POLICY_PREFIX_2)), LTRIM(RTRIM(c.POLICY_NUMBER)), '-', LTRIM(RTRIM(c.POLICY_SUFFIX))) as INVOICE_NUMBER "
			+ "FROM POLICY_MASTER p, SPR_FINANCIAL_FILE s, COLLECTION_MASTER c "
			+ "WHERE p.SUBMISSION_NUMBER = s.SUBMISSION_NUMBER AND p.POLICY_PREFIX_1 = s.POLICY_PREFIX_1 AND p.POLICY_PREFIX_2 = s.POLICY_PREFIX_2 "
			+ "AND p.SUBMISSION_NUMBER = c.SUBMISSION_NUMBER AND p.POLICY_NUMBER = c.POLICY_NUMBER	AND p.POLICY_PREFIX_1 = c.POLICY_PREFIX_1	AND p.POLICY_PREFIX_2 = c.POLICY_PREFIX_2 "
			+ "AND p.POLICY_SUFFIX = c.POLICY_SUFFIX AND p.WINNING_QUOTE_NUMBER = s.QUOTE_NUMBER AND s.AGYDIRECT_BILL_CODE ='D' AND (( p.EFFECTIVE_DATE > '2017-06-29' AND "
			+ "p.EFFECTIVE_DATE = c.ENTRY_DATE) OR (p.EFFECTIVE_DATE <= '2017-06-29' AND p.EFFECTIVE_DATE >= :twoYearsBefore)) AND c.CLEARED_RECEIVABLE='N' AND c.TRANSFER_FLAG='' "
			+ "AND c.O_COMMENT not like '%OFFSET%' GROUP BY p.SUBMISSION_NUMBER, p.POLICY_NUMBER, c.POLICY_PREFIX_1, c.POLICY_PREFIX_2, c.POLICY_NUMBER, c.POLICY_SUFFIX";

	private static final String FIND_ONE_IN_WF_MAM_SRC_FILE = "SELECT * FROM WF_MAM_SRC_FILE "
			+ " WHERE CYCLE_NUMBER =:cycleNumber AND SUBMISSION_NUMBER =:submissionNumber";

	private static final String SAVE_RECORD = "INSERT INTO WF_MAM_SRC_FILE (CYCLE_NUMBER, SEQUENCE_NUMBER, SUBMISSION_NUMBER, SECONDARY_AUTH, REFERENCE_NUMBER, CONSOLIDATED_NAME, AMOUNT_DUE, INVOICE_NUMBER, INVOICE_DATE, DUE_DATE)"
			+ " VALUES(:cycleNumber, :sequenceNumber, :submissionNumber, :secondaryAuth, :referenceNumber, '',:amountDue, :invoiceNumber, :invoiceDate, :dueDate)";

	private static final String GET_MAX_SEQUENCE_NUMBER = "SELECT MAX(SEQUENCE_NUMBER) FROM WF_MAM_SRC_FILE WHERE CYCLE_NUMBER = :cycleNumber";

	@Override
	public List<WFMamSrcFile> findAll(final String twoYearsBefore, final String today) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("today", today);
		parameters.addValue("twoYearsBefore", twoYearsBefore);
		parameters.addValue("agyDirectBillCode", 'D');
		return this.namedParameterJdbcTemplate.query(ProcessPoliciesLessOrEqual2YearsOldDAOImpl.FIND_ALL, parameters,
				new SrcFileMapperForTwoYearsPolicies());
	}

	@Override
	public List<WFMamSrcFile> findOneInWFSrcFile(final int cycleNumber, final int submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("submissionNumber", submissionNumber);
		return this.namedParameterJdbcTemplate.query(
				ProcessPoliciesLessOrEqual2YearsOldDAOImpl.FIND_ONE_IN_WF_MAM_SRC_FILE, parameters,
				new SrcFileMapperForTwoYearsPolicies());
	}

	@Override
	public void create(final WFMamSrcFile wfMamSrcFile) {
		final Calendar cal = Calendar.getInstance();
		final Date todayCal = cal.getTime();
		final SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		final String dueDate = sqlFormat.format(todayCal);
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", wfMamSrcFile.getId().getCycleNumber());
		parameters.addValue("sequenceNumber", wfMamSrcFile.getId().getSequenceNumber());
		parameters.addValue("submissionNumber", wfMamSrcFile.getSubmissionNumber());
		parameters.addValue("secondaryAuth", wfMamSrcFile.getSecondaryAuth());
		parameters.addValue("referenceNumber", wfMamSrcFile.getReferenceNumber());
		parameters.addValue("invoiceDate", wfMamSrcFile.getInvoiceDate());
		parameters.addValue("dueDate", dueDate);
		parameters.addValue("invoiceNumber", wfMamSrcFile.getInvoiceNumber());
		parameters.addValue("amountDue", wfMamSrcFile.getAmountDue());
		this.namedParameterJdbcTemplate.update(ProcessPoliciesLessOrEqual2YearsOldDAOImpl.SAVE_RECORD, parameters);
	}

	@Override
	public int getMaxSequenceNumber(final int cycleNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		return this.namedParameterJdbcTemplate.queryForObject(
				ProcessPoliciesLessOrEqual2YearsOldDAOImpl.GET_MAX_SEQUENCE_NUMBER, parameters, Integer.class);
	}

}
