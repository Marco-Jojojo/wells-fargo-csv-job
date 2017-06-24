package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFMamErrLog;

public class WFMamErrLogMapper implements RowMapper<WFMamErrLog> {

	@Override
	public WFMamErrLog mapRow(final ResultSet rs, final int rownum) throws SQLException {
		final WFMamErrLog wfMamErrLog = new WFMamErrLog();
		wfMamErrLog.setId(rs.getInt("ID_ERR_LOG"));
		wfMamErrLog.setCycleNumber(rs.getInt("CYCLE_NUMBER"));
		wfMamErrLog.setSequenceNumber(rs.getInt("SEQUENCE_NUMBER"));
		wfMamErrLog.setDescription(rs.getString("DESCRIPTION"));
		wfMamErrLog.setStatus(rs.getString("STATUS"));
		return wfMamErrLog;
	}

}
