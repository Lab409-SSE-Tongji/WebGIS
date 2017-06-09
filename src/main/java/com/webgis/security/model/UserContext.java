package com.webgis.security.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CCMEOW on 2017/5/11.
 */
public class UserContext implements Serializable {
    private final String username;
    private final String role;
    private final String company;
    private final List<GrantedAuthority> authorities;

    public UserContext(String username,String role,String company, List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank: " + username);
        this.username = username;
        this.role=role;
        this.company=company;
        this.authorities = authorities;
    }

    public UserContext(String username, List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank: " + username);
        this.username = username;
        this.role=null;
        this.company=null;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public String getRole() {
        return role;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString(){
        return "{\n" +
                "\t\"id\":"+username+",\n" +
                "\t\"role\":\""+role+"\",\n" +
                "\t\"company\":"+company+"\n" +
                "}";
    }
}

