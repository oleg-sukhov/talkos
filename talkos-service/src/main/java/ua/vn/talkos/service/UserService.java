package ua.vn.talkos.service;

import ua.vn.talkos.entity.User;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface UserService {
    public List<User> loadUsers();
}
