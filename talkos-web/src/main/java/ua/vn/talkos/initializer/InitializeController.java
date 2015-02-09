package ua.vn.talkos.initializer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author oleg.sukhov
 */
@Controller
public class InitializeController {
    @RequestMapping("/")
    public String initialize() {



        return "/WEB-INF/index.jsp";
    }
}
