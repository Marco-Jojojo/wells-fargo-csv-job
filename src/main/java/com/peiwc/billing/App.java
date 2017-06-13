package com.peiwc.billing;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.w3c.dom.DOMConfiguration;

import com.peiwc.billing.configuration.ConfigurationBean;

/**
 * Main Start Point for Processing CSV job
 *
 */
public class App {
	
	/**
	 * main method for start point
	 * @param args arguments passed from command line
	 */
	public static void main(String[] args) {
		DOMConfigurator.configure("wells-fargo-log4j.xml");
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationBean.class);
		System.out.println(ctx);
	}
}
