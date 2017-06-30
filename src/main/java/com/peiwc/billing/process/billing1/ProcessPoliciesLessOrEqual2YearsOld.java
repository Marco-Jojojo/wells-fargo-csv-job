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
import com.peiwc.billing.domain.WFMamSrcFile;
import com.peiwc.billing.domain.WFMamSrcFilePK;

@Component("processPoliciesLessOrEqual2YearsOld")
public class ProcessPoliciesLessOrEqual2YearsOld {

	private static final Logger LOGGER = Logger.getLogger(ProcessPoliciesLessOrEqual2YearsOld.class);

	@Autowired
	private ProcessPoliciesLessOrEqual2YearsOldDAO processPoliciesLessOrEqual2YearsOldDAO;

	public void processPolicies(final int cycleNumber) {
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER
				.info("PROCESS STATUS: Starting ProcessPoliciesLessOrEqual2YearsOld.processPolicies");
		final Calendar cal = Calendar.getInstance();
		final Date today = cal.getTime();
		cal.add(Calendar.YEAR, -2);
		final Date twoYearsBefore = cal.getTime();
		final SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		final String todayFormatted = sqlFormat.format(today);
		final String twoYearsBeforeFormatted = sqlFormat.format(twoYearsBefore);
		final List<WFMamSrcFile> recordsFromPolicyMaster = this.processPoliciesLessOrEqual2YearsOldDAO
				.findAllTwoYearsOldPolicies(twoYearsBeforeFormatted, todayFormatted);
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
				seqNumber += 1;
				recordFromPM.setId(id);
				this.processPoliciesLessOrEqual2YearsOldDAO.create(recordFromPM);
				createCounter += 1;
			}
		}
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER.info("PROCESS STATUS: created: " + createCounter);
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER
				.info("PROCESS STATUS: Ending ProcessPoliciesLessOrEqual2YearsOld.processPolicies");
	}

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

}
