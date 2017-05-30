package com.webgis.security.jwt;

import com.webgis.security.SecurityConfiguration;
import com.webgis.security.config.JwtSettings;
import com.webgis.security.jwt.JwtAuthenticationToken;
import com.webgis.security.jwt.extractor.TokenExtractor;
import com.webgis.security.model.token.RawAccessJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;

/**
 * Created by CCMEOW on 2017/5/9.
 */
public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
    private final TokenExtractor tokenExtractor;

    @Autowired
    public JwtAuthenticationTokenFilter(TokenExtractor tokenExtractor, RequestMatcher matcher) {
        super(matcher);
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        System.out.println("token filter attemptAuthentication");
        String tokenPayload = request.getHeader(SecurityConfiguration.HEADER_TOKEN);
        RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }
}
