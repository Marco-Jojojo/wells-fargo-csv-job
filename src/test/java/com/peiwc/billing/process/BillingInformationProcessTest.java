package com.peiwc.billing.process;

import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;
import org.junit.Before;
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
public class BillingInformationProcessTest {

	@Autowired
	private BillingInformationProcess billingInformationProcess;

	@Before
	public void setup() {
		BasicConfigurator.configure();
	}

	@Test
	public void testGetName() {
		int submissionNumber = 26533;
		final String success = billingInformationProcess.getName(submissionNumber);
		Assert.assertNotNull(success);
	}

	@Test
	public void testGetPhone() {
		int submissionNumber = 26533;
		final String success = billingInformationProcess.getPhone(submissionNumber);
		Assert.assertNotNull(success);
	}

	@Test
	public void testGetAddress() {
		int submissionNumber = 26533;
		final String success = billingInformationProcess.getAddress(submissionNumber);
		Assert.assertNotNull(success);
	}

	@Test
	public void testGetAddress2() {
		int submissionNumber = 26533;
		final String success = billingInformationProcess.getAddress2(submissionNumber);
		Assert.assertNotNull(success);
	}

	@Test
	public void testGetCity() {
		int submissionNumber = 26533;
		final String success = billingInformationProcess.getCity(submissionNumber);
		Assert.assertNotNull(success);
	}

	@Test
	public void testGetState() {
		int submissionNumber = 26533;
		final String success = billingInformationProcess.getState(submissionNumber);
		Assert.assertNotNull(success);
	}

	@Test
	public void testGetZip() {
		int submissionNumber = 26533;
		final String success = billingInformationProcess.getZip(submissionNumber);
		Assert.assertNotNull(success);
	}

}
