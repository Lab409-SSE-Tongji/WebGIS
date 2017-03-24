package com.webgis.web.dto;

import java.io.Serializable;

/**
 * Created by Justin on 2017/3/8.
 * map信息相关 数据传输对象
 */

public class WebMapInfo implements Serializable {
    private int id;
    private int accountId;
    private String name;
    private String description;

    public WebMapInfo() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
