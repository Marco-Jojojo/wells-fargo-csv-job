package com.peiwc.billing.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.dao.mappers.WFMamErrLogMapper;
import com.peiwc.billing.domain.WFMamErrLog;

/**
 * error log repository.
 *
 */
@Repository("wfMamErrLogRepository")
public class WFMamErrLogRepositoryImpl implements WFMamErrLogRepository {
	private static final String FIND_ERRORS_BY_CYCLE_NUMBER = "select * from WF_MAM_ERR_LOG where CYCLE_NUMBER = :cycleNumber";
	private static final String INSERT_MAM_ERROR_LOG = "insert into WF_MAM_ERR_LOG ( CYCLE_NUMBER , "
	        + "SEQUENCE_NUMBER , DESCRIPTION , STATUS) "
	        + "values (  :cycleNumber , :sequenceNumber , :description , :status   )";
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WFMamErrLog> getErrorsFromCycleNumber(final int cycleNumber) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cycleNumber", cycleNumber);
		final List<WFMamErrLog> errors = this.namedParameterJdbcTemplate
		        .query(WFMamErrLogRepositoryImpl.FIND_ERRORS_BY_CYCLE_NUMBER, params, new WFMamErrLogMapper());
		return errors;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WFMamErrLog saveAndFlush(final WFMamErrLog error) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cycleNumber", error.getCycleNumber());
		params.addValue("sequenceNumber", error.getSequenceNumber());
		params.addValue("description", error.getDescription());
		params.addValue("status", error.getStatus());
		this.namedParameterJdbcTemplate.update(WFMamErrLogRepositoryImpl.INSERT_MAM_ERROR_LOG, params);
		return error;
	}
}
