package com.peiwc.billing.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.dao.mappers.SrcFileMapperForFindAllUnclearedBilledAmt;
import com.peiwc.billing.domain.WFMamSrcFile;

@Repository("calcUnclearedBilledAmtDAOImpl")
public class CalcUnclearedBilledAmtDAOImpl implements CalcUnclearedBilledAmtDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String FIND_ALL = "SELECT c.POLICY_NUMBER as REFERENCE_NUMBER, c.SUBMISSION_NUMBER as SUBMISSION_NUMBER, sum(c.NET_PREMIUM_AMOUNT) as AMOUNT_DUE, "
			+ "CONCAT(LTRIM(RTRIM(c.POLICY_PREFIX_1)), LTRIM(RTRIM(c.POLICY_PREFIX_2)), LTRIM(RTRIM(c.POLICY_NUMBER)), '-', LTRIM(RTRIM(c.POLICY_SUFFIX))) as INVOICE_NUMBER, "
			+ "(select top (1) s.STMT_DATE FROM SP_BILL_STMT_CTRL s	where s.POLICY_PREFIX_1 = c.POLICY_PREFIX_1	and s.POLICY_PREFIX_2 = c.POLICY_PREFIX_2 and "
			+ "s.POLICY_NUMBER = c.POLICY_NUMBER and s.POLICY_SUFFIX = c.POLICY_SUFFIX and s.INVOICE_NUMBER < 99999999 AND s.INVOICE_NUMBER > 0 order by s.INVOICE_NUMBER desc) as INVOICE_DATE, "
			+ "(select top (1) s.DUE_DATE FROM SP_BILL_STMT_CTRL s where s.POLICY_PREFIX_1 = c.POLICY_PREFIX_1 and s.POLICY_PREFIX_2=c.POLICY_PREFIX_2 and s.POLICY_NUMBER = c.POLICY_NUMBER "
			+ "and s.POLICY_SUFFIX = c.POLICY_SUFFIX and s.INVOICE_NUMBER < 99999999 and s.INVOICE_NUMBER > 0 order by s.INVOICE_NUMBER desc) as DUE_DATE FROM COLLECTION_MASTER c "
			+ "WHERE c.CLEARED_RECEIVABLE= 'N' AND c.AGENCYDIRECT_BILL= 'D' AND c.DIRECT_BILL_INVOICE >= 1 AND c.TRANSFER_FLAG='' group by c.POLICY_NUMBER, c.POLICY_PREFIX_1, "
			+ "c.POLICY_PREFIX_2, c.POLICY_SUFFIX, c.SUBMISSION_NUMBER";

	private static final String SAVE_RECORD = "INSERT INTO WF_MAM_SRC_FILE(CYCLE_NUMBER,SEQUENCE_NUMBER,"
			+ "REFERENCE_NUMBER,SECONDARY_AUTH,AMOUNT_DUE,INVOICE_NUMBER,INVOICE_DATE,CONSOLIDATED_NAME, DUE_DATE, SUBMISSION_NUMBER)VALUES"
			+ "(:cycleNumber,:sequenceNumber,:referenceNumber,:secondaryAuth,:amountDue,:invoiceNumber,:invoiceDate,'', :dueDate, :submissionNumber)";

	@Override
	public List<WFMamSrcFile> findAll() {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		return this.namedParameterJdbcTemplate.query(CalcUnclearedBilledAmtDAOImpl.FIND_ALL, parameters,
				new SrcFileMapperForFindAllUnclearedBilledAmt());
	}

	@Override
	public void create(final WFMamSrcFile wfMamSrcFile) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", wfMamSrcFile.getId().getCycleNumber());
		parameters.addValue("sequenceNumber", wfMamSrcFile.getId().getSequenceNumber());
		parameters.addValue("referenceNumber", wfMamSrcFile.getReferenceNumber());
		parameters.addValue("secondaryAuth", wfMamSrcFile.getSecondaryAuth());
		parameters.addValue("amountDue", wfMamSrcFile.getAmountDue());
		parameters.addValue("invoiceNumber", wfMamSrcFile.getInvoiceNumber());
		parameters.addValue("invoiceDate", wfMamSrcFile.getInvoiceDate());
		parameters.addValue("dueDate", wfMamSrcFile.getDueDate());
		parameters.addValue("submissionNumber", wfMamSrcFile.getSubmissionNumber());

		this.namedParameterJdbcTemplate.update(CalcUnclearedBilledAmtDAOImpl.SAVE_RECORD, parameters);
	}

}
