package com.webgis.mysql.entity;

import com.webgis.web.dto.WebPage;

import java.sql.Timestamp;

/**
 * Created by dell- on 2017/5/8.
 */
public class PageDO extends BaseDO {
    private Integer account_id;
    private String name;
    private Integer previous_page;

    public PageDO() {

    }

    public PageDO (WebPage webPage) {
        this.id = webPage.getId();
        this.account_id = webPage.getAccountId();
        this.name = webPage.getName();
        this.previous_page = webPage.getPrevious_page();
    }

    public PageDO(Integer account_id, String name, Integer previous_page) {
        this.account_id = account_id;
        this.name = name;
        this.previous_page = previous_page;
    }

    public PageDO(Integer id, Timestamp create_time, Timestamp update_time, Integer account_id, String name, Integer previous_page) {
        super(id, create_time, update_time);
        this.account_id = account_id;
        this.name = name;
        this.previous_page=previous_page;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrevious_page() {
        return previous_page;
    }

    public void setDescription(Integer previous_page) {
        this.previous_page = previous_page;
    }
}
