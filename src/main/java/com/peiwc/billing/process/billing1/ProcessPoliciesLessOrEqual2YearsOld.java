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
		cal.add(Calendar.YEAR, -2);
		final Date twoYearsBefore = cal.getTime();
		final SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		final String twoYearsBeforeFormatted = sqlFormat.format(twoYearsBefore);
		final List<WFMamSrcFile> recordsFromPolicyMaster = this.processPoliciesLessOrEqual2YearsOldDAO
				.findAll(twoYearsBeforeFormatted);
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER
				.info("PROCESS STATUS: Getting records: " + recordsFromPolicyMaster.size());
		int seqNumber = this.processPoliciesLessOrEqual2YearsOldDAO.getMaxSequenceNumber(cycleNumber) + 1;
		for (final WFMamSrcFile recordFromPM : recordsFromPolicyMaster) {
			final List<WFMamSrcFile> recordsFound = this.processPoliciesLessOrEqual2YearsOldDAO
					.findOneInWFSrcFile(cycleNumber, recordFromPM.getSecondaryAuth());
			ProcessPoliciesLessOrEqual2YearsOld.LOGGER.info("PROCESS STATUS: Getting record: " + recordsFound.size());
			if (CollectionUtils.isEmpty(recordsFound)) {
				final WFMamSrcFilePK id = new WFMamSrcFilePK();
				id.setCycleNumber(cycleNumber);
				id.setSequenceNumber(seqNumber);
				seqNumber += 1;
				recordFromPM.setId(id);
				recordFromPM.setInvoiceDate(null);
				this.processPoliciesLessOrEqual2YearsOldDAO.create(recordFromPM);
			}
		}
		ProcessPoliciesLessOrEqual2YearsOld.LOGGER
				.info("PROCESS STATUS: Ending ProcessPoliciesLessOrEqual2YearsOld.processPolicies");
	}

}
