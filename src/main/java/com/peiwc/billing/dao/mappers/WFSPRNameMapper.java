package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFSPRName;

public class WFSPRNameMapper implements RowMapper<WFSPRName> {

	@Override
	public WFSPRName mapRow(final ResultSet rs, final int rowNum) throws SQLException {

		final WFSPRName user = new WFSPRName();
		user.setEntityName(rs.getString("ENTITY_NAME"));

		return user;
	}

}
