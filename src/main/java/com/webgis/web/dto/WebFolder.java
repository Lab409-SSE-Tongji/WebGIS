package com.webgis.web.dto;

import java.io.Serializable;

/**
 * Created by cz on 2017/4/25.
 */
public class WebFolder {
    private int id;
    private int accountId;
    private String name;
    private Integer upper_folder;

    public WebFolder() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUpper_folder() {return upper_folder; }

    public void setUpper_folder(Integer upper_folder) { this.upper_folder = upper_folder; }
}
