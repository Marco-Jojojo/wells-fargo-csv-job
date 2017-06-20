package com.peiwc.billing.process;

import com.peiwc.billing.dao.ProcessDAO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * checks if the process has been run for today and gets the next cycle number.
 */
@Component
public class ProcessManagerCheck {

	@Autowired
	private ProcessDAO processDAO;

	/**
	 * @param currentDate
	 *            today's date.
	 * @return an indicator that checks if the process has already been run for
	 *         today.
	 */
	public boolean checkIfProcessHasAlreadyRun(final Date currentDate) {
		return this.processDAO.checkProcessDate(currentDate);
	}

	/**
	 * @return the new cycle number that must run in this process.
	 */
	public int getNextCycleNumber() {
		return this.processDAO.getLastCycleNumber() + 1;
	}

	/**
	 * gets last cycle number.
	 *
	 * @return last cycle number.
	 */
	public int getLastCycleNumber() {
		return this.processDAO.getLastCycleNumber();
	}

	public String getName(final String submissionNumber) {

		String firstName = processDAO.getFirstName(submissionNumber);
		String lastName = processDAO.getLastName(submissionNumber);

		String result = StringUtils.join(firstName, " ", lastName);

		return result;
	}

	public String getPhone(final String submissionNumber) {

		String area = processDAO.getPhoneAreacode(submissionNumber);
		String prefix = processDAO.getPhonePrefix(submissionNumber);
		String suffix = processDAO.getPhoneSuffix(submissionNumber);

		String result = StringUtils.join(area, " ", prefix, suffix);

		return result;
	}

	public String getEmail(final String submissionNumber) {
		return this.processDAO.getEmail(submissionNumber);
	}

	public String getAddress(final String submissionNumber) {

		String addr1 = processDAO.getAddr1(submissionNumber);
		String addr2 = processDAO.getAddr2(submissionNumber);

		String result = StringUtils.join(addr1, " ", addr2);

		return result;
	}

	public String getCity(final String submissionNumber) {
		return this.processDAO.getCity(submissionNumber);
	}

	public String getState(final String submissionNumber) {
		return this.processDAO.getState(submissionNumber);
	}

	public String getZip(final String submissionNumber) {
		return this.processDAO.getZip(submissionNumber);
	}

}