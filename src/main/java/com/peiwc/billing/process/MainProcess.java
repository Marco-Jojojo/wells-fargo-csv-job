package com.peiwc.billing.process;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.App;
import com.peiwc.billing.domain.WFMamOpHDRTRLR;

/**
 * this is the main process where all the data processing runs, this is called
 * from the main method in {@link App} class
 */
@Component("mainProcess")
public class MainProcess {

	private static final Logger LOGGER = Logger.getLogger(MainProcess.class);

	@Autowired
	private ProcessManagerCheck processManagerCheck;

	@Autowired
	private WFMamOpHDRTRLRProcess wfMamOpHDRTRLRProcess;

	@Autowired
	private WriteWFMAMSrcFileCSV writeWFMAMSrcFileCSV;

	/**
	 * this is the main process that checks if the process has already run and
	 * then writes the entire data from a cycle to database.
	 *
	 * @return a flag indicating the process has been run successfully
	 */
	public boolean runWellsFargoCSVProcess() {
		boolean hasRunSuccessfully = true;
		final Date currentDate = new Date();
		if (!this.processManagerCheck.checkIfProcessHasAlreadyRun(currentDate)) {
			final int nextCycle = processManagerCheck.getNextCycleNumber();
			MainProcess.LOGGER.info("Process Has not been run, Next cycle is: " + nextCycle);
			final WFMamOpHDRTRLR wfMamOpHDRTRLR = wfMamOpHDRTRLRProcess.saveNextCycle(nextCycle, currentDate);
			MainProcess.LOGGER.info("wfMamOpHDRTRLR cycleNumber: " + wfMamOpHDRTRLR.getCycleNumber()
					+ " , creationDate:" + wfMamOpHDRTRLR.getCreationDate());
			// here goes the main process where the data for WF_MAM_SRC_FILE
			// table is filled.
			fillTables();
			String fileName = System.getProperty("file.name");
			if (fileName == null) {
				fileName = "test.csv";
			}
			try {
				final int totalRecordCount = writeWFMAMSrcFileCSV.writeDataToCSV(nextCycle, fileName);
				wfMamOpHDRTRLRProcess.saveTotalRecordsProcessed(nextCycle, totalRecordCount);
			} catch (final IOException e) {
				MainProcess.LOGGER.error(e, e);
				hasRunSuccessfully = false;
			}
		} else {
			MainProcess.LOGGER.info("Process has already run today");
			hasRunSuccessfully = false;
		}
		return hasRunSuccessfully;
	}

	private void fillTables() {
		// TODO generate here the process to fill the tables with data.

	}

}
