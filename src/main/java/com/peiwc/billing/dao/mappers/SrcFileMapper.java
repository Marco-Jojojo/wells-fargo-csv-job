package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

public class SrcFileMapper implements RowMapper<WFMamSrcFile> {

	@Override
	public WFMamSrcFile mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
		final WFMamSrcFile wfMamSrcFile = new WFMamSrcFile();
		final WFMamSrcFilePK id = new WFMamSrcFilePK();
		final ResultSetMetaData metaData = rs.getMetaData();
		for (int i = 0; i < metaData.getColumnCount(); i++) {
			final String nameColumn = metaData.getColumnName(i + 1);
			if (nameColumn.equals("CYCLE_NUMBER")) {
				id.setCycleNumber(rs.getInt("CYCLE_NUMBER"));
			}
		}
		id.setSequenceNumber(rs.getInt("SEQUENCE_NUMBER"));
		wfMamSrcFile.setId(id);
		wfMamSrcFile.setReferenceNumber(Integer.toString(rs.getInt("REFERENCE_NUMBER")));
		wfMamSrcFile.setSecondaryAuth(Integer.toString(rs.getInt("SECONDARY_AUTH")));
		wfMamSrcFile.setInvoiceNumber(Integer.toString(rs.getInt("INVOICE_NUMBER")));
		wfMamSrcFile.setAmountDue(rs.getFloat("AMOUNT_DUE"));
		return wfMamSrcFile;
	}

}
