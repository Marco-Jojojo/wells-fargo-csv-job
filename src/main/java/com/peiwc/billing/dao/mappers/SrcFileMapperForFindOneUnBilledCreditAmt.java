package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

/**
*
*
*/
public class SrcFileMapperForFindOneUnBilledCreditAmt implements RowMapper<WFMamSrcFile> {
	@Override
	public WFMamSrcFile mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
		final WFMamSrcFile wfMamSrcFile = new WFMamSrcFile();
		final WFMamSrcFilePK id = new WFMamSrcFilePK();
		try {
			id.setCycleNumber(rs.getInt("CYCLE_NUMBER"));
		} catch (final Exception ignore) {
		}
		id.setSequenceNumber(rs.getInt("SEQUENCE_NUMBER"));
		wfMamSrcFile.setId(id);
		wfMamSrcFile.setSubmissionNumber(rs.getInt("SUBMISSION_NUMBER"));
		wfMamSrcFile.setReferenceNumber(rs.getString("REFERENCE_NUMBER"));
		wfMamSrcFile.setSecondaryAuth("");
		wfMamSrcFile.setInvoiceNumber(rs.getString("INVOICE_NUMBER"));
		wfMamSrcFile.setAmountDue(rs.getFloat("AMOUNT_DUE"));
		return wfMamSrcFile;
	}
}
