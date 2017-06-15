package com.webgis.security.ajax;

import com.webgis.security.exceptions.JwtExpiredTokenException;
import com.webgis.web.BaseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by CCMEOW on 2017/6/9.
 */
@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException e) {
        PrintWriter out = null;
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            out = response.getWriter();
            if(e instanceof UsernameNotFoundException){
                out.append(new BaseResult<>(500, "User not found").toString());
            } else if (e instanceof BadCredentialsException) {
                out.append(new BaseResult<>(500, "Username or password invalid").toString());
            } else if (e instanceof JwtExpiredTokenException) {
                out.append(new BaseResult<>(500, "Token has expired").toString());
            }else {
                out.append(new BaseResult<>(500, "Unknown error").toString());
            }
        } catch (IOException ioe) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
