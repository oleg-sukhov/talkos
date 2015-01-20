package ua.vn.talkos.service;

import ua.vn.talkos.domain.User;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface UserService {
    public List<User> loadUsers();
}
