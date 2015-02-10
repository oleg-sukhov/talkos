package ua.vn.talkos.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ua.vn.talkos.security.AuthenticationJsonResponse;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author oleg.sukhov
 */
public class PreventAnonymousAuthenticationFilter extends GenericFilterBean {
    public static final String INITIALIZE_URI = "/";

    @Resource
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Resource(name = "jsonMapper")
    private ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(isNotInitializeUri(httpRequest) && authenticationTrustResolver.isAnonymous(authentication)) {
            httpResponse.setStatus(UNAUTHORIZED.value());
            AuthenticationJsonResponse jsonResponse = new AuthenticationJsonResponse(false, UNAUTHORIZED);
            httpResponse.getOutputStream().write(jsonMapper.writeValueAsBytes(jsonResponse));
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isNotInitializeUri(HttpServletRequest request) {
        return !INITIALIZE_URI.equals(request.getRequestURI());
    }
}
