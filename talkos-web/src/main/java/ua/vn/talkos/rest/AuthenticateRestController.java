package ua.vn.talkos.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
            return new AuthenticationJsonResponse(authOpt.get().isAuthenticated(), HttpStatus.OK);
        }
        return new AuthenticationJsonResponse(false, HttpStatus.UNAUTHORIZED);
    }
}
