package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamErrLog;

/**
 * repository DAO for {@link WFMamErrLog}
 */
public interface WFMamErrLogRepository {
	/**
	 * get all errors in a cycle run
	 *
	 * @param cycleNumber
	 * @return {@link WFMamErrLog} list
	 */
	List<WFMamErrLog> getErrorsFromCycleNumber(int cycleNumber);

	/**
	 * save error and send it to database
	 * 
	 * @param error
	 * @return saved error
	 */
	WFMamErrLog saveAndFlush(WFMamErrLog error);
}
