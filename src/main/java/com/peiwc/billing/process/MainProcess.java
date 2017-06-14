package com.peiwc.billing.process;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.App;

/**
 * this is the main process where all the data processing runs, this is called
 * from the main method in {@link App} class
 */
@Component("mainProcess")
public class MainProcess {

	private static final Logger LOGGER = Logger.getLogger(MainProcess.class);

	@Autowired
	private ProcessManagerCheck processManagerCheck;

	/**
	 * this is the main process that checks if the process has already run and
	 * then writes the entire data from a cycle to database.
	 *
	 * @return a flag indicating the process has been run successfully
	 */
	public boolean runWellsFargoCSVProcess() {
		final boolean hasRunSuccessfully = true;
		final Date currentDate = new Date();
		if (!this.processManagerCheck.checkIfProcessHasAlreadyRun(currentDate)) {
			final int nextCycle = processManagerCheck.getNextCycleNumber();
			MainProcess.LOGGER.info("Process Has not been run, Next cycle is: " + nextCycle);
		} else {
			MainProcess.LOGGER.info("Process has already run today");
		}
		return hasRunSuccessfully;
	}

}
