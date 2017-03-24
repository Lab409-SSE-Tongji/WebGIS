package com.webgis.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Justin on 2017/3/13.
 *
 * Mongo数据库 map 对象
 *
 */

/* 指定collection名称 */
@Document(collection = "map")
public class MongoMap {

    @Id
    private String id;

    @Indexed(unique = true) //声明该字段需要索引
    private Integer mapId;

    private List<String> layerIds;

    private List<Date> historyDates;

    private List<String> historyIds;

    public MongoMap() {
    }

    public MongoMap(Integer mapId) {
        this.mapId = mapId;
        this.layerIds = new ArrayList<>();
        this.historyDates = new ArrayList<>();
        this.historyIds = new ArrayList<>();
    }

    @PersistenceConstructor
    public MongoMap(Integer mapId, List<String> layerIds, List<Date> historyDates, List<String> historyIds) {
        this.mapId = mapId;
        this.layerIds = layerIds;
        this.historyDates = historyDates;
        this.historyIds = historyIds;
    }

    public MongoMap(String id, Integer mapId, List<String> layerIds, List<Date> historyDates, List<String> historyIds) {
        this.id = id;
        this.mapId = mapId;
        this.layerIds = layerIds;
        this.historyDates = historyDates;
        this.historyIds = historyIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMapId() {
        return mapId;
    }

    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    public List<String> getLayerIds() {
        return layerIds;
    }

    public void setLayerIds(List<String> layerIds) {
        this.layerIds = layerIds;
    }

    public List<Date> getHistoryDates() {
        return historyDates;
    }

    public void setHistoryDates(List<Date> historyDates) {
        this.historyDates = historyDates;
    }

    public List<String> getHistoryIds() {
        return historyIds;
    }

    public void setHistoryIds(List<String> historyIds) {
        this.historyIds = historyIds;
    }
}
