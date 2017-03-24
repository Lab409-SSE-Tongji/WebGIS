package com.webgis.mysql.entity;

import com.webgis.web.dto.WebMapInfo;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Justin on 2017/3/9.
 * map表 数据库对象
 */

public class MapDO extends BaseDO {
    private Integer account_id;
    private String name;
    private String description;
    private List<String> layerIds;

    public MapDO() {

    }

    public MapDO (WebMapInfo webMapInfo) {
        this.id = webMapInfo.getId();
        this.account_id = webMapInfo.getAccountId();
        this.name = webMapInfo.getName();
        this.description = webMapInfo.getDescription();
    }

    public MapDO(Integer account_id, String name, String description, List<String> layerIds) {
        this.account_id = account_id;
        this.name = name;
        this.description = description;
        this.layerIds = layerIds;
    }

    public MapDO(Integer id, Timestamp create_time, Timestamp update_time, Integer account_id, String name, String description, List<String> layerIds) {
        super(id, create_time, update_time);
        this.account_id = account_id;
        this.name = name;
        this.description = description;
        this.layerIds = layerIds;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLayerIds() {
        return layerIds;
    }

    public void setLayerIds(List<String> layerIds) {
        this.layerIds = layerIds;
    }
}
