package com.peiwc.billing.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * this bean contains all the configuration managed across the current
 * application.
 */
@Configuration
@ComponentScans({ @ComponentScan("com.peiwc.billing") })
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
         * Defines the algorithm and reads system property for encryption.
         * @return 
         */
    @Bean
    public static EnvironmentStringPBEConfig environmentVariablesConfiguration() {
        EnvironmentStringPBEConfig environmentVariablesConfiguration = new EnvironmentStringPBEConfig();
        environmentVariablesConfiguration.setAlgorithm("PBEWithMD5AndDES");
        environmentVariablesConfiguration.setPasswordSysPropertyName("jasypt.encryptor.password");
        return environmentVariablesConfiguration;
    }

    /**
     * Reads the String Encryptor.
     * @return 
     */
    @Bean
    public static StringEncryptor configurationEncryptor() {
        StandardPBEStringEncryptor configurationEncryptor = new StandardPBEStringEncryptor();
        configurationEncryptor.setConfig(environmentVariablesConfiguration());
        return configurationEncryptor;
    }

    /**
     * Defines the environment and encryptable property placeholder.
     * @return 
     */
    @Bean
    public static PropertyPlaceholderConfigurer propertyConfigurer() {
        String envPrefix = System.getProperty("env.prefix");
        EncryptablePropertyPlaceholderConfigurer propertyConfigurer = new EncryptablePropertyPlaceholderConfigurer(configurationEncryptor());
        propertyConfigurer.setLocations(new ClassPathResource("database.mssql-"+envPrefix+".properties"), new ClassPathResource("common.properties"));
        return propertyConfigurer;
    }
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
		dataSource.setDefaultAutoCommit(true);
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
