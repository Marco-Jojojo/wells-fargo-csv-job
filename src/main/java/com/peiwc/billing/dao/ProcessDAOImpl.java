package com.peiwc.billing.dao;

import java.util.Date;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("processDAOImpl")
public class ProcessDAOImpl implements ProcessDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String YES = "yes";

	private static final String NO = "no";

	private static final String CHECK_PROCESS_BY_DATE = "select top 1 case when exists "
			+ "(select * from [COMPSUPTPROD].[dbo].[WF_MAM_OP_HDR_TRLR] "
			+ "where creation_date = :creationDate) then 'yes' else 'no' end as HAS_RUN from  "
			+ "[COMPSUPTPROD].[dbo].[WF_MAM_OP_HDR_TRLR];";

	private static final String GET_LAST_CYCLE_NUMBER = "select top 1 case when exists "
			+ "(select  CYCLE_NUMBER from [COMPSUPTPROD].[dbo].[WF_MAM_OP_HDR_TRLR] ) "
			+ "then MAX(CYCLE_NUMBER) else 0 end as MAX_CYCLE_NUMBER "
			+ "from  [COMPSUPTPROD].[dbo].[WF_MAM_OP_HDR_TRLR];";

	@Override
	public boolean checkProcessDate(final Date creationDate) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("creationDate", creationDate);
		final String runStatus = this.namedParameterJdbcTemplate.queryForObject(CHECK_PROCESS_BY_DATE, parameters,
				String.class);
		return BooleanUtils.toBoolean(runStatus, YES, NO);
	}

	@Override
	public int getLastCycleNumber() {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		final int lastCycleNumber = this.namedParameterJdbcTemplate.queryForObject(GET_LAST_CYCLE_NUMBER, parameters,
				Integer.class);
		return lastCycleNumber;
	}

}
