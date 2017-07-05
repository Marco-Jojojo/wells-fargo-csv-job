package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFDBAName;

/**
*
*
*/
public class WFDBANameMapper implements RowMapper<WFDBAName> {
	@Override
	public WFDBAName mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final WFDBAName user = new WFDBAName();
		user.setDbaName(rs.getString("DBA_NAME"));
		return user;
	}
}
