package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFSPROptional;

/**
*
*
*/
public class WFSPROptionalMapper implements RowMapper<WFSPROptional> {
	@Override
	public WFSPROptional mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final WFSPROptional user = new WFSPROptional();
		user.setPhoneArea(rs.getString("PHONE_AREA_CODE"));
		user.setPhonePrefix(rs.getString("PHONE_PREFIX"));
		user.setPhoneSuffix(rs.getString("PHONE_SUFFIX"));
		user.setEmail(rs.getString("EMAIL_ADDRESS"));
		return user;
	}
}
