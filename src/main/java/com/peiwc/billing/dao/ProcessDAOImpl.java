package com.peiwc.billing.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * implementation of {@link ProcessDAO}
 */
@Repository("processDAOImpl")
public class ProcessDAOImpl implements ProcessDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String CHECK_PROCESS_BY_DATE = "select count(0) as HAS_RUN from "
			+ "WF_MAM_OP_HDR_TRLR where cast(creation_date as date)= :creationDate ";

	private static final String GET_LAST_CYCLE_NUMBER = "select ifnull(max( cycle_number ),0) as cycle_number "
			+ "from WF_MAM_OP_HDR_TRLR";

	private static final String GET_FIRST_NAME = "select top1 FIRST_NAME from SPR_INSURED_CONTACT_ where SUBMISSION_NUMBER = submissionNumber";
	
	private static final String GET_LAST_NAME = "select top1 LAST_NAME from SPR_INSURED_CONTACT_ where SUBMISSION_NUMBER = submissionNumber";

	private static final String GET_PHONE_AREA_CODE = "select top1 PHONE_AREA_CODE from SPR_INSURED_CONTACT_ where SUBMISSION_NUMBER = submissionNumber";

	private static final String GET_PHONE_PREFIX = "select top1 PHONE_PREFIX from SPR_INSURED_CONTACT_ where SUBMISSION_NUMBER = submissionNumber";

	private static final String GET_PHONE_SUFFIX = "select top1 PHONE_SUFFIX from SPR_INSURED_CONTACT_ where SUBMISSION_NUMBER = submissionNumber";

	private static final String GET_EMAIL = "select top1 EMAIL_ADDRESS from SPR_INSURED_CONTACT_ where SUBMISSION_NUMBER = submissionNumber";

	private static final String GET_ADDR_1 = "select top1 ADDR_1 from SPR_LOCATION where SUBMISSION_NUMBER = submissionNumber";

	private static final String GET_ADDR_2 = "select top1 ADDR_2 from SPR_LOCATION where SUBMISSION_NUMBER = submissionNumber";

	private static final String GET_CITY = "select top1 CITY from SPR_LOCATION where SUBMISSION_NUMBER = submissionNumber";

	private static final String GET_STATE = "select top1 STATE from SPR_LOCATION where SUBMISSION_NUMBER = submissionNumber";

	private static final String GET_ZIP = "select top1 ZIP from SPR_LOCATION where SUBMISSION_NUMBER = submissionNumber";



	/**
	 * {@inheritDoc}
	 */
	@Override public boolean checkProcessDate(final Date creationDate) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("creationDate", creationDate);
		final int runStatus = this.namedParameterJdbcTemplate.queryForObject(CHECK_PROCESS_BY_DATE, parameters,
				Integer.class);
		return runStatus == 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLastCycleNumber() {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		final int lastCycleNumber = this.namedParameterJdbcTemplate.queryForObject(GET_LAST_CYCLE_NUMBER, parameters,
				Integer.class);
		return lastCycleNumber;
	}

	@Override
	public String getFirstName(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final String result = namedParameterJdbcTemplate.queryForObject(GET_FIRST_NAME, parameters, String.class);
		return result;
	}

	@Override
	public String getLastName(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final String result = namedParameterJdbcTemplate.queryForObject(GET_LAST_NAME, parameters, String.class);
		return result;
	}

	@Override
	public String getPhoneAreacode(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final String result = namedParameterJdbcTemplate.queryForObject(GET_PHONE_AREA_CODE, parameters, String.class);
		return result;
	}

	@Override
	public String getPhonePrefix(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final String result = namedParameterJdbcTemplate.queryForObject(GET_PHONE_PREFIX, parameters, String.class);
		return result;
	}

	@Override
	public String getPhoneSuffix(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final String result = namedParameterJdbcTemplate.queryForObject(GET_PHONE_SUFFIX, parameters, String.class);
		return result;
	}

	@Override
	public String getEmail(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final String result = namedParameterJdbcTemplate.queryForObject(GET_EMAIL, parameters, String.class);
		return result;
	}

	@Override
	public String getAddr1(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final String result = namedParameterJdbcTemplate.queryForObject(GET_ADDR_1, parameters, String.class);
		return result;
	}

	@Override
	public String getAddr2(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final String result = namedParameterJdbcTemplate.queryForObject(GET_ADDR_2, parameters, String.class);
		return result;
	}

	@Override
	public String getCity(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final String result = namedParameterJdbcTemplate.queryForObject(GET_CITY, parameters, String.class);
		return result;
	}

	@Override
	public String getState(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final String result = namedParameterJdbcTemplate.queryForObject(GET_STATE, parameters, String.class);
		return result;
	}

	@Override
	public String getZip(String submissionNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("SUBMISSION_NUMBER", submissionNumber);
		final String result = namedParameterJdbcTemplate.queryForObject(GET_ZIP, parameters, String.class);
		return result;
	}

}
