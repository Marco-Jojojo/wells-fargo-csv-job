package com.peiwc.billing.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.peiwc.billing.domain.SprInsuredContact;
import com.peiwc.billing.domain.SprLocation;

@Repository("billingDAOImpl")
public class BillingProcessDAOImpl implements BillingProcessDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String GET_CONTACT_INFO = "select top1 FIRST_NAME, LAST_NAME, PHONE_AREA_CODE, PHONE_PREFIX, PHONE_SUFFIX, EMAIL_ADDRESS "
			+ "from SPR_INSURED_CONTACT_ where SUBMISSION_NUMBER = submissionNumber";

	private static final String GET_LOCATION = "select top1 ADDR_1, ADDR_2, CITY, STATE, ZIP "
			+ "from SPR_LOCATION where SUBMISSION_NUMBER = submissionNumber";

	@Override
	public SprInsuredContact getContactInfo(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final SprInsuredContact contactInfo = namedParameterJdbcTemplate.queryForObject(GET_CONTACT_INFO, parameters,
				SprInsuredContact.class);
		return contactInfo;
	}

	@Override
	public SprLocation getLocation(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final SprLocation location = namedParameterJdbcTemplate.queryForObject(GET_LOCATION, parameters,
				SprLocation.class);
		return location;
	}
}
