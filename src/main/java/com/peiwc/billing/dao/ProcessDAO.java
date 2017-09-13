package com.peiwc.billing.dao;

import java.util.Date;

/**
 * ProcessDAO interface for defining methods for retrieving database
 * information.
 *
 */
public interface ProcessDAO {

	/**
	 * checks if the process has been run in certain date or not.
	 *
	 * @param creationDate
	 *            this parameter is used to check if the process has been run in
	 *            this specific date.
	 * @return indicates if the process has been run or not in the specific
	 *         date.
	 */
	boolean checkProcessDate(Date creationDate);

	/**
	 * gets last cycle number.
	 *
	 * @return cycle number in conjunction with the current date.
	 */
	int getLastCycleNumber();

        /**
         * Gets the max sequence number given a Cycle Number
        * @param cycleNumber
        * @return sequence number
        */
       int getMaxSequenceNumber(int cycleNumber);
}
