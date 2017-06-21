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
	
	private static final String FIND_ALL = ""
			+ "SELECT c.POLICY_NUMBER, c.SUBMISSION_NUMBER, c.NET_PREMIUM_AMOUNT, c.DIRECT_BILL_INVOICE "
			+ "FROM COLLECTION_MASTER c, SP_BILL_STMT_CTRL s"
			+ "WHERE CLEARED_RECEIVABLE=:clearedReceivable AND AGENCYDIRECT_BILL=:agencyDirectBill AND DIRECT_BILL_INVOICE>=:directBillInvoice "
			+ "AND c.POLICY_PREFIX_1=s.POLICY_PREFIX_1 AND c.POLICY_PREFIX_2=s.POLICY_PREFIX_2  "
			+ "AND c.POLICY_NUMBER=s.POLICY_NUMBER AND c.POLICY_SUFFIX=s.POLICY_SUFFIX "
			+ "AND c.DIRECT_BILL_INVOICE=s.INVOICE_NUMBER";
	
	private static final String IS_RECORD_IN_SRC_FILE = ""
			+ "SELECT AMT_DUE "
			+ "FROM WF_MAM_SRC_FILE "
			+ "WHERE CYCLE_NUMBER=:cycleNumber AND SUBMISSION_NUMBER=:submissionNumber AND INVOICE_NUMBER=:invoiceNumber";
	
	private static final String UPDATE_WF_MAM_SRC_FILE = ""
			+ "UPADATE WF_MAM_SRC_FILE "
			+ "SET AMOUNT_DUE=:amountDue "
			+ "WHERE CYCLE_NUMBER=:cycleNumber AND SUBMISSION_NUMBER=:submissionNumber AND INVOICE_NUMBER=:invoiceNumber";
	
	private static final String GET_INVOICE_DATE = ""
			+ "SELECT STATEMENT_DATE FROM BILLING_STATEMENT_CO where BILLING_INVOICE_NUMB=:invoiceNumber";
	
	private static final String SAVE_RECORD = ""
			+ "INSERT INTO WF_MAM_SRC_FILE (CYCLE_NUMBER, SEQUENCE_NUMBER, REFERENCE_NUMBER, SECONDARY_AUTH, CONSOLIDATED_NAME, DUE_DATE"
			+ ",AMOUNT_DUE, INVOICE_NUMBER, INVOICE_DATE, EMAIL, ADDRESS, ADDRESS_2, CITY, STATE, ZIP, PHONE) "
			+ "VALUES(:cycleNumber, :sequenceNumber, :referenceNumber, :secondaryAuth, :consolidateName, :dueDate, :amountDue, :invoiceNumber, "
			+ ":invoiceDate, :email, :address, :address2, :city, :state, :zip, :phone)";       
           
	

	@Override
	public List<WFMamSrcFile> findAll() {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("clearedReceivable", 'N');
		parameters.addValue("agencyDirectBill", 'D');
		parameters.addValue("directBillInvoice", 1);
		return this.namedParameterJdbcTemplate.query(FIND_ALL, parameters, new SrcFileMapper());
	}

	@Override
	public List<WFMamSrcFile> isRecordInSrcFile(int cycleNumber, String submissionNumber, String invoiceNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("submissionNumber", submissionNumber);
		parameters.addValue("invoiceNumber", invoiceNumber);
		final List<WFMamSrcFile> rows = this.namedParameterJdbcTemplate.queryForList(IS_RECORD_IN_SRC_FILE, parameters, WFMamSrcFile.class);
		return rows;
		
	}
	
	@Override
	public void update(int cycleNumber, String submissionNumber, String invoiceNumber, double amountDue) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("submissionNumber", submissionNumber);
		parameters.addValue("invoiceNumber", invoiceNumber);
		parameters.addValue("amountDue", amountDue);
		
		this.namedParameterJdbcTemplate.update(UPDATE_WF_MAM_SRC_FILE, parameters);
		
	}
	
	@Override
	public void create(WFMamSrcFile wfMamSrcFile) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", wfMamSrcFile.getId().getCycleNumber());
		parameters.addValue("sequenceNumber", wfMamSrcFile.getId().getSequenceNumber());
		parameters.addValue("referenceNumber", wfMamSrcFile.getReferenceNumber());
		parameters.addValue("secondaryAuth", wfMamSrcFile.getSecondaryAuth());
		parameters.addValue("consolidatedName", wfMamSrcFile.getConsolidatedName());
		parameters.addValue("dueDate", wfMamSrcFile.getDueDate());
		parameters.addValue("amountDue", wfMamSrcFile.getAmountDue());
		parameters.addValue("invoiceNumber", wfMamSrcFile.getInvoiceNumber());
		parameters.addValue("invoiceDate", wfMamSrcFile.getInvoiceDate());
		parameters.addValue("email", wfMamSrcFile.getEmail());
		parameters.addValue("addresss", wfMamSrcFile.getAddress());
		parameters.addValue("address2", wfMamSrcFile.getAddress2());
		parameters.addValue("city", wfMamSrcFile.getCity());
		parameters.addValue("state", wfMamSrcFile.getState());
		parameters.addValue("zip", wfMamSrcFile.getZip());
		parameters.addValue("phone", wfMamSrcFile.getPhone());
		
		this.namedParameterJdbcTemplate.update(SAVE_RECORD, parameters);		
	}
	
	@Override
	public Date getInvoiceDate(String invoiceNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("invoiceNumber", invoiceNumber);
		return this.namedParameterJdbcTemplate.queryForObject(GET_INVOICE_DATE, parameters, Date.class);
		
	}
	

}
