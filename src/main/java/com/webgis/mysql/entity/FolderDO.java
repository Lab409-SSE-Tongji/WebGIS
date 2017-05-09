package com.webgis.mysql.entity;

import com.webgis.web.dto.WebFolder;

import java.sql.Timestamp;

/**
 * Created by cz on 2017/4/25.
 * folder信息相关 数据传输对象
 */

public class FolderDO extends BaseDO{
    private Integer account_id;
    private String name;
    private Integer upper_folder;


    public FolderDO() {

    }

    public FolderDO (WebFolder webFolder) {
        this.id = webFolder.getId();
        this.account_id = webFolder.getAccountId();
        this.name = webFolder.getName();
        this.upper_folder = webFolder.getUpper_folder();
    }

    public FolderDO(Integer account_id, String name, Integer upper_folder) {
        this.account_id = account_id;
        this.name = name;
        this.upper_folder = upper_folder;
    }

    public FolderDO(Integer id, Timestamp create_time, Timestamp update_time, Integer account_id, String name, Integer upper_folder) {
        super(id, create_time, update_time);
        this.account_id = account_id;
        this.name = name;
        this.upper_folder = upper_folder;
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

    public Integer getUpperFolder() {
        return upper_folder;
    }

    public void setDescription(Integer upper_folder) {
        this.upper_folder = upper_folder;
    }

}
