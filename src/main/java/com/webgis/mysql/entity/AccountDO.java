package com.webgis.mysql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webgis.web.dto.WebAccount;

import java.sql.Timestamp;

/**
 * Created by Justin on 2017/3/8.
 * account表 数据库对象
 */

public class AccountDO extends BaseDO {

    private String name;
    private String username;
    @JsonIgnore private String password;
    @JsonIgnore private String role;
    private String company;
    private Integer super_admin_id;

    public AccountDO(WebAccount webAccount) {
        setName(webAccount.getName());
        setUsername(webAccount.getUsername());
        setPassword(webAccount.getPassword());
    }

    public AccountDO(WebAccount webAccount,String role) {
        setName(webAccount.getName());
        setUsername(webAccount.getUsername());
        setPassword(webAccount.getPassword());
        setRole(role);
        setCompany(webAccount.getCompany());
        setSuper_admin_id(webAccount.getSuperAdminId());
    }

    public AccountDO() {

    }

    public AccountDO(Integer id, Timestamp create_time, Timestamp update_time, String name, String username, String password) {
        super(id, create_time, update_time);
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSuper_admin_id(Integer super_admin_id) {
        this.super_admin_id = super_admin_id;
    }


    public Integer getSuper_admin_id() {
        return super_admin_id;
    }

    @Override
    public String toString() {
        return "AccountDO{" +
                "name='" + getName() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
