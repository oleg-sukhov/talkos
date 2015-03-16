package ua.vn.talkos.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.vn.talkos.dto.AccountDto;
import ua.vn.talkos.entity.Account;
import ua.vn.talkos.security.AuthenticationJsonResponse;

import java.util.Optional;

/**
 * @author oleg.sukhov
 */
@RestController
public class AuthenticateRestController {

    @RequestMapping(value = "/isAuthenticated",
            method = RequestMethod.GET,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            headers="Content-Type=application/json"
    )
    public AuthenticationJsonResponse isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return createAuthenticationJsonResponse(Optional.ofNullable(authentication));
    }

    private AuthenticationJsonResponse createAuthenticationJsonResponse(Optional<Authentication> authOpt) {
        if(authOpt.isPresent()) {
            Authentication auth = authOpt.get();
            Optional<Account> accountOpt = Optional.ofNullable((Account) auth.getPrincipal());
            return AuthenticationJsonResponse.create(auth.isAuthenticated(), HttpStatus.OK, new AccountDto(accountOpt));
        }
        return AuthenticationJsonResponse.create(false, HttpStatus.UNAUTHORIZED, null);
    }
}
