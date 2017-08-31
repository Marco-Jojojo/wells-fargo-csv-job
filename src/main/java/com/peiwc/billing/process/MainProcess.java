package com.peiwc.billing.process;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.peiwc.billing.App;
import com.peiwc.billing.dao.WFMamErrLogRepository;
import com.peiwc.billing.domain.WFMamErrLog;
import com.peiwc.billing.domain.WFMamOpHDRTRLR;
import com.peiwc.billing.process.billing1.WFMamSrcGenRecs;
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
	private WFMamSrcGenRecs wfMamSrcGenRecs;
	@Autowired
	private BillingPart2Process billingPart2Process;
	@Autowired
                  private UpdateHistoricalPolicies updateHistoricalPolicies;
	@Value("${csv.name.suffix}")
	private String dateFormatPattern;
	@Autowired
	private MailSender mailSender;
	@Autowired
	private WFMamErrLogRepository wfMamErrLogRepository;

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
				MainProcess.LOGGER.info("Process has not been run, Next cycle number is: " + nextCycle);
				final WFMamOpHDRTRLR wfMamOpHDRTRLR = wfMamOpHDRTRLRProcess.saveNextCycle(nextCycle, currentDate);
				wfMamOpHDRTRLRProcess.setCurrentState(ProcessState.PENDING_START, nextCycle);
				MainProcess.LOGGER.info("wfMamOpHDRTRLR cycleNumber: " + wfMamOpHDRTRLR.getCycleNumber()
				        + " , creationDate:" + wfMamOpHDRTRLR.getCreationDate());
				wfMamOpHDRTRLRProcess.setCurrentState(ProcessState.RUNNING, nextCycle);
				// here goes the main process where the data for WF_MAM_SRC_FILE
				// table is filled.
				wfMamSrcGenRecs.billingProcess(nextCycle);
				billingPart2Process.updateUserInfo(nextCycle);
                                                                        updateHistoricalPolicies.updateHistoricalBills();
				final String fileNameId = System.getProperty("csv.id.prefix");
				final String fileNamePrefix = System.getProperty("csv.name.prefix");
				String fileName = "test.txt";
				if (StringUtils.isNotEmpty(fileNamePrefix) && StringUtils.isNotEmpty(fileNameId)) {
					fileName = fileNameId + "_" + fileNamePrefix + generateFileSuffix() + ".txt";
				}
				try {
					final int totalRecordCount = writeWFMAMSrcFileCSV.writeDataToCSV(nextCycle, fileName);
					wfMamOpHDRTRLRProcess.saveTotalRecordsProcessed(nextCycle, totalRecordCount);
					wfMamOpHDRTRLRProcess.saveFileName(nextCycle, fileName);
				} catch (final IOException ex) {
					MainProcess.LOGGER.error(ex, ex);
					hasRunSuccessfully = false;
					wfMamOpHDRTRLRProcess.saveStatusMessage(nextCycle, ex.getMessage());
				}
				if (hasRunSuccessfully) {
					wfMamOpHDRTRLRProcess.setCurrentState(ProcessState.FINISHED, nextCycle);
					mailSender.sendMailMessage(
					        "Cycle Number: " + nextCycle + " has run successfully.\r\n " + getFailedRecordsMessage(nextCycle));
				}
			} catch (final Exception ex) {
				hasRunSuccessfully = false;
				wfMamOpHDRTRLRProcess.saveStatusMessage(nextCycle, ex.getMessage());
				mailSender.sendMailMessage("Process (Cycle Number) #" + nextCycle + ", has failed: " + ex.getMessage());
				MainProcess.LOGGER.error(ex, ex);
			}
		} else {
			MainProcess.LOGGER.info("Process has already run today");
			hasRunSuccessfully = false;
			wfMamOpHDRTRLRProcess.setProcessAsAlreadyRunForToday();
		}
		return hasRunSuccessfully;
	}

	private String getFailedRecordsMessage(final int nextCycle) {
		final StringBuilder buffer = new StringBuilder();
		final List<WFMamErrLog> errors = this.wfMamErrLogRepository.getErrorsFromCycleNumber(nextCycle);
		if (!CollectionUtils.isEmpty(errors)) {
			buffer.append("\nThe following records have not been processed since there was no information in mandatory fields: \r\n");
			for (final WFMamErrLog wfMamErrLog : errors) {
				final int sequenceNumber = wfMamErrLog.getSequenceNumber();
				buffer.append("\n-> Sequence Number: ").append(sequenceNumber).append(" / Reason: ").append(wfMamErrLog.getDescription());
			}
		}
		return buffer.toString();
	}

	private String generateFileSuffix() {
		final Date date = new Date();
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormatPattern);
		return sdf.format(date);
	}
}
