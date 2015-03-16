package ua.vn.talkos.security;

import lombok.Data;
import org.springframework.http.HttpStatus;
import ua.vn.talkos.dto.AccountDto;
import ua.vn.talkos.entity.Account;

/**
 * @author oleg.sukhov
 */
@Data
public class AuthenticationJsonResponse {
    public boolean authenticated;
    public HttpStatus httpStatus;
    public AccountDto loggedAccount;

    public AuthenticationJsonResponse(boolean authenticated, HttpStatus httpStatus, AccountDto loggedAccount) {
        this.authenticated = authenticated;
        this.httpStatus = httpStatus;
        this.loggedAccount = loggedAccount;
    }

    public static AuthenticationJsonResponse create(boolean authenticated, HttpStatus httpStatus, AccountDto accountDto) {
        return new AuthenticationJsonResponse(authenticated, httpStatus, accountDto);
    }
}
