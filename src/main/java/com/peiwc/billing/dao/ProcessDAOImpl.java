package com.peiwc.billing.dao;

import java.util.Date;

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
	
	
}
