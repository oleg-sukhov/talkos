package ua.vn.talkos.persistence;

import ua.vn.talkos.domain.User;
import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface UserRepository {
    public List<User> loadUsers();
}
