package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Aug 30, 2017
 */
public class SrcFileMapperForClearedReceivables implements RowMapper<WFMamSrcFile> {
	@Override
	public WFMamSrcFile mapRow(final ResultSet rs, final int i) throws SQLException {
		final WFMamSrcFile wfMamSrcFile = new WFMamSrcFile();
		wfMamSrcFile.setReferenceNumber(Integer.toString(rs.getInt("REFERENCE_NUMBER")));
		wfMamSrcFile.setSubmissionNumber(rs.getInt("SUBMISSION_NUMBER"));
		wfMamSrcFile.setInvoiceNumber(rs.getString("INVOICE_NUMBER"));
		wfMamSrcFile.setInvoiceDate(rs.getDate("INVOICE_DATE"));
		wfMamSrcFile.setDueDate(rs.getDate("DUE_DATE"));
		return wfMamSrcFile;
	}
}
