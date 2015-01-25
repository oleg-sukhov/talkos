package ua.vn.talkos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ua.vn.talkos.entity.User;
import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
