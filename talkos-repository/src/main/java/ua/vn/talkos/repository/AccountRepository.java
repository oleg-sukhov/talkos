package ua.vn.talkos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vn.talkos.entity.Account;

/**
 * @author oleg.sukhov
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByLogin(String login);
}
