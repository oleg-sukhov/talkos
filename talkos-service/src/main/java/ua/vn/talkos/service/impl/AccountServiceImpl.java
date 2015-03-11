package ua.vn.talkos.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.vn.talkos.dto.AccountDto;
import ua.vn.talkos.entity.Account;
import ua.vn.talkos.repository.AccountRepository;
import ua.vn.talkos.service.AccountService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author oleg.sukhov
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AccountRepository accountRepository;

    @Override
    public List<Account> loadAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account loadByLogin(String login) {
        return accountRepository.findByLogin(login);
    }

    @Override
    public void save(AccountDto accountDto) {
        Optional<AccountDto> optionalUser = Optional.ofNullable(accountDto);

        if(!optionalUser.isPresent()) {
            throw new IllegalArgumentException("Account cannot be null");
        }

        Account account = accountDto.toEntity();
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadByLogin(username);
    }
}
