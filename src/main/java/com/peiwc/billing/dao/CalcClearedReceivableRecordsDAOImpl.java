package com.peiwc.billing.dao;

import com.peiwc.billing.dao.mappers.SrcFileMapperForClearedReceivables;
import com.peiwc.billing.domain.WFMamSrcFile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Aug 30, 2017
 */
@Repository
public class CalcClearedReceivableRecordsDAOImpl implements CalcClearedReceivableRecordsDAO
{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    public static final String FIND_ALL_CLEARED_RECEIVABLES = 
            "SELECT wf.REFERENCE_NUMBER as REFERENCE_NUMBER " +
            " ,wf.DUE_DATE as DUE_DATE " +
            " ,wf.INVOICE_NUMBER as INVOICE_NUMBER " +
            " ,wf.INVOICE_DATE as INVOICE_DATE " +
            " ,wf.SUBMISSION_NUMBER as SUBMISSION_NUMBER " +
            "FROM WF_MAM_SRC_FILE wf INNER JOIN  " +
            "	(SELECT  " +
            "		cm.SUBMISSION_NUMBER, " +
            "		bill.DUE_DATE,  " +
            "		bill.STMT_DATE " +
            "		FROM COLLECTION_MASTER cm " +
            "		 INNER JOIN SP_BILL_STMT_CTRL bill " +
            "		 ON cm.POLICY_PREFIX_1 = bill.POLICY_PREFIX_1 " +
            "		 AND cm.POLICY_PREFIX_2 = bill.POLICY_PREFIX_2 " +
            "		 AND cm.POLICY_NUMBER = bill.POLICY_NUMBER " +
            "		 AND cm.POLICY_SUFFIX = bill.POLICY_SUFFIX " +
            "		 AND cm.DIRECT_BILL_INVOICE = bill.INVOICE_NUMBER " +
            "		 WHERE cm.CLEARED_RECEIVABLE = 'Y'  " +
            "		 AND cm.DATE_CLEARED BETWEEN DATEADD(DAY, -60, GETDATE()) AND GETDATE() " +
            "		 AND cm.DIRECT_BILL_INVOICE NOT IN (0, 99999999) " +
            "	 ) AS cm_bill " +
            "ON cm_bill.SUBMISSION_NUMBER = wf.SUBMISSION_NUMBER " +
            " AND cm_bill.DUE_DATE = wf.DUE_DATE " +
            " AND cm_bill.STMT_DATE = wf.INVOICE_DATE " +
            "WHERE wf.CLEARED_RECORD_SENT = 0 " +
            "GROUP BY wf.REFERENCE_NUMBER " +
            " ,wf.SECONDARY_AUTH " +
            " ,wf.CONSOLIDATED_NAME " +
            " ,wf.DUE_DATE " +
            " ,wf.AMOUNT_DUE " +
            " ,wf.INVOICE_NUMBER " +
            " ,wf.INVOICE_DATE " +
            " ,wf.STATUS_INVOICE " +
            " ,wf.SUBMISSION_NUMBER;";
    
    private static final String SAVE_RECORD = "INSERT INTO WF_MAM_SRC_FILE(CYCLE_NUMBER,SEQUENCE_NUMBER,"
			+ "REFERENCE_NUMBER,SECONDARY_AUTH,AMOUNT_DUE,INVOICE_NUMBER,INVOICE_DATE,CONSOLIDATED_NAME, DUE_DATE, SUBMISSION_NUMBER, CLEARED_RECORD_SENT)VALUES"
			+ "(:cycleNumber,:sequenceNumber,:referenceNumber,'',0,:invoiceNumber,:invoiceDate,'', :dueDate, :submissionNumber,1);";
    
    @Override
    public List<WFMamSrcFile> findAllClearedReceivables() {
        return this.namedParameterJdbcTemplate.query(FIND_ALL_CLEARED_RECEIVABLES, new SrcFileMapperForClearedReceivables());
    }

    @Override
    public void create(WFMamSrcFile wfMamSrcFile) {
        final MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("cycleNumber", wfMamSrcFile.getId().getCycleNumber());
        parameters.addValue("sequenceNumber", wfMamSrcFile.getId().getSequenceNumber());
        parameters.addValue("submissionNumber", wfMamSrcFile.getSubmissionNumber());
        parameters.addValue("referenceNumber", wfMamSrcFile.getReferenceNumber());
        parameters.addValue("invoiceDate", wfMamSrcFile.getInvoiceDate());
        parameters.addValue("dueDate", wfMamSrcFile.getDueDate());
        parameters.addValue("invoiceNumber", wfMamSrcFile.getInvoiceNumber());
        this.namedParameterJdbcTemplate.update(SAVE_RECORD, parameters);
    }
    
}
