package ua.vn.talkos.filter;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author oleg.sukhov
 */
@Component
public class CsrfCookiesFilter extends OncePerRequestFilter {
    public static final String XSRF_TOKEN_COOKIE_KEY = "XSRF-TOKEN";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        if (csrfToken != null) {
            Cookie cookie = WebUtils.getCookie(request, XSRF_TOKEN_COOKIE_KEY);
            String token = csrfToken.getToken();

            if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                cookie = new Cookie(XSRF_TOKEN_COOKIE_KEY, token);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

        filterChain.doFilter(request, response);

    }
}
