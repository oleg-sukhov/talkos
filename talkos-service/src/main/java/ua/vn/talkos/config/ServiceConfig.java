package ua.vn.talkos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.vn.talkos.service.UserService;
import ua.vn.talkos.service.impl.UserServiceImpl;

/**
 * @author oleg.sukhov
 */
@Configuration
@ComponentScan
@Import(RepositoryConfig.class)
public class ServiceConfig {

}
