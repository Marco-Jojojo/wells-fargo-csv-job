package com.peiwc.billing.process.billing1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.peiwc.billing.dao.ProcessPoliciesLessOrEqual2YearsOldDAO;
import com.peiwc.billing.domain.WFMamSrcFile;

@Component("processPoliciesLessOrEqual2YearsOld")
public class ProcessPoliciesLessOrEqual2YearsOld {
	
	@Autowired
	private ProcessPoliciesLessOrEqual2YearsOldDAO processPoliciesLessOrEqual2YearsOldDAO;

	public void processPolicies(int cycleNumber) {
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		cal.add(Calendar.YEAR, -2);
		Date twoYearsBefore = cal.getTime();
		SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayFormatted = sqlFormat.format(today);
		String twoYearsBeforeFormatted = sqlFormat.format(twoYearsBefore);
		List<WFMamSrcFile> recordsFromPolicyMaster = this.processPoliciesLessOrEqual2YearsOldDAO.findAll(twoYearsBeforeFormatted, todayFormatted);
		for (WFMamSrcFile recordFromPM : recordsFromPolicyMaster) {
			List<WFMamSrcFile> recordsFound = this.processPoliciesLessOrEqual2YearsOldDAO.findOneInWFSrcFile(cycleNumber, recordFromPM.getSecondaryAuth());
			if (CollectionUtils.isEmpty(recordsFound)) {
				this.processPoliciesLessOrEqual2YearsOldDAO.insert(recordFromPM);
			}
		}
	}


}
