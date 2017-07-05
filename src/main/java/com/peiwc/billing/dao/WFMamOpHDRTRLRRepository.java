package com.peiwc.billing.dao;

import com.peiwc.billing.domain.WFMamOpHDRTRLR;

/**
 * repository for {@link WFMamOpHDRTRLR}
 */
public interface WFMamOpHDRTRLRRepository {
	/**
	 * inserts WFMamOpHDRTRLR record for master cycle number in current run.
	 *
	 * @param wfMamOpHDRTRLR
	 * @return WFMamOpHDRTRLR written to database.
	 */
	WFMamOpHDRTRLR insert(WFMamOpHDRTRLR wfMamOpHDRTRLR);

	/**
	 * updates WFMamOpHDRTRLR record for master cycle number in current run.
	 *
	 * @param wfMamOpHDRTRLR
	 * @return WFMamOpHDRTRLR written to database.
	 */
	WFMamOpHDRTRLR update(WFMamOpHDRTRLR wfMamOpHDRTRLR);

	/**
	 * find WFMamOpHDRTRLR with nextcycle key.
	 *
	 * @param nextCycle
	 * @return WFMamOpHDRTRLR record with the specified key
	 */
	WFMamOpHDRTRLR findOne(int nextCycle);
}
