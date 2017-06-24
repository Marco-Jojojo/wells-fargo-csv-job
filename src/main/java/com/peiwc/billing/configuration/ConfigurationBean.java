package com.peiwc.billing.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * this bean contains all the configuration managed across the current
 * application.
 */
@Configuration
@ComponentScans({ @ComponentScan("com.peiwc.billing") })
@PropertySources({ @PropertySource("file:database.mssql.properties"), @PropertySource("file:common.properties") })
public class ConfigurationBean {

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
	 * generates a global datasource using the persistence settings in the
	 * properties file.
	 *
	 * @return a datasource managed bean
	 */
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

	/**
	 * generates a spring jdbc template for global use in the application.
	 *
	 * @return a named parameter jdbc template
	 */
	@Bean("namedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		final NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		return namedParameterJdbcTemplate;
	}

}
