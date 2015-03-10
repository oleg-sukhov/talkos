package ua.vn.talkos.config;

import org.dbunit.DataSourceDatabaseTester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @author oleg.sukhov
 */
@Configuration
public class RepositoryTestConfig extends RepositoryConfig {
    public static final String SQL_MIGRATION_SCRIPTS_DIRECTORY = "ua/vn/talkos/testsql/";

    @Bean
    @Override
    public EmbeddedDatabase dataSource() throws IOException {
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        databaseBuilder.setName("talkos");
        databaseBuilder.setType(EmbeddedDatabaseType.H2);
        databaseBuilder.addScripts(loadMigrationScriptPaths());
        return databaseBuilder.build();
    }

    @Bean
    public DataSourceDatabaseTester databaseTester() throws IOException {
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

    private String[] loadMigrationScriptPaths() throws IOException {
        File scriptsDirectory = new ClassPathResource(SQL_MIGRATION_SCRIPTS_DIRECTORY).getFile();

        String[] scriptsPaths = Files.walk(Paths.get(scriptsDirectory.getPath()))
                .filter(path -> !Files.isDirectory(path))
                .map(path -> SQL_MIGRATION_SCRIPTS_DIRECTORY + path.getFileName())
                .sorted()
                .toArray(String[]::new);

        return scriptsPaths;
    }
}
