package com.peiwc.billing.process;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.WFMamOpHDRTRLRDAO;
import com.peiwc.billing.domain.WFMamOpHDRTRLR;

/**
 * manages master table process for creating next run of wells fargo csv
 * details.
 */
@Component("wfMamOpHDRTRLRProcess")
public class WFMamOpHDRTRLRProcess {

	@Autowired
	private WFMamOpHDRTRLRDAO wfMamOpHDRTRLRDAO;

	/**
	 * saves next cycle and return generated object
	 *
	 * @param nextCycle
	 *            next cycle that is being run.
	 * @param currentDate
	 *            today's date.
	 * @return WFMamOpHDRTRLR saved instance
	 */
	public WFMamOpHDRTRLR saveNextCycle(final int nextCycle, final Date currentDate) {
		return wfMamOpHDRTRLRDAO.saveCycleForToday(currentDate, nextCycle);
	}

}
