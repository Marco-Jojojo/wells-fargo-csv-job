package com.peiwc.billing;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.w3c.dom.DOMConfiguration;

import com.peiwc.billing.configuration.ConfigurationBean;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
    	DOMConfigurator.configure("wells-fargo-log4j.xml");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationBean.class);
        
        
    }
}
