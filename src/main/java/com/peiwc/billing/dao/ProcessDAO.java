package com.peiwc.billing.dao;

import java.util.Date;
import java.util.List;

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
	
	
	
	String getFirstName(String submissionNumber);
	String getLastName(String submissionNumber);
	String getPhoneAreacode(String submissionNumber);
	String getPhonePrefix(String submissionNumber);
	String getPhoneSuffix(String submissionNumber);
	String getEmail(String submissionNumber);
	String getAddr1(String submissionNumber);
	String getAddr2(String submissionNumber);
	String getCity(String submissionNumber);
	String getState(String submissionNumber);
	String getZip(String submissionNumber);

}
