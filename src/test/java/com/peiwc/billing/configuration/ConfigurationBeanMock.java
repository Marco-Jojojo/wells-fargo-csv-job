package com.peiwc.billing.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration file for checking integration tests in current application.
 */
@Configuration
@ComponentScan(basePackages = { "com.peiwc.billing" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { ConfigurationBean.class }) })
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = { "com.peiwc.billing.dao" })
@EnableTransactionManagement
@PropertySource("file:database.h2.properties")
public class ConfigurationBeanMock {

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

	/**
	 * generates global datasource.
	 *
	 * @return global datasource.
	 */
	@Bean("dataSource")
	public DataSource getDataSource() {
		final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		final EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("file:database/create-db.sql")
				.addScript("file:database/insert-data.sql").build();
		return db;
	}

	/**
	 * generates entitymanager factory.
	 *
	 * @return a global entity manager factory.
	 */
	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setPersistenceProvider(new HibernatePersistenceProvider());
		factory.setDataSource(getDataSource());
		factory.setJpaDialect(new HibernateJpaDialect());
		factory.setPackagesToScan("com.peiwc.billing.domain");
		factory.setJpaVendorAdapter(getJpaVendorAdapter());
		final Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect", databasePlatform);
		factory.setJpaProperties(jpaProperties);
		factory.setPersistenceUnitName(ConfigurationBeanMock.PERSISTENCE_APP_NAME);
		return factory;
	}

	/**
	 * generates a hibernate vendor adapter.
	 *
	 * @return a generated vendor adapter.
	 */
	@Bean("vendorAdapter")
	public JpaVendorAdapter getJpaVendorAdapter() {
		final HibernateJpaVendorAdapter hibernateVendor = new HibernateJpaVendorAdapter();
		hibernateVendor.setDatabasePlatform(databasePlatform);
		hibernateVendor.setShowSql(Boolean.valueOf(showSql));
		hibernateVendor.setGenerateDdl(Boolean.valueOf(generateDdl));
		return hibernateVendor;
	}

	/**
	 * generates a named jdbc template.
	 *
	 * @return a global named jdbc template.
	 */
	@Bean("namedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		final NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		return namedParameterJdbcTemplate;
	}

	@Bean
	JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

}
