package com.peiwc.billing.process.billing1;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

public class SrcFileMapper implements RowMapper<WFMamSrcFile> {

	@Override
	public WFMamSrcFile mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
		final WFMamSrcFile wfMamSrcFile = new WFMamSrcFile();
		final WFMamSrcFilePK id = new WFMamSrcFilePK();
		id.setSequenceNumber(rs.getInt("SEQUENCE_NUMBER"));
		wfMamSrcFile.setId(id);
		// wfMamSrcFile.setConsolidatedName(rs.getString("CONSOLIDATED_NAME"));
		wfMamSrcFile.setReferenceNumber(Integer.toString(rs.getInt("POLICY_NUMBER")));
		wfMamSrcFile.setSecondaryAuth(Integer.toString(rs.getInt("SUBMISSION_NUMBER")));
		wfMamSrcFile.setInvoiceNumber(Integer.toString(rs.getInt("INVOICE_NUMBER")));
		// wfMamSrcFile.setDueDate(rs.getDate("DUE_DATE"));
		wfMamSrcFile.setAmountDue(rs.getFloat("AMOUNT_DUE"));
		return wfMamSrcFile;
	}

}
