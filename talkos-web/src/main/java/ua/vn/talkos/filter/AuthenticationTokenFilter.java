package ua.vn.talkos.filter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import ua.vn.talkos.util.AuthenticationTokenUtils;
import ua.vn.talkos.service.UserService;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author oleg.sukhov
 */
public class AuthenticationTokenFilter extends GenericFilterBean {

    public static final String AUTH_TOKEN_HEADER_NAME = "auth-token";

    @Resource
    private UserService userService;

    private AuthenticationTokenUtils authTokenUtils;

    public AuthenticationTokenFilter() {
        authTokenUtils = new AuthenticationTokenUtils();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authToken = httpServletRequest.getHeader(AUTH_TOKEN_HEADER_NAME);

        if(!StringUtils.isEmpty(authToken)) {
            String username = authTokenUtils.getUserNameFromAuthToken(authToken);

            UserDetails userDetails = userService.loadUserByUserName(username);



        }

        chain.doFilter(request, response);
    }
}
