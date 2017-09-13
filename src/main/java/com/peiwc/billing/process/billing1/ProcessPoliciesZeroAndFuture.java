package com.peiwc.billing.process.billing1;

import com.peiwc.billing.dao.ProcessDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;
import com.peiwc.billing.helpers.ErrorHandling;
import com.peiwc.billing.dao.ProcessPoliciesZeroAndFutureDAO;

/**
 * @author jolivarria
 *
 */
@Service("processPoliciesLessOrEqual2YearsOld")
public class ProcessPoliciesZeroAndFuture {

	private static final Logger LOGGER = Logger.getLogger(ProcessPoliciesZeroAndFuture.class);

	@Autowired
	private ProcessPoliciesZeroAndFutureDAO processPoliciesLessOrEqual2YearsOldDAO;
        
	@Autowired
                  private ErrorHandling errorHandling;
        
	@Autowired
                  private ProcessDAO processDAO;

	/**
	 * Get Zero bill policies records and insert them
	 * WF_MAM_SRC_FILE
	 *
	 * @param cycleNumber
	 */
	public void processPolicies(final int cycleNumber) {
		ProcessPoliciesZeroAndFuture.LOGGER
				.info("PROCESS STATUS: Starting ProcessPoliciesLessOrEqual2YearsOld.processPolicies");
		final List<WFMamSrcFile> recordsFromPolicyMaster = this.processPoliciesLessOrEqual2YearsOldDAO
				.findAllPoliciesForZeroBills();
		ProcessPoliciesZeroAndFuture.LOGGER
				.info("PROCESS STATUS: Getting records: " + recordsFromPolicyMaster.size());
		int seqNumber = processDAO.getMaxSequenceNumber(cycleNumber) + 1;
		int createCounter = 0;
		for (final WFMamSrcFile recordFromPM : recordsFromPolicyMaster) {
				final WFMamSrcFilePK id = new WFMamSrcFilePK();
				id.setCycleNumber(cycleNumber);
				id.setSequenceNumber(seqNumber);
				errorHandling.checkingMandatoryFields(recordFromPM, cycleNumber, seqNumber);
				seqNumber += 1;
				recordFromPM.setId(id);
				this.processPoliciesLessOrEqual2YearsOldDAO.create(recordFromPM);
				createCounter += 1;
		}
		ProcessPoliciesZeroAndFuture.LOGGER.info("PROCESS STATUS: created: " + createCounter);
		ProcessPoliciesZeroAndFuture.LOGGER
				.info("PROCESS STATUS: Ending ProcessPoliciesLessOrEqual2YearsOld.processPolicies");
	}

	/**
	 * Get records from POLICY_MASTER where EFFECTIVE DAY > currentDay and
	 * insert them in WF_MAM_SRC_FILE table
	 *
	 * @param cycleNumber
	 */
	public void futurePolicies(final int cycleNumber) {
		ProcessPoliciesZeroAndFuture.LOGGER
				.info("PROCESS STATUS: Starting ProcessPoliciesLessOrEqual2YearsOld.futurePolicies");
		final Calendar cal = Calendar.getInstance();
		final Date today = cal.getTime();
		final SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		final String todayFormatted = sqlFormat.format(today);
		final List<WFMamSrcFile> recordsFromPolicyMaster = this.processPoliciesLessOrEqual2YearsOldDAO
				.findAllFuturePolicies(todayFormatted);
		ProcessPoliciesZeroAndFuture.LOGGER
				.info("PROCESS STATUS: Getting records: " + recordsFromPolicyMaster.size());
		int seqNumber = processDAO.getMaxSequenceNumber(cycleNumber) + 1;
		int createCounter = 0;
		for (final WFMamSrcFile recordFromPM : recordsFromPolicyMaster) {
			final List<WFMamSrcFile> recordsFound = this.processPoliciesLessOrEqual2YearsOldDAO
					.findOneInWFSrcFile(cycleNumber, recordFromPM.getSubmissionNumber());
			if (CollectionUtils.isEmpty(recordsFound)) {
				final WFMamSrcFilePK id = new WFMamSrcFilePK();
				id.setCycleNumber(cycleNumber);
				id.setSequenceNumber(seqNumber);
				errorHandling.checkingMandatoryFields(recordFromPM, cycleNumber, seqNumber);
				seqNumber += 1;
				recordFromPM.setId(id);
				this.processPoliciesLessOrEqual2YearsOldDAO.create(recordFromPM);
				createCounter += 1;
			}
		}
		ProcessPoliciesZeroAndFuture.LOGGER.info("PROCESS STATUS: created: " + createCounter);
		ProcessPoliciesZeroAndFuture.LOGGER
				.info("PROCESS STATUS: Ending ProcessPoliciesLessOrEqual2YearsOld.futurePolicies");
	}

}
