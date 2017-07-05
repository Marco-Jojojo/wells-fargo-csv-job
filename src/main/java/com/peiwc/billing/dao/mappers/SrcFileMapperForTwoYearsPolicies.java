package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFMamSrcFile;

/**
*
*
*/
public class SrcFileMapperForTwoYearsPolicies implements RowMapper<WFMamSrcFile> {
	@Override
	public WFMamSrcFile mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
		final WFMamSrcFile wfMamSrcFile = new WFMamSrcFile();
		wfMamSrcFile.setReferenceNumber(Integer.toString(rs.getInt("REFERENCE_NUMBER")));
		wfMamSrcFile.setSecondaryAuth("");
		wfMamSrcFile.setSubmissionNumber(rs.getInt("SUBMISSION_NUMBER"));
		wfMamSrcFile.setAmountDue(rs.getFloat("AMOUNT_DUE"));
		wfMamSrcFile.setInvoiceNumber(rs.getString("INVOICE_NUMBER"));
		wfMamSrcFile.setDueDate(rs.getDate("DUE_DATE"));
		wfMamSrcFile.setInvoiceDate(rs.getDate("INVOICE_DATE"));
		return wfMamSrcFile;
	}
}
