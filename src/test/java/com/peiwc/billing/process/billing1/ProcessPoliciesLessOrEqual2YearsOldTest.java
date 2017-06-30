package com.peiwc.billing.process.billing1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peiwc.billing.configuration.ConfigurationBeanMock;
import com.peiwc.billing.dao.ProcessPoliciesLessOrEqual2YearsOldDAO;
import com.peiwc.billing.domain.WFMamSrcFile;

@ContextConfiguration(classes = { ConfigurationBeanMock.class })
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class ProcessPoliciesLessOrEqual2YearsOldTest {

	@Autowired
	private ProcessPoliciesLessOrEqual2YearsOldDAO processPoliciesLessOrEqual2YearsOldDAO;

	@Test
	public void testFindAll() {
		final Calendar cal = Calendar.getInstance();
		final Date today = cal.getTime();
		cal.add(Calendar.YEAR, -2);
		final Date twoYearsBefore = cal.getTime();
		final SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
		final String todayFormatted = sqlFormat.format(today);
		final String twoYearsBeforeFormatted = sqlFormat.format(twoYearsBefore);
		final List<WFMamSrcFile> rows = this.processPoliciesLessOrEqual2YearsOldDAO
				.findAllTwoYearsOldPolicies(twoYearsBeforeFormatted, todayFormatted);
		Assert.assertNotEquals(0, rows.size());
	}
}
