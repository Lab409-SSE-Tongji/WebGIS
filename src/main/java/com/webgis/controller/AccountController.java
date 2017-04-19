package com.webgis.controller;

import com.webgis.security.AuthenticationRequest;
import com.webgis.service.AccountService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 用户注册
     * @param webAccount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseResult<Object> register(@RequestBody WebAccount webAccount) {
        return accountService.register(webAccount);
    }

    /**
     * 用户登录
     * @param authenticationRequest
     * @return
     * @throws AuthenticationException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String auth(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Login successfully";
    }

    /**
     * 删除用户
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult<Object> deleteAccount(@RequestParam("username") String userName){
        return accountService.deleteAccount(userName);
    }

    /**
     * 更新用户信息
     * @param webAccount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult<Object> update(@RequestBody WebAccount webAccount) {
        return accountService.update(webAccount);
    }
}
