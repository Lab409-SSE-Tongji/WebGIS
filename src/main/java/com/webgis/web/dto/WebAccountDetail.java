package com.webgis.web.dto;

import com.webgis.mysql.entity.AccountDO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;

/**
 * Created by CCMEOW on 2017/6/17.
 */
public class WebAccountDetail implements Serializable {
    private int id;
    private String name;
    private String username;
    private String company;
    private String role;
    private Integer superAdminId;
    private String superAdminName;

    public WebAccountDetail(AccountDO accountDO,String superAdminName){
        this.id=accountDO.getId();
        this.name = accountDO.getName();
        this.username = accountDO.getUsername();
        this.company = accountDO.getCompany();
        this.role = accountDO.getRole();
        this.superAdminId = accountDO.getSuper_admin_id();
        this.superAdminName = superAdminName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getSuperAdminId() {
        return superAdminId;
    }

    public void setSuperAdminId(int superAdminId) {
        this.superAdminId = superAdminId;
    }

    public void setSuperAdminId(Integer superAdminId) {
        this.superAdminId = superAdminId;
    }

    public String getSuperAdminName() {
        return superAdminName;
    }

    public void setSuperAdminName(String superAdminName) {
        this.superAdminName = superAdminName;
    }

}
