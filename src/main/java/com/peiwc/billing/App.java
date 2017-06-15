package com.peiwc.billing;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.peiwc.billing.configuration.ConfigurationBean;
import com.peiwc.billing.process.MainProcess;

/**
 * Main Start Point for Processing CSV job
 *
 */
public class App {

	/**
	 * main method for start point
	 *
	 * @param args
	 *            arguments passed from command line
	 */
	public static void main(final String[] args) {
		DOMConfigurator.configure("wells-fargo-log4j.xml");
		final AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationBean.class);
		final MainProcess mainProcess = ctx.getBean(MainProcess.class);
		mainProcess.runWellsFargoCSVProcess();
		ctx.close();
		System.exit(0);
	}
}