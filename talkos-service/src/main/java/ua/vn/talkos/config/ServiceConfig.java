package ua.vn.talkos.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author oleg.sukhov
 */
@Configuration
@ComponentScan
@Import(RepositoryConfig.class)
public class ServiceConfig {
}
