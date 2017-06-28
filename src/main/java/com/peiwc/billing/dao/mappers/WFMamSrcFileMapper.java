package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

public class WFMamSrcFileMapper implements RowMapper<WFMamSrcFile> {

	@Override
	public WFMamSrcFile mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
		final WFMamSrcFile file = new WFMamSrcFile();
		final WFMamSrcFilePK id = new WFMamSrcFilePK();
		id.setCycleNumber(rs.getInt("CYCLE_NUMBER"));
		id.setSequenceNumber(rs.getInt("SEQUENCE_NUMBER"));
		file.setId(id);
		file.setReferenceNumber(rs.getString("REFERENCE_NUMBER"));
		file.setSecondaryAuth(rs.getString("SECONDARY_AUTH"));
		file.setConsolidatedName(rs.getString("CONSOLIDATED_NAME"));
		file.setDueDate(rs.getDate("DUE_DATE"));
		file.setAmountDue(rs.getFloat("AMOUNT_DUE"));
		file.setInvoiceNumber(rs.getString("INVOICE_NUMBER"));
		file.setInvoiceDate(rs.getDate("INVOICE_DATE"));
		file.setEmail(rs.getString("EMAIL"));
		file.setAddress(rs.getString("ADDRESS"));
		file.setAddress2(rs.getString("ADDRESS_2"));
		file.setCity(rs.getString("CITY"));
		file.setState(rs.getString("STATE"));
		file.setZip(rs.getString("ZIP"));
		file.setPhone(rs.getString("PHONE"));
		file.setStatusInvoice(rs.getString("STATUS_INVOICE"));
		return file;
	}

}
