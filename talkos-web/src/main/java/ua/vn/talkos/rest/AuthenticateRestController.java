package ua.vn.talkos.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.vn.talkos.service.UserService;

import javax.annotation.Resource;

/**
 * @author oleg.sukhov
 */
@RestController
public class AuthenticateRestController {

    @Resource
    private UserService service;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public void authenticate(@PathVariable String username, @PathVariable String password) {
        System.out.println("User -> <" + username +">, password -> <" + password + ">");
    }
}
