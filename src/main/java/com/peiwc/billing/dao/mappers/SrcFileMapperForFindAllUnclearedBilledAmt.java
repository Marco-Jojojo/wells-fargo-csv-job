package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

public class SrcFileMapperForFindAllUnclearedBilledAmt implements RowMapper<WFMamSrcFile> {

	@Override
	public WFMamSrcFile mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final WFMamSrcFile wfMamSrcFile = new WFMamSrcFile();
		final WFMamSrcFilePK id = new WFMamSrcFilePK();
		try {
			id.setCycleNumber(rs.getInt("CYCLE_NUMBER"));
		} catch (final Exception ignore) {
		}
		id.setSequenceNumber(rs.getInt("SEQUENCE_NUMBER"));
		wfMamSrcFile.setId(id);
		wfMamSrcFile.setReferenceNumber(Integer.toString(rs.getInt("REFERENCE_NUMBER")));
		wfMamSrcFile.setSecondaryAuth(Integer.toString(rs.getInt("SECONDARY_AUTH")));
		wfMamSrcFile.setInvoiceNumber(Integer.toString(rs.getInt("INVOICE_NUMBER")));
		wfMamSrcFile.setInvoiceDate(rs.getDate("INVOICE_DATE"));
		wfMamSrcFile.setAmountDue(rs.getFloat("AMOUNT_DUE"));
		return wfMamSrcFile;
	}

}
