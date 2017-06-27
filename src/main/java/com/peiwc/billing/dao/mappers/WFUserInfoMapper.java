package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFUserInfo;

public class WFUserInfoMapper implements RowMapper<WFUserInfo> {

	@Override
	public WFUserInfo mapRow(final ResultSet rs, final int rowNum) throws SQLException {

		final WFUserInfo user = new WFUserInfo();

		user.setPhoneArea(rs.getString("PHONE_AREA_CODE"));
		user.setPhonePrefix(rs.getString("PHONE_PREFIX"));
		user.setPhoneSuffix(rs.getString("PHONE_SUFFIX"));
		user.setEmail(rs.getString("EMAIL_ADDRESS"));
		user.setAddress(rs.getString("ADDR_1"));
		user.setAddress2(rs.getString("ADDR_2"));
		user.setCity(rs.getString("CITY"));
		user.setState(rs.getString("STATE"));
		user.setZip(rs.getString("ZIP1"));
		user.setStatus(rs.getString("STATUS_CODE"));

		return user;
	}

}
