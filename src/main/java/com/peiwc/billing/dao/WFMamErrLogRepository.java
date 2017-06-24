package com.peiwc.billing.dao;

import java.util.List;

import com.peiwc.billing.domain.WFMamErrLog;

/**
 * repository DAO for {@link WFMamErrLog}
 */
public interface WFMamErrLogRepository {

	List<WFMamErrLog> getErrorsFromCycleNumber(int cycleNumber);

	WFMamErrLog saveAndFlush(WFMamErrLog error);

}
