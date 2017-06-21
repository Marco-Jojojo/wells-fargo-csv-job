package com.peiwc.billing.process.billingP1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
		Date twoYearsFromToday = cal.getTime();
		SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayFormatted = sqlFormat.format(today);
		String twoYearsFromTodayFormatted = sqlFormat.format(twoYearsFromToday);
		List<WFMamSrcFile> rows = this.processPoliciesLessOrEqual2YearsOldDAO.findAll(twoYearsFromTodayFormatted, todayFormatted);
		for (WFMamSrcFile row : rows) {
			this.processPoliciesLessOrEqual2YearsOldDAO.insertBill(row);
		}
	}


}
