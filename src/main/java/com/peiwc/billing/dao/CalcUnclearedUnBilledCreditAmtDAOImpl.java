package com.peiwc.billing.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.dao.mappers.SrcFileMapper;
import com.peiwc.billing.domain.WFMamSrcFile;

@Repository("calcUnclearedUnBilledCreditAmtDAOImpl")
public class CalcUnclearedUnBilledCreditAmtDAOImpl implements CalcUnclearedUnBilledCreditAmtDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String FIND_ALL = " SELECT POLICY_NUMBER as REFERENCE_NUMBER , "
			+ " SUBMISSION_NUMBER as SECONDARY_AUTH , NET_PREMIUM_AMOUNT as AMOUNT_DUE , "
			+ " DIRECT_BILL_INVOICE as INVOICE_NUMBER , SEQUENCENUMBER as SEQUENCE_NUMBER FROM COLLECTION_MASTER "
			+ " WHERE CLEARED_RECEIVABLE = 'N' AND AGENCYDIRECT_BILL = 'D' AND DIRECT_BILL_INVOICE = 0 AND TRANSFER_FLAG <> 'T' "
			+ " AND (TRANSACTION_CODE = 200 OR TRANSACTION_CODE = 202 OR TRANSACTION_CODE = 205 OR TRANSACTION_CODE = 226 "
			+ " OR TRANSACTION_CODE = 217 OR TRANSACTION_CODE = 220)";

	private static final String FIND_BY_DBI1 = ""
			+ " SELECT CYCLE_NUMBER, SECONDARY_AUTH, INVOICE_NUMBER, SEQUENCE_NUMBER, REFERENCE_NUMBER, AMOUNT_DUE, DUE_DATE FROM WF_MAM_SRC_FILE "
			+ " WHERE CYCLE_NUMBER = :cycleNumber AND SECONDARY_AUTH = :secondaryAuth AND INVOICE_NUMBER >= 1 AND AMOUNT_DUE <> 0";

	private static final String UPDATE_DBI1 = "" + "UPDATE WF_MAM_SRC_FILE " + "SET AMOUNT_DUE =:amtDue "
			+ " WHERE CYCLE_NUMBER = :cycleNumber AND SECONDARY_AUTH = :secondaryAuth AND INVOICE_NUMBER >= 1 AND AMOUNT_DUE <> 0";

	private static final String FIND_BY_DBI0 = ""
			+ " SELECT CYCLE_NUMBER, SECONDARY_AUTH, INVOICE_NUMBER, SEQUENCE_NUMBER, REFERENCE_NUMBER, AMOUNT_DUE, DUE_DATE "
			+ "FROM WF_MAM_SRC_FILE "
			+ " WHERE CYCLE_NUMBER = :cycleNumber AND SECONDARY_AUTH = :secondaryAuth AND INVOICE_NUMBER >= :invoiceNumber";

	private static final String UPDATE_DBI0 = "" + "UPDATE WF_MAM_SRC_FILE " + "SET AMOUNT_DUE = :amtDue "
			+ " WHERE CYCLE_NUMBER = :cycleNumber AND SECONDARY_AUTH = :secondaryAuth AND INVOICE_NUMBER >= :invoiceNumber";

	private static final String SAVE_RECORD = ""
			+ " INSERT INTO WF_MAM_SRC_FILE (CYCLE_NUMBER, SEQUENCE_NUMBER, REFERENCE_NUMBER, SECONDARY_AUTH "
			+ " ,AMOUNT_DUE, INVOICE_NUMBER,INVOICE_DATE, CONSOLIDATED_NAME) "
			+ " VALUES(:cycleNumber, :sequenceNumber, :referenceNumber, :secondaryAuth, :amountDue, :invoiceNumber, :invoiceDate, '')";

	private static final String GET_INVOICE_DATE = ""
			+ " SELECT STATEMENT_DATE FROM BILLING_STATEMENT_CO where BILLING_INVOICE_NUMB = :invoiceNumber";

	private static final String GET_MAX_SEQUENCE_NUMBER = "SELECT MAX(SEQUENCE_NUMBER) FROM WF_MAM_SRC_FILE WHERE CYCLE_NUMBER = :cycleNumber";

	@Override
	public List<WFMamSrcFile> findAll() {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("clearedReceivable", 'N');
		parameters.addValue("agencyDirectBill", 'D');
		parameters.addValue("transferFlag", 'T');
		return this.namedParameterJdbcTemplate.query(CalcUnclearedUnBilledCreditAmtDAOImpl.FIND_ALL, parameters,
				new SrcFileMapper());
	}

	@Override
	public List<WFMamSrcFile> findOneByDBI1(final int cycleNumber, final String secondaryAuth) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("secondaryAuth", secondaryAuth);
		return this.namedParameterJdbcTemplate.query(CalcUnclearedUnBilledCreditAmtDAOImpl.FIND_BY_DBI1, parameters,
				new SrcFileMapper());
	}

	@Override
	public List<WFMamSrcFile> findOneByDBI0(final int cycleNumber, final String secondaryAuth,
			final String invoiceNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("secondaryAuth", secondaryAuth);
		parameters.addValue("invoiceNumber", invoiceNumber);
		return this.namedParameterJdbcTemplate.query(CalcUnclearedUnBilledCreditAmtDAOImpl.FIND_BY_DBI0, parameters,
				new SrcFileMapper());
	}

	@Override
	public void updateDBI1(final int cycleNumber, final String secondaryAuth, final float amtDue) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("secondaryAuth", secondaryAuth);
		parameters.addValue("amtDue", amtDue);
		this.namedParameterJdbcTemplate.update(CalcUnclearedUnBilledCreditAmtDAOImpl.UPDATE_DBI1, parameters);

	}

	@Override
	public void updateDBI0(final int cycleNumber, final String secondaryAuth, final String invoiceNumber,
			final float amtDue) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("secondaryAuth", secondaryAuth);
		parameters.addValue("invoiceNumber", invoiceNumber);
		parameters.addValue("amtDue", amtDue);
		this.namedParameterJdbcTemplate.update(CalcUnclearedUnBilledCreditAmtDAOImpl.UPDATE_DBI0, parameters);

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

		this.namedParameterJdbcTemplate.update(CalcUnclearedUnBilledCreditAmtDAOImpl.SAVE_RECORD, parameters);
	}

	@Override
	public Date getInvoiceDate(final String invoiceNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("invoiceNumber", invoiceNumber);
		return this.namedParameterJdbcTemplate.queryForObject(CalcUnclearedUnBilledCreditAmtDAOImpl.GET_INVOICE_DATE,
				parameters, Date.class);

	}

	@Override
	public int getMaxSequenceNumber(final int cycleNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		return this.namedParameterJdbcTemplate.queryForObject(
				CalcUnclearedUnBilledCreditAmtDAOImpl.GET_MAX_SEQUENCE_NUMBER, parameters, Integer.class);
	}

}
