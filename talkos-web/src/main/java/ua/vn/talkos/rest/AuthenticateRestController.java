package ua.vn.talkos.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.vn.talkos.service.UserService;

import javax.annotation.Resource;

/**
 * @author oleg.sukhov
 */
@Controller
public class AuthenticateRestController {

    @Resource
    private UserService service;

    @RequestMapping("/")
    public String test() {
        System.out.println(service.loadUsers());
        return "/WEB-INF/index.jsp";
    }

}
