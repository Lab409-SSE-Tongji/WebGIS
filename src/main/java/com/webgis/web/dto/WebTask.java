package com.webgis.web.dto;

import com.webgis.mongo.entity.MongoTask;
import com.webgis.utils.DateUtil;

import java.io.Serializable;
import java.text.ParseException;

/**
 * Created by CCMEOW on 2017/6/5.
 */
public class WebTask implements Serializable {

    private String id;
    private int creatorId;
    private double lat;
    private double lng;
    private String addressDesc;
    private String taskDesc;
    private double value;
    private Long createDate;

    public WebTask() {
    }

    public WebTask(MongoTask mongoTask) throws ParseException {
        this.creatorId = mongoTask.getCreatorId();
        this.lat = mongoTask.getLat();
        this.lng = mongoTask.getLng();
        this.addressDesc = mongoTask.getAddressDesc();
        this.taskDesc = mongoTask.getTaskDesc();
        this.value = mongoTask.getValue();
        this.createDate = DateUtil.timestampToLong(mongoTask.getCreateTime());
        this.id = mongoTask.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

}
