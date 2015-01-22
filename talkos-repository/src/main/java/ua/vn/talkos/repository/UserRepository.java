package ua.vn.talkos.repository;

import ua.vn.talkos.entity.User;
import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface UserRepository {
    public List<User> loadUsers();
}
