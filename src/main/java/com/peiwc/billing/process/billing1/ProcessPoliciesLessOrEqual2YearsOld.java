package com.peiwc.billing.process.billing1;

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

	public void processPolicies(final int cycleNumber) {
		final Calendar cal = Calendar.getInstance();
		final Date today = cal.getTime();
		cal.add(Calendar.YEAR, -2);
		final Date twoYearsFromToday = cal.getTime();
		final SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		final String todayFormatted = sqlFormat.format(today);
		final String twoYearsFromTodayFormatted = sqlFormat.format(twoYearsFromToday);
		final List<WFMamSrcFile> rows = this.processPoliciesLessOrEqual2YearsOldDAO.findAll(twoYearsFromTodayFormatted,
				todayFormatted);
		for (final WFMamSrcFile row : rows) {
			this.processPoliciesLessOrEqual2YearsOldDAO.insertBill(row);
		}
	}

}
