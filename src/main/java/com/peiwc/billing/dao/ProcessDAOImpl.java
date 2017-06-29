package com.peiwc.billing.dao;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.helpers.DateFormatUtil;

/**
 * implementation of {@link ProcessDAO}
 */
@Repository("processDAOImpl")
public class ProcessDAOImpl implements ProcessDAO {

	private static final Logger LOGGER = Logger.getLogger( ProcessDAOImpl.class );
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String CHECK_PROCESS_BY_DATE = "select count(0) as HAS_RUN from "
			+ "WF_MAM_OP_HDR_TRLR where cast(creation_date as date)= :creationDate ";

	private static final String GET_LAST_CYCLE_NUMBER = "select isnull(max( cycle_number ),0) as cycle_number "
			+ "from WF_MAM_OP_HDR_TRLR";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkProcessDate(final Date creationDate) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		final String compareDate =  DateFormatUtil.formatDate(creationDate);
		parameters.addValue("creationDate", compareDate);
		final int runTimes = this.namedParameterJdbcTemplate.queryForObject(ProcessDAOImpl.CHECK_PROCESS_BY_DATE,
				parameters, Integer.class);
		LOGGER.info("process has been run: " +  runTimes);
		boolean hasBeenRun = false;
		if(runTimes != 0){
			hasBeenRun = true;
		}
		return hasBeenRun;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLastCycleNumber() {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		final int lastCycleNumber = this.namedParameterJdbcTemplate.queryForObject(ProcessDAOImpl.GET_LAST_CYCLE_NUMBER,
				parameters, Integer.class);
		return lastCycleNumber;
	}

}
