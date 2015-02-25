package ua.vn.talkos.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.vn.talkos.entity.User;

/**
 * @author oleg.sukhov
 */
@RestController
public class UserRestController {



    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void save(@RequestBody User user) {
        System.out.println(123);

    }
}
