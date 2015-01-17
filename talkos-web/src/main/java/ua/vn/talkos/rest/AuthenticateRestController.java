package ua.vn.talkos.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oleg.sukhov
 */
@Controller
public class AuthenticateRestController {

    @RequestMapping("/")
    public String test() {
        return "/WEB-INF/index.jsp";
    }

}
