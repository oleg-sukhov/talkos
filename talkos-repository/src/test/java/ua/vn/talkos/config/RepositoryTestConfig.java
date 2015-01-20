package ua.vn.talkos.config;

import org.dbunit.DataSourceDatabaseTester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * @author oleg.sukhov
 */
@Configuration
public class RepositoryTestConfig extends RepositoryConfig {

    @Bean
    @Override
    public EmbeddedDatabase dataSource() {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        databaseBuilder.addScript("classpath:schema.sql");
        databaseBuilder.setName("test");
        databaseBuilder.setType(EmbeddedDatabaseType.H2);
        return databaseBuilder.build();
    }

    @Bean
    public DataSourceDatabaseTester databaseTester() {
        return new DataSourceDatabaseTester(dataSource());
    }
}
