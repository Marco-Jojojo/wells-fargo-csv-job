package com.peiwc.billing.dao;

import com.peiwc.billing.domain.WFMamOpHDRTRLR;

/**
 * repository for {@link WFMamOpHDRTRLR}
 */
public interface WFMamOpHDRTRLRRepository {

	WFMamOpHDRTRLR insert(WFMamOpHDRTRLR wfMamOpHDRTRLR);

	WFMamOpHDRTRLR update(WFMamOpHDRTRLR wfMamOpHDRTRLR);

	WFMamOpHDRTRLR findOne(int nextCycle);

}
