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
			+ "(select * from [COMPSUPTPROD WF].[dbo].[MAM_OP_HDR_TRLR] "
			+ "where creation_date = :creationDate) then 'yes' else 'no' end from  "
			+ "[COMPSUPTPROD WF].[dbo].[MAM_OP_HDR_TRLR];";

	@Override
	public boolean checkProcessDate(final Date creationDate) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("creationDate", creationDate);
		final String runStatus = this.namedParameterJdbcTemplate.queryForObject(CHECK_PROCESS_BY_DATE, parameters,
				String.class);
		return BooleanUtils.toBoolean(runStatus, YES, NO);
	}

}
