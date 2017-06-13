package com.peiwc.billing.process;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peiwc.billing.configuration.ConfigurationBeanMock;

@ContextConfiguration(classes = { ConfigurationBeanMock.class })
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class ProcessManagerCheckTest {

	@Autowired
	private ProcessManagerCheck processManagerCheck;

	/**
	 * this test should check if process has already been run after setting a
	 * valid date in the system.
	 *
	 * @throws ParseException
	 *             if current date can not be parsed normally
	 */
	@Test
	public void processShouldAlreadyRun() throws Exception {
		final String currentDate = "2017-06-11";
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		final Date date = format.parse(currentDate);
		Assert.assertTrue(processManagerCheck.checkIfProcessHasAlreadyRun(date));
	}

	/**
	 * this test should check if the process have been not run in the date set
	 *
	 * @throws ParseException
	 *             if current date can not be parsed normally
	 */
	@Test
	public void processShouldHaveNotRun() throws ParseException {
		final String currentDate = "2017-06-09";
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		final Date date = format.parse(currentDate);
		Assert.assertFalse(processManagerCheck.checkIfProcessHasAlreadyRun(date));
	}

	/**
	 * this test should check if the next cycle number is one plus the one get
	 * from the database.
	 */
	@Test
	public void testGetNextCycleNumber() {
		Assert.assertEquals(2007, this.processManagerCheck.getNextCycleNumber());
	}

}
