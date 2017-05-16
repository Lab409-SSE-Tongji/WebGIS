package com.webgis.service.imp;

import com.webgis.mysql.entity.AccountDO;
import com.webgis.mysql.mapper.AccountMapper;
import com.webgis.security.WebGISUser;
import com.webgis.security.model.UserContext;
import com.webgis.security.model.token.JwtToken;
import com.webgis.security.model.token.JwtTokenFactory;
import com.webgis.service.AccountService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by Justin on 2017/3/8.
 * 用户相关服务实现
 */

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenFactory tokenFactory;

    /**
     * 用户注册
     * @param webAccount
     * @return
     */
    @Override
    public BaseResult<Object> register(WebAccount webAccount) {

        if (accountMapper.getAccountByUsername(webAccount.getUsername()) != null) {
            return new BaseResult<>(500, "用户已经存在");
        }
        accountMapper.insert(new AccountDO(webAccount));
        return new BaseResult<>();
    }

    /**
     * 更新用户信息
     * @param webAccount
     * @return
     */
    @Override
    public BaseResult<Object> update(WebAccount webAccount) {
        if (accountMapper.getAccountByUsername(webAccount.getUsername()) == null) {
            return new BaseResult<>(500, "该用户不存在");
        }
        accountMapper.update(new AccountDO(webAccount));
        return new BaseResult<>();
    }

    /**
     * 删除用户
     * @param userName
     * @return
     */
    @Override
    public BaseResult<Object> deleteAccount(String userName) {
        if (accountMapper.getAccountByUsername(userName) == null) {
            return new BaseResult<>(500, "该用户不存在");
        }
        accountMapper.deleteAccount(userName);
        return new BaseResult<>();
    }


}
