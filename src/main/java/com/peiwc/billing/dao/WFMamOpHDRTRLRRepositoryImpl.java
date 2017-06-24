package com.peiwc.billing.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.peiwc.billing.dao.mappers.WFMamOpHDRTRLRMapper;
import com.peiwc.billing.domain.WFMamOpHDRTRLR;
import com.peiwc.billing.helpers.DateFormatUtil;

@Repository("wfMamOpHDRTRLRRepository")
public class WFMamOpHDRTRLRRepositoryImpl implements WFMamOpHDRTRLRRepository {

	private static final Logger LOGGER = Logger.getLogger(WFMamOpHDRTRLRRepositoryImpl.class);

	private static final String FIND_BY_CYCLE_NUMBER = "select * from WF_MAM_OP_HDR_TRLR where CYCLE_NUMBER = :cycleNumber";

	private static final String UPDATE_WFMAMOPHDRTRLR = "	update WF_MAM_OP_HDR_TRLR set  CREATION_DATE = :creationDate ,  "
			+ " TOTAL_RECORD_COUNT = :totalRecordCount , FILENAME = :fileName , ERROR_MSG = :errorMessage , "
			+ " STATUS = :status where CYCLE_NUMBER = :cycleNumber";

	private static final String INSERT_WFMAMOPHDRTRLR = "	insert into WF_MAM_OP_HDR_TRLR (CYCLE_NUMBER,CREATION_DATE,TOTAL_RECORD_COUNT,FILENAME,ERROR_MSG,STATUS) "
			+ "values( :cycleNumber , :creationDate , :totalRecordCount , :fileName , :errorMessage , :status )";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public WFMamOpHDRTRLR findOne(final int nextCycle) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cycleNumber", nextCycle);
		WFMamOpHDRTRLR wfMamOpHDRTRLR = null;
		final List<WFMamOpHDRTRLR> wfMamOpHDRTRLRList = this.namedParameterJdbcTemplate
				.query(WFMamOpHDRTRLRRepositoryImpl.FIND_BY_CYCLE_NUMBER, params, new WFMamOpHDRTRLRMapper());
		WFMamOpHDRTRLRRepositoryImpl.LOGGER.info("Number of results = " + wfMamOpHDRTRLRList.size());
		for (final WFMamOpHDRTRLR wfMamOpHDRTRLR2 : wfMamOpHDRTRLRList) {
			wfMamOpHDRTRLR = wfMamOpHDRTRLR2;
		}
		return wfMamOpHDRTRLR;
	}

	@Override
	public WFMamOpHDRTRLR insert(final WFMamOpHDRTRLR wfMamOpHdrTrlr) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cycleNumber", wfMamOpHdrTrlr.getCycleNumber());
		params.addValue("creationDate", wfMamOpHdrTrlr.getCreationDate());
		params.addValue("totalRecordCount", wfMamOpHdrTrlr.getTotalRecordCount());
		params.addValue("fileName", wfMamOpHdrTrlr.getFileName());
		params.addValue("errorMessage", wfMamOpHdrTrlr.getErrorMessage());
		params.addValue("status", wfMamOpHdrTrlr.getStatus());
		this.namedParameterJdbcTemplate.update(WFMamOpHDRTRLRRepositoryImpl.INSERT_WFMAMOPHDRTRLR, params);
		WFMamOpHDRTRLRRepositoryImpl.LOGGER
				.info("insert WFMamOpHDRTRL: " + WFMamOpHDRTRLRRepositoryImpl.INSERT_WFMAMOPHDRTRLR);
		return wfMamOpHdrTrlr;
	}

	@Override
	public WFMamOpHDRTRLR update(final WFMamOpHDRTRLR wfMamOpHdrTrlr) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("cycleNumber", wfMamOpHdrTrlr.getCycleNumber());
		params.addValue("creationDate", DateFormatUtil.formatDate(wfMamOpHdrTrlr.getCreationDate()));
		params.addValue("totalRecordCount", wfMamOpHdrTrlr.getTotalRecordCount());
		params.addValue("fileName", wfMamOpHdrTrlr.getFileName());
		params.addValue("errorMessage", wfMamOpHdrTrlr.getErrorMessage());
		params.addValue("status", wfMamOpHdrTrlr.getStatus());
		this.namedParameterJdbcTemplate.update(WFMamOpHDRTRLRRepositoryImpl.UPDATE_WFMAMOPHDRTRLR, params);
		return wfMamOpHdrTrlr;
	}

}
