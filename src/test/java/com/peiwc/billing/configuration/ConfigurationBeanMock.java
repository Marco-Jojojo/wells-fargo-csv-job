package com.peiwc.billing.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@ComponentScan(basePackages = { "com.peiwc.billing" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { ConfigurationBean.class }) })
@EnableTransactionManagement()
@PropertySource("file:database.h2.properties")
public class ConfigurationBeanMock implements TransactionManagementConfigurer {

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
		final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		final EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("file:database/create-db.sql")
				.addScript("file:database/insert-data.sql").build();
		return db;
	}

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
		factory.setPersistenceUnitName(PERSISTENCE_APP_NAME);
		return factory;
	}

	@Bean("vendorAdapter")
	public JpaVendorAdapter getJpaVendorAdapter() {
		final HibernateJpaVendorAdapter hibernateVendor = new HibernateJpaVendorAdapter();
		hibernateVendor.setDatabasePlatform(databasePlatform);
		hibernateVendor.setShowSql(Boolean.valueOf(showSql));
		hibernateVendor.setGenerateDdl(Boolean.valueOf(generateDdl));
		return hibernateVendor;
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
