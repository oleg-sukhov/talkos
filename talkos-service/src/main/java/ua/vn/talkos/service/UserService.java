package ua.vn.talkos.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.vn.talkos.dto.UserDto;
import ua.vn.talkos.entity.User;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface UserService extends UserDetailsService {
    List<User> loadAll();
    User loadByLogin(String username);
    void save(UserDto userDto);
}
