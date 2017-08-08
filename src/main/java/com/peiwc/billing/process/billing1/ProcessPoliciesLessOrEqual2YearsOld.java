package com.peiwc.billing.process.billing1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.ProcessPoliciesLessOrEqual2YearsOldDAO;
import com.peiwc.billing.dao.WFMamErrLogRepository;
import com.peiwc.billing.domain.WFMamErrLog;
import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

/**
 * @author jolivarria
 *
 */
@Component("processPoliciesLessOrEqual2YearsOld")
public class ProcessPoliciesLessOrEqual2YearsOld {

	private static final Logger LOGGER = Logger.getLogger(ProcessPoliciesLessOrEqual2YearsOld.class);

	@Autowired
	private ProcessPoliciesLessOrEqual2YearsOldDAO processPoliciesLessOrEqual2YearsOldDAO;

	@Autowired
	private WFMamErrLogRepository wfMamErrLogRepository;

	private static final String PENDING_REC = "PENDING_REC";

	private static final String POLICY_NUMBER_DESCRIPTION_ERROR = "Policy number cannot be zero or null";

	private static final String DUE_DATE_DESCRIPTION_ERROR = "Due date cannot be null";

	/**
	 * Get two years old records from POLICY_MASTER and insert them
	 * WF_MAM_SRC_FILE
	 *
	 * @param cycleNumber
	 */
	public void processPolicies(final int cycleNumber) {
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER
				.info("PROCESS STATUS: Starting ProcessPoliciesLessOrEqual2YearsOld.processPolicies");
		final List<WFMamSrcFile> recordsFromPolicyMaster = this.processPoliciesLessOrEqual2YearsOldDAO
				.findAllPoliciesForZeroBills();
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER
				.info("PROCESS STATUS: Getting records: " + recordsFromPolicyMaster.size());
		int seqNumber = this.processPoliciesLessOrEqual2YearsOldDAO.getMaxSequenceNumber(cycleNumber) + 1;
		int createCounter = 0;
		for (final WFMamSrcFile recordFromPM : recordsFromPolicyMaster) {
				final WFMamSrcFilePK id = new WFMamSrcFilePK();
				id.setCycleNumber(cycleNumber);
				id.setSequenceNumber(seqNumber);
				if ("0".equals(recordFromPM.getReferenceNumber())) {
					this.sendError(cycleNumber, seqNumber,
							ProcessPoliciesLessOrEqual2YearsOld.POLICY_NUMBER_DESCRIPTION_ERROR);
				} else if (recordFromPM.getDueDate() == null) {
					this.sendError(cycleNumber, seqNumber,
							ProcessPoliciesLessOrEqual2YearsOld.DUE_DATE_DESCRIPTION_ERROR);
				}
				seqNumber += 1;
				recordFromPM.setId(id);
				this.processPoliciesLessOrEqual2YearsOldDAO.create(recordFromPM);
				createCounter += 1;
		}
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER.info("PROCESS STATUS: created: " + createCounter);
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER
				.info("PROCESS STATUS: Ending ProcessPoliciesLessOrEqual2YearsOld.processPolicies");
	}

	/**
	 * Get records from POLICY_MASTER where EFFECTIVE DAY > currentDay and
	 * insert them in WF_MAM_SRC_FILE table
	 *
	 * @param cycleNumber
	 */
	public void futurePolicies(final int cycleNumber) {
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER
				.info("PROCESS STATUS: Starting ProcessPoliciesLessOrEqual2YearsOld.futurePolicies");
		final Calendar cal = Calendar.getInstance();
		final Date today = cal.getTime();
		final SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		final String todayFormatted = sqlFormat.format(today);
		final List<WFMamSrcFile> recordsFromPolicyMaster = this.processPoliciesLessOrEqual2YearsOldDAO
				.findAllFuturePolicies(todayFormatted);
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER
				.info("PROCESS STATUS: Getting records: " + recordsFromPolicyMaster.size());
		int seqNumber = this.processPoliciesLessOrEqual2YearsOldDAO.getMaxSequenceNumber(cycleNumber) + 1;
		int createCounter = 0;
		for (final WFMamSrcFile recordFromPM : recordsFromPolicyMaster) {
			final List<WFMamSrcFile> recordsFound = this.processPoliciesLessOrEqual2YearsOldDAO
					.findOneInWFSrcFile(cycleNumber, recordFromPM.getSubmissionNumber());
			if (CollectionUtils.isEmpty(recordsFound)) {
				final WFMamSrcFilePK id = new WFMamSrcFilePK();
				id.setCycleNumber(cycleNumber);
				id.setSequenceNumber(seqNumber);
				if ("0".equals(recordFromPM.getReferenceNumber())) {
					this.sendError(cycleNumber, seqNumber,
							ProcessPoliciesLessOrEqual2YearsOld.POLICY_NUMBER_DESCRIPTION_ERROR);
				} else if (recordFromPM.getDueDate() == null) {
					this.sendError(cycleNumber, seqNumber,
							ProcessPoliciesLessOrEqual2YearsOld.DUE_DATE_DESCRIPTION_ERROR);
				}
				seqNumber += 1;
				recordFromPM.setId(id);
				this.processPoliciesLessOrEqual2YearsOldDAO.create(recordFromPM);
				createCounter += 1;
			}
		}
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER.info("PROCESS STATUS: created: " + createCounter);
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER
				.info("PROCESS STATUS: Ending ProcessPoliciesLessOrEqual2YearsOld.futurePolicies");
	}

	private void sendError(final int cycleNumber, final int sequenceNumber, final String errorMsg) {
		final WFMamErrLog error = new WFMamErrLog();
		error.setCycleNumber(cycleNumber);
		error.setSequenceNumber(sequenceNumber);
		error.setDescription(errorMsg);
		error.setStatus(ProcessPoliciesLessOrEqual2YearsOld.PENDING_REC);
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER.info("PROCESS STATUS: MSG ERROR: " + error.getDescription());
		wfMamErrLogRepository.saveAndFlush(error);

	}

}
