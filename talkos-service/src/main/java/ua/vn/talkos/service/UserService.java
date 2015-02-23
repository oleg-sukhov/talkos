package ua.vn.talkos.service;

import ua.vn.talkos.entity.User;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface UserService {
    List<User> loadAll();
    User loadByLogin(String username);
    void save(User user);
}
