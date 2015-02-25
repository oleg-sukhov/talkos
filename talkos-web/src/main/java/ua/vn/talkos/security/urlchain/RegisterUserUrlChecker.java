package ua.vn.talkos.security.urlchain;

import org.springframework.stereotype.Component;

/**
 * @author osukhov
 */
@Component
public class RegisterUserUrlChecker implements UrlCheckerChain {
    public static final String REGISTER_USER_URI = "/register";

    @Override
    public boolean checkUrl(String url) {
        return !REGISTER_USER_URI.equals(url) ;
    }
}
