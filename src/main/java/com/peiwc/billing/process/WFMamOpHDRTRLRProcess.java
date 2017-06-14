package com.peiwc.billing.process;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.WFMamOpHDRTRLRDAO;
import com.peiwc.billing.dao.WFMamOpHDRTRLRRepository;
import com.peiwc.billing.domain.WFMamOpHDRTRLR;

/**
 * manages master table process for creating next run of wells fargo csv
 * details.
 */
@Component("wfMamOpHDRTRLRProcess")
public class WFMamOpHDRTRLRProcess {

	@Autowired
	private WFMamOpHDRTRLRDAO wfMamOpHDRTRLRDAO;

	@Autowired
	private WFMamOpHDRTRLRRepository wfMamOpHDRTRLRRepository;

	/**
	 * saves next cycle and return generated object
	 *
	 * @param nextCycle
	 *            next cycle that is being run.
	 * @param currentDate
	 *            today's date.
	 * @return WFMamOpHDRTRLR saved instance
	 */
	@Transactional
	public WFMamOpHDRTRLR saveNextCycle(final int nextCycle, final Date currentDate) {
		final WFMamOpHDRTRLR wfMamOpHDRTRLR = new WFMamOpHDRTRLR();
		wfMamOpHDRTRLR.setCreationDate(currentDate);
		wfMamOpHDRTRLR.setCycleNumber(nextCycle);
		return this.wfMamOpHDRTRLRRepository.saveAndFlush(wfMamOpHDRTRLR);
	}

	/**
	 * saves the total of records processed in this run.
	 *
	 * @param nextCycle
	 *            cycle that is currently run for getting the CSV records and
	 *            saving them into the file.
	 * @param totalRecordCount
	 *            records that have been processed currently.
	 */
	public void saveTotalRecordsProcessed(final int nextCycle, final int totalRecordCount) {
		wfMamOpHDRTRLRDAO.saveTotalRecordsProcessed(nextCycle, totalRecordCount);

	}

}
