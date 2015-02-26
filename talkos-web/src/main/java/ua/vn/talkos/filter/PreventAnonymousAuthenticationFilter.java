package ua.vn.talkos.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ua.vn.talkos.security.AuthenticationJsonResponse;
import ua.vn.talkos.security.urlchecker.UrlCheckerChain;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author oleg.sukhov
 */
@Component
public class PreventAnonymousAuthenticationFilter extends GenericFilterBean {

    @Resource
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Resource(name = "jsonMapper")
    private ObjectMapper jsonMapper = new ObjectMapper();

    @Resource(name = "initializeUrlChecker")
    private UrlCheckerChain urlChecker;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String url = httpRequest.getRequestURI();

        if(urlChecker.checkUrl(url) && authenticationTrustResolver.isAnonymous(authentication)) {
            httpResponse.setStatus(OK.value());
            AuthenticationJsonResponse jsonResponse = new AuthenticationJsonResponse(false, UNAUTHORIZED);
            httpResponse.getOutputStream().write(jsonMapper.writeValueAsBytes(jsonResponse));
            return;
        }

        chain.doFilter(request, response);
    }
}
