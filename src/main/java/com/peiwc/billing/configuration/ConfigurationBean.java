package com.peiwc.billing.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * this bean contains all the configuration managed across the current
 * application.
 */
@Configuration
@ComponentScans({@ComponentScan("com.peiwc.billing")})
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {"com.peiwc.billing.dao"})
@EnableTransactionManagement
@PropertySources({@PropertySource("file:database.mssql.properties"), @PropertySource("file:common.properties")})
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
     * Generates a entity manager factory that scans the application beans to
     * search all JPA managed beans.
     *
     * @return an global entity manager factory
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
        factory.setPersistenceUnitName(ConfigurationBean.PERSISTENCE_APP_NAME);
        return factory;
    }

    /**
     * Generates a vendor adapter for hibernate that manages specific vendor
     * properties of hibernate connection.
     *
     * @return a global vendor adapter for hibernate.
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
     * constructs a transaction manager for use when generating a transaction in
     * a DAO repository or component.
     *
     * @return a global transaction manager for use in the application.
     */
    @Bean("transactionManager")
    public JpaTransactionManager setTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(getDataSource());
        return transactionManager;
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

    /**
     * generates an annotationdriven transaction manager
     *
     * @return datasource transaction manager.
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }

    /**
     * generates a transaction manager for use in @Transactional annotations
     *
     * @param entityManagerFactory entityManager Factory passed as parameter when building transaction manager method.
     * @return a transaction manager to use in jpa transactions.
     */
    @Bean
    JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
