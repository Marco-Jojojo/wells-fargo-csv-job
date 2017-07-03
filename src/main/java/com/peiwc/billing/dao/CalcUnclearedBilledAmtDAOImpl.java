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

	private static final String FIND_ALL = "SELECT cm.POLICY_NUMBER as REFERENCE_NUMBER, cm.SUBMISSION_NUMBER as SUBMISSION_NUMBER, CASE WHEN "
			+ "MAX(spb.AMOUNT_DUE) < SUM(cm.NET_PREMIUM_AMOUNT) THEN MAX(spb.AMOUNT_DUE) ELSE CASE WHEN SUM(cm.NET_PREMIUM_AMOUNT) < 0 THEN MAX(spb.AMOUNT_DUE) ELSE SUM(cm.NET_PREMIUM_AMOUNT) END END AS AMOUNT_DUE, "
			+ "CONCAT(LTRIM(RTRIM(cm.POLICY_PREFIX_1)), LTRIM(RTRIM(cm.POLICY_PREFIX_2)), LTRIM(RTRIM(cm.POLICY_NUMBER)), '-', LTRIM(RTRIM(cm.POLICY_SUFFIX))) as INVOICE_NUMBER, spb.STMT_DATE as INVOICE_DATE, spb.DUE_DATE as DUE_DATE "
			+ "FROM COLLECTION_MASTER cm LEFT OUTER JOIN SP_BILL_STMT_CTRL spb ON "
			+ "cm.POLICY_PREFIX_1 = spb.POLICY_PREFIX_1 AND cm.POLICY_PREFIX_2 = spb.POLICY_PREFIX_2 AND cm.POLICY_NUMBER = spb.POLICY_NUMBER AND cm.policy_suffix = spb.policy_suffix AND "
			+ "cm.DIRECT_BILL_INVOICE = spb.INVOICE_NUMBER "
			+ "WHERE cm.cleared_receivable = 'N' AND cm.O_COMMENT NOT LIKE '%OFFSET%' AND cm.DIRECT_BILL_INVOICE NOT IN (0, 99999999) GROUP BY "
			+ "cm.POLICY_NUMBER, cm.POLICY_PREFIX_1, cm.POLICY_PREFIX_2, cm.POLICY_SUFFIX, cm.SUBMISSION_NUMBER, cm.DIRECT_BILL_INVOICE, spb.DUE_DATE";

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
