package ua.vn.talkos.dto;

import lombok.Data;
import ua.vn.talkos.entity.Account;

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
}
