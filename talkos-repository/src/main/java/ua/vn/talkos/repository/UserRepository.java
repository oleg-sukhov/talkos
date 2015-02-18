package ua.vn.talkos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vn.talkos.entity.User;

/**
 * @author oleg.sukhov
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
