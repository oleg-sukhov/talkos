package ua.vn.talkos.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author oleg.sukhov
 */
@Configuration
@ComponentScan
@MapperScan("ua/vn/talkos/persistence")
@PropertySource(value = "classpath*:ua/vn/talkos/configuration/db.properties")
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
        dataSource.setDriverClassName(env.getRequiredProperty(DB_DRIVER_KEY));
        dataSource.setUrl(env.getRequiredProperty(DB_URL_KEY));
        dataSource.setUsername(env.getRequiredProperty(DB_USERNAME_KEY));
        dataSource.setPassword(env.getRequiredProperty(DB_PASSWORD_KEY));
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("ua.vn.talkos.domain");
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
