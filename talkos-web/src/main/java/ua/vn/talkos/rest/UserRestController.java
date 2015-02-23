package ua.vn.talkos.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.vn.talkos.entity.User;

/**
 * @author oleg.sukhov
 */
@RequestMapping("/user")
@RestController
public class UserRestController {

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void save(User user) {

    }
}
