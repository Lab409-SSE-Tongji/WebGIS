package com.webgis.mysql.entity;

import com.webgis.web.dto.WebMapInfo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Date;

/**
 * Created by Justin on 2017/3/9.
 * map表 数据库对象
 */

public class MapDO extends BaseDO {
    private Integer account_id;
    private String name;
    private Integer folder;
    private String description;
    private List<String> layerIds;
    private List<String> historyIds;
    private List<Date> historyDates;

    public MapDO() {

    }

    public MapDO (WebMapInfo webMapInfo) {
        this.id = webMapInfo.getId();
        this.account_id = webMapInfo.getAccountId();
        this.name = webMapInfo.getName();
        this.folder = webMapInfo.getFolder();
        this.description = webMapInfo.getDescription();
    }

    public MapDO(Integer account_id, String name, Integer folder, String description, List<String> layerIds, List<String> historyIds, List<Date> historyDates) {
        this.account_id = account_id;
        this.name = name;
        this.folder = folder;
        this.description = description;
        this.layerIds = layerIds;
        this.historyIds = historyIds;
        this.historyDates = historyDates;
    }

    public MapDO(Integer id, Timestamp create_time, Timestamp update_time, Integer account_id, String name, Integer folder, String description, List<String> layerIds, List<String> historyIds,  List<Date> historyDates) {
        super(id, create_time, update_time);
        this.account_id = account_id;
        this.name = name;
        this.folder = folder;
        this.description = description;
        this.layerIds = layerIds;
        this.historyIds = historyIds;
        this.historyDates = historyDates;
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

    public Integer getFolder() {
        return folder;
    }

    public void setFolder(Integer folder) {
        this.folder = folder;
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

    public List<String> getHistoryIds() {
        return historyIds;
    }

    public List<Date> getHistoryDates() {
        return historyDates;
    }

    public void setLayerIds(List<String> layerIds) {
        this.layerIds = layerIds;
    }

    public void setHistoryIds(List<String> historyIds) {
        this.historyIds = historyIds;
    }

    public void setHistoryDates(List<Date> historyDates) {
        this.historyDates = historyDates;
    }

}
