package ua.vn.talkos.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.vn.talkos.dto.AccountDto;
import ua.vn.talkos.entity.Account;

import java.util.List;

/**
 * @author oleg.sukhov
 */
public interface AccountService extends UserDetailsService {
    List<Account> loadAll();
    Account loadByLogin(String username);
    void save(AccountDto accountDto);
}
