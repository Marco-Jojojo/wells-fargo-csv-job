package com.peiwc.billing.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Marco Alvarado <marco.alvarado@softtek.com>
 * @date Aug 30, 2017
 */
@Repository
public class UpdateHistoricalPoliciesDAOImpl implements UpdateHistoricalPoliciesDAO {
	/**
	 * Stored procedure call for update historical bills.
	 */
	public static final String STMNT = "EXEC dbo.updateHistoricalBillsWF ";
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public void update() {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		namedParameterJdbcTemplate.execute(UpdateHistoricalPoliciesDAOImpl.STMNT, parameters,
		        new PreparedStatementCallback<Boolean>() {
			        @Override
			        public Boolean doInPreparedStatement(final PreparedStatement ps)
			                throws SQLException, DataAccessException {
				        return ps.execute();
			        }
		        });
	}
}
