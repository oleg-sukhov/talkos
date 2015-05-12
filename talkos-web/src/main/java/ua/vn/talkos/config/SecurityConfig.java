package ua.vn.talkos.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.SpringProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import ua.vn.talkos.filter.CsrfCookiesFilter;
import ua.vn.talkos.security.AuthenticationErrorHandler;
import ua.vn.talkos.service.AccountService;

import javax.annotation.Resource;
import javax.servlet.Filter;

/**
 * @author oleg.sukhov
 */
@Configuration
@EnableWebMvcSecurity
@Import(WebConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    @Resource
    private ApplicationContext applicationContext;

    @Autowired
    public void configureAuthenticationManagerBuilder(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((AccountService)applicationContext.getBean("accountServiceImpl"))
                .passwordEncoder((PasswordEncoder) applicationContext.getBean("passwordEncoder"));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                    .csrf().csrfTokenRepository(csrfTokenRepository())
                .and()
                    .addFilterAfter((Filter) applicationContext.getBean("csrfCookiesFilter"), CsrfFilter.class)
                    //.addFilterAfter((Filter) applicationContext.getBean("preventAnonymousAuthenticationFilter"), CsrfCookiesFilter.class)

                .formLogin()
                    .failureHandler(new AuthenticationErrorHandler())
                    .usernameParameter(USERNAME_KEY)
                    .passwordParameter(PASSWORD_KEY)
                .and().csrf().disable()
                    .logout()
                .and();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Bean(name = "jsonMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public AuthenticationTrustResolver authenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

}
