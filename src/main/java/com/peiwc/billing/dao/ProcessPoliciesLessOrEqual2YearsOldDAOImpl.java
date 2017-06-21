package com.peiwc.billing.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.process.billing1.SrcFileMapperForTwoYearsPolicies;

@Repository("processPoliciesLessOrEqual2YearsOldDAOImpl")
public class ProcessPoliciesLessOrEqual2YearsOldDAOImpl implements ProcessPoliciesLessOrEqual2YearsOldDAO{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String FIND_ALL = ""
			+ "SELECT p.SUBMISSION_NUMBER, p.POLICY_NUMBER "
			+ "FROM POLICY_MASTER p, SPR_FINANCIAL_FILE s "
			+ "WHERE p.SUBMISSION_NUMBER = s.SUBMISSION_NUMBER AND s.QUOTE_NUMBER = p.WINNING_QUOTE_NUMBER "
			+ "AND AGYDIRECT_BILL_CODE =:agyDirectBillCode AND EFFECTIVE_DATE BETWEEN :twoYearsBefore AND :today";
	
	private static final String FIND_ONE_IN_WF_MAM_SRC_FILE = ""
			+ "SELECT * "
			+ "FROM WF_MAM_SRC_FILE"
			+ "WHERE CYCLE_NUMBER =:cycleNumber AND SECONDARY_AUTH =:secondaryAuth";
	
	private static final String SAVE_RECORD = ""
			+ "INSERT INTO WF_MAM_SRC_FILE (CYCLE_NUMBER, SECONDARY_AUTH)"
			+ "VALUES(:cycleNumber, :secondaryAuth";


	@Override
	public List<WFMamSrcFile> findAll(String twoYearsBefore, String today) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("twoYearsBefore", twoYearsBefore);
		parameters.addValue("today", today);
		parameters.addValue("agyDirectBillCode", 'D');
		return this.namedParameterJdbcTemplate.query(FIND_ALL, parameters, new SrcFileMapperForTwoYearsPolicies());
	}
	
	@Override
	public List<WFMamSrcFile> findOneInWFSrcFile(int cycleNumber, String secondaryAuth) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("secondaryAuth", secondaryAuth);
		return this.namedParameterJdbcTemplate.queryForList(FIND_ONE_IN_WF_MAM_SRC_FILE, parameters, WFMamSrcFile.class);
	}

	@Override
	public void insert(WFMamSrcFile wfMamSrcFile) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", wfMamSrcFile.getId().getCycleNumber());
		parameters.addValue("secondaryAuth", wfMamSrcFile.getSecondaryAuth());
		this.namedParameterJdbcTemplate.update(SAVE_RECORD, parameters);
		
	}

}
