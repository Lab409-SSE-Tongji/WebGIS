package com.webgis.security;

import com.webgis.mysql.entity.AccountDO;
import com.webgis.mysql.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 谢天帝
 * @version v0.1 2017/3/17.
 */
@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDO accountDO = accountMapper.getAccountByUsername(username);

        if (accountDO == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            WebGISUser user = new WebGISUser(accountDO.getId(),accountDO.getName(),accountDO.getUsername(),accountDO.getPassword(),authorities,true,new Date());
            return user;
        }
    }
}
