package com.peiwc.billing.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.dao.mappers.WFUserInfoMapper;
import com.peiwc.billing.domain.WFUserInfo;

@Repository("billingDAOImpl")
public class BillingProcessDAOImpl implements BillingProcessDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public static final String GET_USER_INFORMATION = "SELECT "
			+ "FIRST_NAME, LAST_NAME, PHONE_AREA_CODE, PHONE_PREFIX, PHONE_SUFFIX, EMAIL_ADDRESS, "
			+ "ADDR_1, ADDR_2, CITY, STATE, ZIP1, STATUS_CODE FROM SPR_INSURED_CONTACT_ as p JOIN SPR_LOCATION as b "
			+ "ON b.SUBMISSION_NUMBER = p.SUBMISSION_NUMBER  JOIN POLICY_MASTER as r ON r.SUBMISSION_NUMBER = p.SUBMISSION_NUMBER "
			+ "WHERE p.SUBMISSION_NUMBER = :submissionNumber";

	@Override
	public List<WFUserInfo> getUserInformation(final int submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("submissionNumber", submissionNumber);
		final List<WFUserInfo> result = namedParameterJdbcTemplate.query(BillingProcessDAOImpl.GET_USER_INFORMATION,
				parameters, new WFUserInfoMapper());

		return result;
	}
}
