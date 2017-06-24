package com.peiwc.billing;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.peiwc.billing.configuration.ConfigurationBean;
import com.peiwc.billing.process.MainProcess;

/**
 * Main Start Point for Processing CSV job
 */
public class App {

	private static final Logger LOGGER = Logger.getLogger(App.class);

	/**
	 * main method for start point
	 *
	 * @param args
	 *            arguments passed from command line
	 */
	public static void main(final String[] args) {
		DOMConfigurator.configure("wells-fargo-log4j.xml");
		try {
			final AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationBean.class);
			// final ApplicationContext ctx = new
			// ClassPathXmlApplicationContext("applicationContext.xml");
			final MainProcess mainProcess = ctx.getBean(MainProcess.class);
			mainProcess.runWellsFargoCSVProcess();
			ctx.close();
		} catch (final Exception e) {
			App.LOGGER.error(e, e);
		}

		System.exit(0);
	}
}