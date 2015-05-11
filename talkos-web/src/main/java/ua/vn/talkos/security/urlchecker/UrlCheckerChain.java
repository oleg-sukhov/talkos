package ua.vn.talkos.security.urlchecker;

/**
 * @author oleg.sukhov
 */
public interface UrlCheckerChain {
    boolean checkUrl(String url);
}
