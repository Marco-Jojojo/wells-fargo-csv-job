package com.peiwc.billing.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.process.billing1.SrcFileMapper;

@Repository("calcUnclearedBilledAmtDAOImpl")
public class CalcUnclearedBilledAmtDAOImpl implements CalcUnclearedBilledAmtDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String FIND_ALL = " SELECT c.POLICY_NUMBER as REFERENCE_NUMBER , "
			+ "c.SUBMISSION_NUMBER as SECONDARY_AUTH , c.NET_PREMIUM_AMOUNT as AMOUNT_DUE , "
			+ " c.DIRECT_BILL_INVOICE as INVOICE_NUMBER , c.SEQUENCENUMBER as SEQUENCE_NUMBER"
			+ " FROM COLLECTION_MASTER c, SP_BILL_STMT_CTRL s"
			+ " WHERE CLEARED_RECEIVABLE=:clearedReceivable AND AGENCYDIRECT_BILL=:agencyDirectBill AND DIRECT_BILL_INVOICE>=:directBillInvoice "
			+ " AND c.POLICY_PREFIX_1=s.POLICY_PREFIX_1 AND c.POLICY_PREFIX_2=s.POLICY_PREFIX_2  "
			+ " AND c.POLICY_NUMBER=s.POLICY_NUMBER AND c.POLICY_SUFFIX=s.POLICY_SUFFIX "
			+ " AND c.DIRECT_BILL_INVOICE=s.INVOICE_NUMBER";

	private static final String IS_RECORD_IN_SRC_FILE = "" + "SELECT * " + "FROM WF_MAM_SRC_FILE "
			+ " WHERE CYCLE_NUMBER=:cycleNumber AND SECONDARY_AUTH = :submissionNumber AND INVOICE_NUMBER = :invoiceNumber";

	private static final String UPDATE_WF_MAM_SRC_FILE = "" + "UPADATE WF_MAM_SRC_FILE " + "SET AMOUNT_DUE=:amountDue "
			+ " WHERE CYCLE_NUMBER=:cycleNumber AND SECONDARY_AUTH = :submissionNumber AND INVOICE_NUMBER=:invoiceNumber";

	private static final String GET_INVOICE_DATE = ""
			+ " SELECT STATEMENT_DATE FROM BILLING_STATEMENT_CO where BILLING_INVOICE_NUMB=:invoiceNumber";

	private static final String SAVE_RECORD = "INSERT INTO WF_MAM_SRC_FILE(CYCLE_NUMBER,SEQUENCE_NUMBER,"
			+ "REFERENCE_NUMBER,SECONDARY_AUTH,AMOUNT_DUE,INVOICE_NUMBER,INVOICE_DATE,CONSOLIDATED_NAME)VALUES"
			+ "(:cycleNumber,:sequenceNumber,:referenceNumber,:secondaryAuth,:amountDue,:invoiceNumber,:invoiceDate,'')";

	@Override
	public List<WFMamSrcFile> findAll() {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("clearedReceivable", 'N');
		parameters.addValue("agencyDirectBill", 'D');
		parameters.addValue("directBillInvoice", 1);
		return this.namedParameterJdbcTemplate.query(CalcUnclearedBilledAmtDAOImpl.FIND_ALL, parameters,
				new SrcFileMapper());
	}

	@Override

	public List<WFMamSrcFile> isRecordInSrcFile(final int cycleNumber, final String submissionNumber,
			final String invoiceNumber) {

		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("submissionNumber", submissionNumber);
		parameters.addValue("invoiceNumber", invoiceNumber);

		final List<WFMamSrcFile> rows = this.namedParameterJdbcTemplate
				.query(CalcUnclearedBilledAmtDAOImpl.IS_RECORD_IN_SRC_FILE, parameters, new SrcFileMapper());
		return rows;
	}

	@Override
	public void update(final int cycleNumber, final String submissionNumber, final String invoiceNumber,
			final double amountDue) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("submissionNumber", submissionNumber);
		parameters.addValue("invoiceNumber", invoiceNumber);
		parameters.addValue("amountDue", amountDue);

		this.namedParameterJdbcTemplate.update(CalcUnclearedBilledAmtDAOImpl.UPDATE_WF_MAM_SRC_FILE, parameters);

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

		this.namedParameterJdbcTemplate.update(CalcUnclearedBilledAmtDAOImpl.SAVE_RECORD, parameters);
	}

	@Override
	public Date getInvoiceDate(final String invoiceNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("invoiceNumber", invoiceNumber);
		return this.namedParameterJdbcTemplate.queryForObject(CalcUnclearedBilledAmtDAOImpl.GET_INVOICE_DATE,
				parameters, Date.class);

	}

}
