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

	/**
	 * saves the total of records processed in this run.
	 *
	 * @param nextCycle
	 *            cycle that is currently run for getting the CSV records and
	 *            saving them into the file.
	 * @param totalRecordCount
	 *            records that have been processed currently.
	 */
	void saveTotalRecordsProcessed(int nextCycle, int totalRecordCount);

}
