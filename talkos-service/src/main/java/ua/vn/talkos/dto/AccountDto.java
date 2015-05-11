package ua.vn.talkos.dto;

import lombok.Data;
import ua.vn.talkos.entity.Account;

import javax.accessibility.AccessibleAction;
import java.util.Optional;

/**
 * @author oleg.sukhov
 */
@Data
public class AccountDto implements BaseDto<Account> {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String avatarPath;
    private boolean enabled;

    public AccountDto() {
    }

    public AccountDto(Optional<Account> accountOpt) {
        if(accountOpt.isPresent()) {
            fromEntity(accountOpt.get());
        }
    }

    @Override
    public Account toEntity() {
        Account account = new Account();
        account.setLogin(login);
        account.setPassword(password);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setEmail(email);
        account.setAvatarPath(avatarPath);
        account.setEnabled(enabled);
        return account;
    }

    @Override
    public BaseDto fromEntity(Account account) {
        this.setLogin(account.getLogin());
        this.setFirstName(account.getFirstName());
        this.setLastName(account.getLastName());
        this.setEmail(account.getEmail());
        this.setAvatarPath(account.getAvatarPath());
        this.setEnabled(account.isEnabled());
        return this;
    }
}
