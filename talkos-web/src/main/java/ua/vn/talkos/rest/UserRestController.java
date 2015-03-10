package ua.vn.talkos.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.vn.talkos.dto.UserDto;
import ua.vn.talkos.service.UserService;

import javax.annotation.Resource;

/**
 * @author oleg.sukhov
 */
@RestController
public class UserRestController {

    @Resource
    private UserService userService;

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void save(@RequestBody UserDto userDto) {
        userService.save(userDto);
    }
}
