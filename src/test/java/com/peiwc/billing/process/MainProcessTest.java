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

/**
 * this class test {@link MainProcess}
 */
@ContextConfiguration(classes = { ConfigurationBeanMock.class })
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class MainProcessTest {

	@Autowired
	private MainProcess mainProcess;

	/**
	 * sets up logging before tests begins.
	 */
	@Before
	public void setup() {
		BasicConfigurator.configure();
	}

	/**
	 * this test checks if runWellsFargoCSVProcess runs correctly
	 */
	@Test
	public void testRunWellsFargoCSVProcess() {
		final boolean success = this.mainProcess.runWellsFargoCSVProcess();
		Assert.assertTrue(success);
	}

}
