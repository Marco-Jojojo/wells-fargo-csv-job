package com.peiwc.billing.dao;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.dao.mappers.WFMamSrcFileMapper;
import com.peiwc.billing.domain.WFMamSrcFile;

/**
 *
 *
 */
@Repository("wfMamSrcFileDAO")
public class WFMamSrcFileDAOImpl implements WFMamSrcFileDAO {
	private static final Logger LOGGER = Logger.getLogger(WFMamSrcFileDAOImpl.class);
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static final String FIND_BY_CYCLE_NUMBER = "SELECT CYCLE_NUMBER ,SEQUENCE_NUMBER ,REFERENCE_NUMBER ,"
	        + "SECONDARY_AUTH ,CONSOLIDATED_NAME ,DUE_DATE ,AMOUNT_DUE ,INVOICE_NUMBER ,INVOICE_DATE ,"
	        + "EMAIL ,ADDRESS ,ADDRESS_2 ,CITY ,STATE,ZIP ,PHONE ,SUBMISSION_NUMBER, "
	        + "STATUS_INVOICE FROM WF_MAM_SRC_FILE where cycle_number = :cycleNumber ";
	private static final String INSERT_WFMAMSRCFILE = "INSERT INTO WF_MAM_SRC_FILE ( CYCLE_NUMBER ,SEQUENCE_NUMBER ,REFERENCE_NUMBER ,SECONDARY_AUTH ,"
	        + "CONSOLIDATED_NAME ,DUE_DATE ,AMOUNT_DUE ,INVOICE_NUMBER ,INVOICE_DATE , "
	        + "EMAIL ,ADDRESS ,ADDRESS_2 ,CITY ,STATE,ZIP ,PHONE ,STATUS_INVOICE  ) values"
	        + "( :cycleNumber,:sequenceNumber,:referenceNumber,:secondaryAuth,:consolidatedName,"
	        + ":dueDate,:amountDue,:invoiceNumber,:invoiceDate,:email,:address,:address2,"
	        + ":city,:state,:zip,:phone,:statusInvoice )";
	private static final String UPDATE_WFMAMSRCFILE = "UPDATE  WF_MAM_SRC_FILE set  REFERENCE_NUMBER = :referenceNumber ,"
	        + " SECONDARY_AUTH = :secondaryAuth , CONSOLIDATED_NAME =  :consolidatedName, "
	        + " DUE_DATE = :dueDate, AMOUNT_DUE = :amountDue, INVOICE_NUMBER = :invoiceNumber, "
	        + " INVOICE_DATE = :invoiceDate ,  EMAIL = :email, ADDRESS = :address, ADDRESS_2 =  :address2,"
	        + " CITY =  :city, STATE = :state, ZIP = :zip, PHONE = :phone ,"
	        + " STATUS_INVOICE = :statusInvoice WHERE  CYCLE_NUMBER = :cycleNumber  AND  SEQUENCE_NUMBER = :sequenceNumber";

	@Override
	public List<WFMamSrcFile> findByCycleNumber(final int cycleNumber) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cycleNumber", cycleNumber);
		final List<WFMamSrcFile> mamSrcFiles = namedParameterJdbcTemplate
		        .query(WFMamSrcFileDAOImpl.FIND_BY_CYCLE_NUMBER, params, new WFMamSrcFileMapper());
		return mamSrcFiles;
	}

	@Override
	public void updateSrcFile(final WFMamSrcFile srcFile) {
		if (WFMamSrcFileDAOImpl.LOGGER.isDebugEnabled()) {
			WFMamSrcFileDAOImpl.LOGGER.debug("srcFile: " + ToStringBuilder.reflectionToString(srcFile));
		}
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cycleNumber", srcFile.getId().getCycleNumber());
		params.addValue("sequenceNumber", srcFile.getId().getSequenceNumber());
		params.addValue("referenceNumber", srcFile.getReferenceNumber());
		params.addValue("secondaryAuth", srcFile.getSecondaryAuth());
		params.addValue("consolidatedName", srcFile.getConsolidatedName());
		params.addValue("dueDate", srcFile.getDueDate());
		params.addValue("amountDue", srcFile.getAmountDue());
		params.addValue("invoiceNumber", srcFile.getInvoiceNumber());
		params.addValue("invoiceDate", srcFile.getInvoiceDate());
		params.addValue("email", srcFile.getEmail());
		params.addValue("address", srcFile.getAddress());
		params.addValue("address2", srcFile.getAddress2());
		params.addValue("city", srcFile.getCity());
		params.addValue("state", srcFile.getState());
		params.addValue("zip", srcFile.getZip());
		params.addValue("phone", srcFile.getPhone());
		params.addValue("statusInvoice", srcFile.getStatusInvoice());
		params.addValue("submissionNumber", srcFile.getSubmissionNumber());
		this.namedParameterJdbcTemplate.update(WFMamSrcFileDAOImpl.UPDATE_WFMAMSRCFILE, params);
	}

	@Override
	public void insertSrcFile(final WFMamSrcFile srcFile) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cycleNumber", srcFile.getId().getCycleNumber());
		params.addValue("sequenceNumber", srcFile.getId().getSequenceNumber());
		params.addValue("referenceNumber", srcFile.getReferenceNumber());
		params.addValue("secondaryAuth", srcFile.getSecondaryAuth());
		params.addValue("consolidatedName", srcFile.getConsolidatedName());
		params.addValue("dueDate", srcFile.getDueDate());
		params.addValue("amountDue", srcFile.getAmountDue());
		params.addValue("invoiceNumber", srcFile.getInvoiceNumber());
		params.addValue("invoiceDate", srcFile.getInvoiceDate());
		params.addValue("email", srcFile.getEmail());
		params.addValue("address", srcFile.getAddress());
		params.addValue("address2", srcFile.getAddress2());
		params.addValue("city", srcFile.getCity());
		params.addValue("state", srcFile.getState());
		params.addValue("zip", srcFile.getZip());
		params.addValue("phone", srcFile.getPhone());
		params.addValue("statusInvoice", srcFile.getSubmissionNumber());
		this.namedParameterJdbcTemplate.update(WFMamSrcFileDAOImpl.INSERT_WFMAMSRCFILE, params);
	}
}
