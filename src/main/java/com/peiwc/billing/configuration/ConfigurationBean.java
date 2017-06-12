package com.peiwc.billing.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@ComponentScans({ @ComponentScan("com.peiwc.billing") })
@EnableTransactionManagement()
@PropertySource("file:database.mssql.properties")
public class ConfigurationBean implements TransactionManagementConfigurer {

	@Value("${database.mssql.driverClassName}")
	private String driverClassName;
	@Value("${database.mssql.url}")
	private String url;
	@Value("${database.mssql.username}")
	private String username;
	@Value("${database.mssql.password}")
	private String password;
	@Value("${database.mssql.maxActive}")
	private String maxActive;
	@Value("${database.mssql.minIdle}")
	private String minIdle;
	@Value("${database.mssql.databasePlatform}")
	private String databasePlatform;
	@Value("${database.mssql.showSql}")
	private String showSql;
	@Value("${database.mssql.generateDdl}")
	private String generateDdl;

	@Bean("dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMaxActive(Integer.parseInt(maxActive));
		dataSource.setMinIdle(Integer.parseInt(minIdle));
		return dataSource;
	}

	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(getDataSource());
		emf.setJpaDialect(new HibernateJpaDialect());
		emf.setPackagesToScan("com.peiwc.billing.domain");
		emf.setJpaVendorAdapter(getJpaVendorAdapter());
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect", databasePlatform);
		emf.setJpaProperties(jpaProperties);
		return emf;
	}

	@Bean("vendorAdapter")
	public JpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateVendor = new HibernateJpaVendorAdapter();
		hibernateVendor.setDatabasePlatform(databasePlatform);
		hibernateVendor.setShowSql(Boolean.valueOf(showSql));
		hibernateVendor.setGenerateDdl(Boolean.valueOf(generateDdl));
		return hibernateVendor;
	}

	@Bean("transactionManager")
	public JpaTransactionManager setTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(getDataSource());
		return transactionManager;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}

}
