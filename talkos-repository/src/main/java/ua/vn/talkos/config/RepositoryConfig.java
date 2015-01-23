package ua.vn.talkos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author oleg.sukhov
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableJpaRepositories("ua.vn.talkos.repository")
@PropertySource(value = "classpath*:ua/vn/talkos/configuration/db.properties", ignoreResourceNotFound = true)
public class RepositoryConfig {
    public static final String DB_DRIVER_KEY = "database.connection.driver";
    public static final String DB_URL_KEY = "database.connection.url";
    public static final String DB_USERNAME_KEY = "database.connection.username";
    public static final String DB_PASSWORD_KEY = "database.connection.password";

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"/*env.getRequiredProperty(DB_DRIVER_KEY)*/);
        dataSource.setUrl("jdbc:postgresql://localhost:5432/talkos"/*env.getRequiredProperty(DB_URL_KEY)*/);
        dataSource.setUsername("os"/*env.getRequiredProperty(DB_USERNAME_KEY)*/);
        dataSource.setPassword("satellite"/*env.getRequiredProperty(DB_PASSWORD_KEY)*/);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("ua.vn.talkos.entity");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(jpaProperties());
        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("eclipselink.weaving", "static");
        return properties;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
