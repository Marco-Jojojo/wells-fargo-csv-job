package com.peiwc.billing.dao;

import java.util.Date;

import com.peiwc.billing.domain.WFMamOpHDRTRLR;

/**
 * dao interface for methods regarding {@link WFMamOpHDRTRLR}
 */
public interface WFMamOpHDRTRLRDAO {

	/**
	 * generates a {@link WFMamOpHDRTRLR} with the current date and all fields
	 * in blank.
	 *
	 * @param currentDate
	 *            today's date
	 * @param cycleNumber
	 *            current cycleNumber
	 * @return a WFMamOpHDRTRLR generated object.
	 */
	WFMamOpHDRTRLR saveCycleForToday(Date currentDate, int cycleNumber);

}
