package ua.vn.talkos.service;

import org.springframework.security.core.userdetails.UserDetails;
import ua.vn.talkos.entity.User;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface UserService {
    List<User> loadUsers();
    User loadUserByLogin(String username);
}
