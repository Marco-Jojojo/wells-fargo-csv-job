package com.peiwc.billing.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.dao.mappers.SrcFileMapperForClearedReceivables;
import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Aug 30, 2017
 */
@Repository
public class CalcClearedReceivableRecordsDAOImpl implements CalcClearedReceivableRecordsDAO {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	/**
	 * SQL Definition for cleared receivables.
	 */
	public static final String FIND_ALL_CLEARED_RECEIVABLES = "SELECT wf.REFERENCE_NUMBER AS REFERENCE_NUMBER\n"
	        + "	,wf.DUE_DATE AS DUE_DATE\n" + "	,wf.INVOICE_NUMBER AS INVOICE_NUMBER\n"
	        + "	,wf.INVOICE_DATE AS INVOICE_DATE\n" + "	,wf.SUBMISSION_NUMBER AS SUBMISSION_NUMBER\n"
	        + "FROM WF_MAM_SRC_FILE wf\n" + "INNER JOIN (\n" + "	SELECT cm.SUBMISSION_NUMBER\n"
	        + "		,bill.DUE_DATE\n" + "		,bill.STMT_DATE\n" + "	FROM COLLECTION_MASTER cm\n"
	        + "	INNER JOIN SP_BILL_STMT_CTRL bill ON cm.POLICY_PREFIX_1 = bill.POLICY_PREFIX_1\n"
	        + "		AND cm.POLICY_PREFIX_2 = bill.POLICY_PREFIX_2\n"
	        + "		AND cm.POLICY_NUMBER = bill.POLICY_NUMBER\n" + "		AND cm.POLICY_SUFFIX = bill.POLICY_SUFFIX\n"
	        + "		AND cm.DIRECT_BILL_INVOICE = bill.INVOICE_NUMBER\n" + "		AND cm.TRANSACTION_CODE NOT IN (\n"
	        + "			200\n" + "			,202\n" + "			,205\n" + "			,226\n" + "			,227\n"
	        + "			,220\n" + "			)\n" + "		AND RTRIM(CM.O_COMMENT) <> 'OFFSET-MATCHING'\n"
	        + "	WHERE cm.CLEARED_RECEIVABLE = 'Y'\n"
	        + "		AND cm.DATE_CLEARED BETWEEN DATEADD(DAY, - 60, GETDATE())\n" + "			AND GETDATE()\n"
	        + "		AND cm.DIRECT_BILL_INVOICE NOT IN (\n" + "			0\n" + "			,99999999\n"
	        + "			)\n" + "	) AS cm_bill ON cm_bill.SUBMISSION_NUMBER = wf.SUBMISSION_NUMBER\n"
	        + "	AND cm_bill.DUE_DATE = wf.DUE_DATE\n" + "	AND cm_bill.STMT_DATE = wf.INVOICE_DATE\n"
	        + "WHERE wf.CLEARED_RECORD_SENT = 0\n" + "GROUP BY wf.REFERENCE_NUMBER\n" + "	,wf.SECONDARY_AUTH\n"
	        + "	,wf.CONSOLIDATED_NAME\n" + "	,wf.DUE_DATE\n" + "	,wf.AMOUNT_DUE\n" + "	,wf.INVOICE_NUMBER\n"
	        + "	,wf.INVOICE_DATE\n" + "	,wf.STATUS_INVOICE\n" + "	,wf.SUBMISSION_NUMBER\n";
	private static final String SAVE_RECORD = "INSERT INTO WF_MAM_SRC_FILE(CYCLE_NUMBER,SEQUENCE_NUMBER,"
	        + "REFERENCE_NUMBER,SECONDARY_AUTH,AMOUNT_DUE,INVOICE_NUMBER,INVOICE_DATE,CONSOLIDATED_NAME, DUE_DATE, SUBMISSION_NUMBER, CLEARED_RECORD_SENT)VALUES"
	        + "(:cycleNumber,:sequenceNumber,:referenceNumber,'',0,:invoiceNumber,:invoiceDate,'', :dueDate, :submissionNumber,1);";

	@Override
	public List<WFMamSrcFile> findAllClearedReceivables() {
		return this.namedParameterJdbcTemplate.query(CalcClearedReceivableRecordsDAOImpl.FIND_ALL_CLEARED_RECEIVABLES,
		        new SrcFileMapperForClearedReceivables());
	}

	@Override
	public void create(final WFMamSrcFile wfMamSrcFile) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", wfMamSrcFile.getId().getCycleNumber());
		parameters.addValue("sequenceNumber", wfMamSrcFile.getId().getSequenceNumber());
		parameters.addValue("submissionNumber", wfMamSrcFile.getSubmissionNumber());
		parameters.addValue("referenceNumber", wfMamSrcFile.getReferenceNumber());
		parameters.addValue("invoiceDate", wfMamSrcFile.getInvoiceDate());
		parameters.addValue("dueDate", wfMamSrcFile.getDueDate());
		parameters.addValue("invoiceNumber", wfMamSrcFile.getInvoiceNumber());
		this.namedParameterJdbcTemplate.update(CalcClearedReceivableRecordsDAOImpl.SAVE_RECORD, parameters);
	}
}
