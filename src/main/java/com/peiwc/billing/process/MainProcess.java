package com.peiwc.billing.process;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.peiwc.billing.App;
import com.peiwc.billing.domain.WFMamOpHDRTRLR;
import com.peiwc.billing.process.mail.MailSender;

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
	
	@Autowired
	private BillingInformationProcess billingInformationProcess;

	@Value("${csv.name.suffix}")
	private String dateFormatPattern;

	@Autowired
	private MailSender mailSender;

	/**
	 * this is the main process that checks if the process has already run and
	 * then writes the entire data from a cycle to database.
	 *
	 * @return a flag indicating the process has been run successfully
	 */
	public boolean runWellsFargoCSVProcess() {
		boolean hasRunSuccessfully = true;
		final Date currentDate = new Date();
		int nextCycle = 0;
		if (!processManagerCheck.checkIfProcessHasAlreadyRun(currentDate)) {
			try {
				nextCycle = processManagerCheck.getNextCycleNumber();
				MainProcess.LOGGER.info("Process Has not been run, Next cycle is: " + nextCycle);
				final WFMamOpHDRTRLR wfMamOpHDRTRLR = wfMamOpHDRTRLRProcess.saveNextCycle(nextCycle, currentDate);
				wfMamOpHDRTRLRProcess.setCurrentState(ProcessState.PENDING_START, nextCycle);
				MainProcess.LOGGER.info("wfMamOpHDRTRLR cycleNumber: " + wfMamOpHDRTRLR.getCycleNumber()
						+ " , creationDate:" + wfMamOpHDRTRLR.getCreationDate());
				wfMamOpHDRTRLRProcess.setCurrentState(ProcessState.RUNNING, nextCycle);
				// here goes the main process where the data for WF_MAM_SRC_FILE
				// table is filled.
				fillTables(nextCycle);
				final String fileNameId = System.getProperty("csv.id.prefix");
				final String fileNamePrefix = System.getProperty("csv.name.prefix");
				final String fileNameLocation = System.getProperty("csv.path.location");
				String fileName = "test.csv";
				if (StringUtils.isNotEmpty(fileNamePrefix) && StringUtils.isNotEmpty(fileNameId)) {
					fileName = fileNameId + "_" + fileNamePrefix + generateFileSuffix() + ".csv";
				}
				try {
					final int totalRecordCount = writeWFMAMSrcFileCSV.writeDataToCSV(nextCycle, fileName);
					wfMamOpHDRTRLRProcess.saveTotalRecordsProcessed(nextCycle, totalRecordCount);
					wfMamOpHDRTRLRProcess.moveGeneratedFileToExternalLocation(fileName, fileNameLocation);
				} catch (final IOException ex) {
					MainProcess.LOGGER.error(ex, ex);
					hasRunSuccessfully = false;
					wfMamOpHDRTRLRProcess.saveErrorMessage(nextCycle, ex.getMessage());
				}
				if (hasRunSuccessfully) {
					wfMamOpHDRTRLRProcess.setCurrentState(ProcessState.FINISHED, nextCycle);
					mailSender.sendMailMessage("Process #" + nextCycle + "has run successfully ");
				}
			} catch (final Exception ex) {
				wfMamOpHDRTRLRProcess.saveErrorMessage(nextCycle, ex.getMessage());
				mailSender.sendMailMessage("Process # " + nextCycle + " , has failed: " + ex.getMessage());
			}
		} else {
			MainProcess.LOGGER.info("Process has already run today");
			hasRunSuccessfully = false;
			wfMamOpHDRTRLRProcess.setProcessAsAlreadyRunForToday();
		}
		return hasRunSuccessfully;
	}

	private void fillTables(final int nextCycle) {
		// TODO: generate here the process to fill the tables with data.
		

		final String submissionNumber = "297110";

		String name = billingInformationProcess.getName(submissionNumber);
		String phone = billingInformationProcess.getPhone(submissionNumber);
		String email = billingInformationProcess.getEmail(submissionNumber);
		String address = billingInformationProcess.getAddress(submissionNumber);
		String city = billingInformationProcess.getCity(submissionNumber);
		String state = billingInformationProcess.getState(submissionNumber);
		String zip = billingInformationProcess.getZip(submissionNumber);

	}

	private String generateFileSuffix() {
		final Date date = new Date();
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormatPattern);
		return sdf.format(date);
	}

}
