package ua.vn.talkos.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oleg.sukhov
 */
@RestController
public class AuthenticateController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String test() {
        System.out.println("hello");
        return "hello";
    }
}
