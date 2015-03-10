package ua.vn.talkos.security;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author oleg.sukhov
 */
@Data
public class AuthenticationJsonResponse {
    public boolean authenticated;
    public HttpStatus httpStatus;

    public AuthenticationJsonResponse(boolean authenticated, HttpStatus httpStatus) {
        this.authenticated = authenticated;
        this.httpStatus = httpStatus;
    }
}
