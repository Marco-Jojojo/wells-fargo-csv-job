package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFMamOpHDRTRLR;

/**
*
*
*/
public class WFMamOpHDRTRLRMapper implements RowMapper<WFMamOpHDRTRLR> {
	@Override
	public WFMamOpHDRTRLR mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final WFMamOpHDRTRLR wfMamOpHdrTrlr = new WFMamOpHDRTRLR();
		wfMamOpHdrTrlr.setCycleNumber(rs.getInt("CYCLE_NUMBER"));
		wfMamOpHdrTrlr.setCreationDate(rs.getDate("CREATION_DATE"));
		wfMamOpHdrTrlr.setTotalRecordCount(rs.getInt("TOTAL_RECORD_COUNT"));
		wfMamOpHdrTrlr.setFileName(rs.getString("FILENAME"));
		wfMamOpHdrTrlr.setStatusMessage(rs.getString("STATUS_MESSAGE"));
		wfMamOpHdrTrlr.setStatus(rs.getString("STATUS"));
		return wfMamOpHdrTrlr;
	}
}
