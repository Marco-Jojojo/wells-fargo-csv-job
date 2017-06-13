package com.peiwc.billing.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

	/**
	 * global persistence name shared across application
	 */
	public static final String PERSISTENCE_APP_NAME = "WFPU";

	@Bean("dataSource")
	public DataSource getDataSource() {
		final BasicDataSource dataSource = new BasicDataSource();
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
		final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(getDataSource());
		emf.setJpaDialect(new HibernateJpaDialect());
		emf.setPackagesToScan("com.peiwc.billing.domain");
		emf.setJpaVendorAdapter(getJpaVendorAdapter());
		final Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect", databasePlatform);
		emf.setJpaProperties(jpaProperties);
		emf.setPersistenceUnitName(PERSISTENCE_APP_NAME);
		return emf;
	}

	@Bean("vendorAdapter")
	public JpaVendorAdapter getJpaVendorAdapter() {
		final HibernateJpaVendorAdapter hibernateVendor = new HibernateJpaVendorAdapter();
		hibernateVendor.setDatabasePlatform(databasePlatform);
		hibernateVendor.setShowSql(Boolean.valueOf(showSql));
		hibernateVendor.setGenerateDdl(Boolean.valueOf(generateDdl));
		return hibernateVendor;
	}

	@Bean("transactionManager")
	public JpaTransactionManager setTransactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(getDataSource());
		return transactionManager;
	}

	@Bean("namedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		final NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		return namedParameterJdbcTemplate;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}

}
