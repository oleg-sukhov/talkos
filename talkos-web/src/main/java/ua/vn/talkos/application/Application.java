package ua.vn.talkos.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author oleg.sukhov
 */
@Controller
@ComponentScan("ua.vn.talkos")
@EnableAutoConfiguration
public class Application {
    @RequestMapping("/")
    public String init() {
        return "index.html";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
