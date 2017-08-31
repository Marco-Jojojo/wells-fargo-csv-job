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
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

/**
 * this class test {@link MainProcess}
 */
@RunWith(PowerMockRunner.class)
@ContextConfiguration(classes = { ConfigurationBeanMock.class })
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@PrepareForTest(UpdateHistoricalPoliciesImpl.class)
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
                        //Suppressing this method since H2 is behaving wrongly for actual SQL valid syntax to execute a stored procedure
                        suppress(method(UpdateHistoricalPoliciesImpl.class, "updateHistoricalBills"));
		final boolean success = this.mainProcess.runWellsFargoCSVProcess();
		Assert.assertTrue(success);
		final boolean failure = this.mainProcess.runWellsFargoCSVProcess();
		Assert.assertFalse(failure);
	}

}
