package com.peiwc.billing.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.dao.mappers.WFDBANameMapper;
import com.peiwc.billing.dao.mappers.WFSPRNameMapper;
import com.peiwc.billing.dao.mappers.WFSPROptionalMapper;
import com.peiwc.billing.dao.mappers.WFUserInfoMapper;
import com.peiwc.billing.domain.WFDBAName;
import com.peiwc.billing.domain.WFSPRName;
import com.peiwc.billing.domain.WFSPROptional;
import com.peiwc.billing.domain.WFUserInfo;

/**
 * @author alfonso.pech Implementation of the DAO of the billing process, this
 *         will fill the user information
 *
 */
@Repository("billingDAOImpl")
public class BillingProcessDAOImpl implements BillingProcessDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 *
	 */
	public static final String GET_USER_INFORMATION = "SELECT "
			+ "ADDR_1, ADDR_2, CITY, STATE, ZIP1, STATUS_CODE FROM SPR_LOCATION as b "
			+ "JOIN POLICY_MASTER as r ON r.SUBMISSION_NUMBER = b.SUBMISSION_NUMBER "
			+ "WHERE b.SUBMISSION_NUMBER = :submissionNumber AND b.ENTITY_NUMBER = 1";

	/**
	 *
	 */
	public static final String GET_OPTIONAL_INFORMATION = "SELECT "
			+ "PHONE_AREA_CODE, PHONE_PREFIX, PHONE_SUFFIX, EMAIL_ADDRESS "
			+ "FROM SPR_INSURED_CONTACT_ WHERE SUBMISSION_NUMBER = :submissionNumber";

	/**
	 *
	 */
	public static final String GET_NAME_FROM_SPR_DBA = "SELECT DBA_NAME FROM SPR_DBA WHERE SUBMISSION_NUMBER = :submissionNumber "
			+ "AND ENTITY_NUMBER = 1 AND DBA_NUMBER = 1";

	/**
	 *
	 */
	public static final String GET_NAME_FROM_SPR_ENTITY_FILE = "SELECT ENTITY_NAME FROM SPR_ENTITY_FILE "
			+ "WHERE SUBMISSION_NUMBER = :submissionNumber";

	@Override
	public List<WFUserInfo> getUserInformation(final int submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("submissionNumber", submissionNumber);
		final List<WFUserInfo> result = namedParameterJdbcTemplate.query(BillingProcessDAOImpl.GET_USER_INFORMATION,
				parameters, new WFUserInfoMapper());

		return result;
	}

	@Override
	public List<WFDBAName> getUserDBAName(final int submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("submissionNumber", submissionNumber);
		final List<WFDBAName> result = namedParameterJdbcTemplate.query(BillingProcessDAOImpl.GET_NAME_FROM_SPR_DBA,
				parameters, new WFDBANameMapper());

		return result;
	}

	@Override
	public List<WFSPRName> getUserSPRName(final int submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("submissionNumber", submissionNumber);
		final List<WFSPRName> result = namedParameterJdbcTemplate
				.query(BillingProcessDAOImpl.GET_NAME_FROM_SPR_ENTITY_FILE, parameters, new WFSPRNameMapper());

		return result;
	}

	@Override
	public List<WFSPROptional> getUserOptional(final int submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("submissionNumber", submissionNumber);
		final List<WFSPROptional> result = namedParameterJdbcTemplate
				.query(BillingProcessDAOImpl.GET_OPTIONAL_INFORMATION, parameters, new WFSPROptionalMapper());

		return result;
	}
}
