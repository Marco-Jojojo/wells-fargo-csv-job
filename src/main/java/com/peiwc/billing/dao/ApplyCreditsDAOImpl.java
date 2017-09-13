package com.peiwc.billing.dao;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.dao.mappers.SrcFileMapperForApplyCredits;
import com.peiwc.billing.domain.WFMamSrcFile;

/**
 * @author jolivarria
 *
 */
@Repository("applyCreditsDAOImpl")
public class ApplyCreditsDAOImpl implements ApplyCreditsDAO {

	private static final Logger LOGGER = Logger.getLogger(ApplyCreditsDAO.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String FIND_ALL = "SELECT w.CYCLE_NUMBER, w.SECONDARY_AUTH, w.AMOUNT_DUE "
			+ "FROM WF_MAM_SRC_FILE w WHERE w.CYCLE_NUMBER =:cycleNumber AND w.SECONDARY_AUTH >= 1";

	private static final String GET_AMOUNT_DUE = "SELECT w.CYCLE_NUMBER, w.SECONDARY_AUTH, w.AMOUNT_DUE FROM WF_MAM_SRC_FILE w WHERE CYCLE_NUMBER = :cycleNumber AND SECONDARY_AUTH = :secondaryAuth";

	private static final String UPDATE_AMOUNT_DUE = "UPDATE WF_MAM_SRC_FILE SET AMOUNT_DUE =:amountDue WHERE CYCLE_NUMBER =:cycleNumber AND SECONDARY_AUTH =:secondaryAuth";

	@Override
	public List<WFMamSrcFile> findAll(final int cycleNumber) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("secondaryAuth", '1');
		return this.namedParameterJdbcTemplate.query(ApplyCreditsDAOImpl.FIND_ALL, parameters,
				new SrcFileMapperForApplyCredits());
	}

	@Override
	public List<WFMamSrcFile> getAmountsDue(final int cycleNumber, final String secondaryAuth) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", cycleNumber);
		parameters.addValue("secondaryAuth", secondaryAuth);
		return this.namedParameterJdbcTemplate.query(ApplyCreditsDAOImpl.GET_AMOUNT_DUE, parameters,
				new SrcFileMapperForApplyCredits());
	}

	@Override
	public void updateAmtDue(final WFMamSrcFile wfMamSrcFile) {
		if (ApplyCreditsDAOImpl.LOGGER.isDebugEnabled()) {
			ApplyCreditsDAOImpl.LOGGER.debug("WFMamSrcFile: " + ToStringBuilder.reflectionToString(wfMamSrcFile));
		}
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("cycleNumber", wfMamSrcFile.getId().getCycleNumber());
		parameters.addValue("secondaryAuth", wfMamSrcFile.getSecondaryAuth());
		parameters.addValue("amountDue", wfMamSrcFile.getAmountDue());
		this.namedParameterJdbcTemplate.update(ApplyCreditsDAOImpl.UPDATE_AMOUNT_DUE, parameters);

	}

}
