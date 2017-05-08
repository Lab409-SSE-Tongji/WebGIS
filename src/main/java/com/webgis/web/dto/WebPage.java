package com.webgis.web.dto;

/**
 * Created by dell- on 2017/5/8.
 */
public class WebPage {
    private int id;
    private int accountId;
    private String name;
    private Integer previous_page;

    public WebPage() {
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

    public Integer getPrevious_page() {return previous_page; }

    public void setPrevious_page(Integer previous_page) { this.previous_page=previous_page; }
}
