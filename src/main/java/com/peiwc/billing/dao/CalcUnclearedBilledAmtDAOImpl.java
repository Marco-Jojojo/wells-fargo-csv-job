package com.peiwc.billing.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.domain.WFMamSrcFile;

@Repository("calcUnclearedBilledAmtDAOImpl")
public class CalcUnclearedBilledAmtDAOImpl implements CalcUnclearedBilledAmtDAO {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String FIND_ALL = ""
			+ "SELECT c.POLICY_NUMBER, c.SUBMISSION_NUMBER, c.NET_PREMIUM_AMOUNT, c.DIRECT_BILL_INVOICE "
			+ "FROM COLLECTION_MASTER c, SP_BILL_STMT_CTRL s "
			+ "WHERE CLEARED_RECEIVABLE=:clearedReceivable AND AGENCYDIRECT_BILL=:agencyDirectBill AND DIRECT_BILL_INVOICE>=:directBillInvoice "
			+ "AND c.POLICY_PREFIX_1=s.POLICY_PREFIX_1 AND c.POLICY_PREFIX_2=s.POLICY_PREFIX_2  "
			+ "AND c.POLICY_NUMBER=s.POLICY_NUMBER AND c.POLICY_SUFFIX=s.POLICY_SUFFIX "
			+ "AND c.DIRECT_BILL_INVOICE=s.INVOICE_NUMBER";
	
	private static final String IS_RECORD_IN_SRC_FILE = ""
			+ "SELECT * "
			+ "FROM WF_MAM_SRC_FILE "
			+ "WHERE CYCLE_NUMBER=:cycleNumber AND SUBMISSION_NUMBER=:submissionNumber AND INVOICE_NUMBER=:invoiceNumber";

	@Override
	public List<WFMamSrcFile> findAll() {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("clearedReceivable", 'N');
		parameters.addValue("agencyDirectBill", 'D');
		parameters.addValue("directBillInvoice", 1);
		return this.namedParameterJdbcTemplate.query(FIND_ALL, parameters, new SrcFileMapper());
	}

	@Override
	public WFMamSrcFile isRecordInSrcFile(int cycleNumber, String submissionNumber, String invoiceNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("submissionNumber", submissionNumber);
		parameters.addValue("invoiceNumber", invoiceNumber);
		try {
			final WFMamSrcFile row = this.namedParameterJdbcTemplate.queryForObject(IS_RECORD_IN_SRC_FILE, parameters, WFMamSrcFile.class);
			return row;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	private static final class SrcFileMapper implements RowMapper<WFMamSrcFile> {

		@Override
		public WFMamSrcFile mapRow(ResultSet arg0, int arg1) throws SQLException {
			WFMamSrcFile wfMamSrcFile = new WFMamSrcFile();
			//TODO: finish mapper
			return null;
		}
		
	}

}
