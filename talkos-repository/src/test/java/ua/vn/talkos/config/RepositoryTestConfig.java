package ua.vn.talkos.config;

import org.dbunit.DataSourceDatabaseTester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.Properties;

/**
 * @author oleg.sukhov
 */
@Configuration
public class RepositoryTestConfig extends RepositoryConfig {

    @Bean
    @Override
    public EmbeddedDatabase dataSource() {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        databaseBuilder.setName("talkos");
        databaseBuilder.setType(EmbeddedDatabaseType.H2);
        return databaseBuilder.build();
    }

    @Bean
    public DataSourceDatabaseTester databaseTester() {
        return new DataSourceDatabaseTester(dataSource());
    }

    @Override
    protected Properties jpaProperties() {
        Properties jpaProps = new Properties();
        jpaProps.put("eclipselink.target-database", "org.eclipse.persistence.platform.database.H2Platform");
        jpaProps.put("eclipselink.weaving", "static");
        jpaProps.put("eclipselink.ddl-generation", "drop-and-create-tables");
        jpaProps.put("eclipselink.logging.level.sql", "FINE");
        jpaProps.put("eclipselink.logging.parameters", "true");
        return jpaProps;
    }
}
