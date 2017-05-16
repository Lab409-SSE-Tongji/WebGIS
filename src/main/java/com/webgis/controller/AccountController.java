package com.webgis.controller;

import com.webgis.security.AuthenticationRequest;
import com.webgis.security.SecurityConfiguration;
import com.webgis.security.model.UserContext;
import com.webgis.security.model.token.JwtToken;
import com.webgis.security.model.token.JwtTokenFactory;
import com.webgis.service.AccountService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Justin on 2017/3/8.
 * 用户相关web接口
 */

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenFactory tokenFactory;
    /**
     * 用户注册
     * @param webAccount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public BaseResult<Object> register(@RequestBody WebAccount webAccount) {
        return accountService.register(webAccount);
    }

//    /**
//     * 用户登录
//     * @param authenticationRequest
//     * @return
//     * @throws AuthenticationException
//     */
//    @RequestMapping(value = "/token", method = RequestMethod.POST)
//    public String auth(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response) throws AuthenticationException {
//        // Perform the security
//        System.out.println("login controller");
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authenticationRequest.getUsername(),
//                        authenticationRequest.getPassword()
//                )
//        );
//
//        System.out.println("authenticate in controller");
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        JwtToken jwtToken =  accountService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword());
//        System.out.println("generate jwtToken");
//        response.setStatus(HttpStatus.OK.value());
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setHeader(SecurityConfiguration.HEADER_TOKEN, jwtToken.getToken());
//        return "Login successfully";
//    }

    /**
     * 删除用户
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/accounts/id", method = RequestMethod.DELETE)
    public BaseResult<Object> deleteAccount(@RequestParam("username") String userName){
        return accountService.deleteAccount(userName);
    }

    /**
     * 更新用户信息
     * @param webAccount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/accounts/id", method = RequestMethod.PATCH)
    public BaseResult<Object> update(@RequestBody WebAccount webAccount) {

        return accountService.update(webAccount);
    }

    private void test(){
        System.out.println("test controller");
    }
}
