package com.peiwc.billing.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.peiwc.billing.domain.WFUserInfo;

/**
*
*
*/
public class WFUserInfoMapper implements RowMapper<WFUserInfo> {
	@Override
	public WFUserInfo mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final WFUserInfo user = new WFUserInfo();
		user.setAddress(rs.getString("INS_ADDR_1"));
		user.setAddress2(rs.getString("INS_ADDR_2"));
		user.setCity(rs.getString("INS_CITY"));
		user.setState(rs.getString("INS_STATE_CODE"));
		user.setZip(rs.getString("INS_ZIP_1"));
		user.setStatus(rs.getString("STATUS_CODE"));
		return user;
	}
}
