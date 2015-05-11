package ua.vn.talkos.security.urlchecker;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author oleg.sukhov
 */
@Component
public class InitializeUrlChecker implements UrlCheckerChain {
    public static final String INITIALIZE_URI = "/WEB-INF/views/index.jsp";

    @Resource(name = "registerUserUrlChecker")
    private UrlCheckerChain nextChecker;

    @Override
    public boolean checkUrl(String url) {
        return !INITIALIZE_URI.equals(url) && nextChecker.checkUrl(url);
    }
}
