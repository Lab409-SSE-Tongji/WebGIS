package com.webgis.security;

import com.webgis.security.jwt.extractor.TokenExtractor;
import com.webgis.security.model.token.RawAccessJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by CCMEOW on 2017/5/9.
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final TokenExtractor tokenExtractor;

    @Autowired
    public JwtAuthenticationTokenFilter(TokenExtractor tokenExtractor) {
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public void doFilterInternal( HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain chain) throws ServletException, IOException{
        String tokenPayload = request.getHeader(SecurityConfiguration.HEADER_TOKEN);
        if(tokenPayload!=null){
            RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));
        }
        chain.doFilter(request,response);
    }
}
