package com.peiwc.billing.process;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.WFMamOpHDRTRLRRepository;
import com.peiwc.billing.domain.WFMamOpHDRTRLR;

/**
 * manages master table process for creating next run of wells fargo csv
 * details.
 */
@Component("wfMamOpHDRTRLRProcess")
public class WFMamOpHDRTRLRProcess {

	@Autowired
	private WFMamOpHDRTRLRRepository wfMamOpHDRTRLRRepository;

	@Autowired
	private ProcessManagerCheck processManagerCheck;

	private static final Logger LOGGER = Logger.getLogger(WFMamOpHDRTRLRProcess.class);

	private static final String PROC_ALREADY_RUN = "PROCESS HAS ALREADY BEEN RUN FOR TODAY";

	@Value("${cifs.process.enabled}")
	private boolean processEnabled;

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
		WFMamOpHDRTRLRProcess.LOGGER
				.info("wfMamOpHDRTRLR before saving: " + ToStringBuilder.reflectionToString(wfMamOpHDRTRLR));
		return this.wfMamOpHDRTRLRRepository.insert(wfMamOpHDRTRLR);
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
		final WFMamOpHDRTRLR wfMamOpHDRTRLR = wfMamOpHDRTRLRRepository.findOne(nextCycle);
		wfMamOpHDRTRLR.setTotalRecordCount(totalRecordCount);
		this.wfMamOpHDRTRLRRepository.update(wfMamOpHDRTRLR);
	}

	/**
	 * saves current Error Message to master table for current cycle.
	 *
	 * @param cycleNumber
	 *            current cycle number run by the application.
	 * @param errorMessage
	 *            critical error caught during execution.
	 */
	public void saveStatusMessage(final int cycleNumber, final String errorMessage) {

		final WFMamOpHDRTRLR wfMamOpHDRTRLR = wfMamOpHDRTRLRRepository.findOne(cycleNumber);
		if (wfMamOpHDRTRLR != null && errorMessage != null) {
			String errMessage = errorMessage;
			if (errorMessage.length() > 100) {
				errMessage = errorMessage.substring(0, 99);
			}
			wfMamOpHDRTRLR.setStatusMessage(errMessage);
			wfMamOpHDRTRLRRepository.update(wfMamOpHDRTRLR);
		}

	}

	/**
	 * saves current state of project to master table.
	 *
	 * @param processState
	 *            indicates the current process of project.
	 * @param cycleNumber
	 *            current cycle number run by the application.
	 */
	public void setCurrentState(final ProcessState processState, final int cycleNumber) {
		final WFMamOpHDRTRLR wfMamOpHDRTRLR = wfMamOpHDRTRLRRepository.findOne(cycleNumber);
		WFMamOpHDRTRLRProcess.LOGGER.info("Process State is: " + processState.getName());
		wfMamOpHDRTRLR.setStatus(processState.getName());
		wfMamOpHDRTRLRRepository.update(wfMamOpHDRTRLR);
	}

	/**
	 * if process has already run for today, sets error as already run.
	 *
	 * @return cycle number.
	 */
	public int setProcessAsAlreadyRunForToday() {
		final int lastCycleNumber = processManagerCheck.getLastCycleNumber();
		this.setCurrentState(ProcessState.ALREADY_RUN, lastCycleNumber);
		this.setProcessAlreadyRun( lastCycleNumber );
		return lastCycleNumber;
	}

	private void setProcessAlreadyRun(int lastCycleNumber) {
		 WFMamOpHDRTRLR wfMamOpHrdTrlr = this.wfMamOpHDRTRLRRepository.findOne(lastCycleNumber);
		 wfMamOpHrdTrlr.setStatusMessage(PROC_ALREADY_RUN);
		 wfMamOpHDRTRLRRepository.update(wfMamOpHrdTrlr);
	}

	public void saveFileName(final int nextCycle, final String fileName) {
		final WFMamOpHDRTRLR wfMamOpHDRTRLR = wfMamOpHDRTRLRRepository.findOne(nextCycle);
		wfMamOpHDRTRLR.setFileName(fileName);
		this.wfMamOpHDRTRLRRepository.update(wfMamOpHDRTRLR);
	}

	
	
	
}
