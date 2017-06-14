package com.webgis.web.dto;


import com.webgis.enums.ReportStateEnum;

import java.io.Serializable;

public class WebRepair implements Serializable {
    private String id;

    private Long specialId;
    private String layerId;
    private int mapId;
    private int userId;

    private String desc;
    private Long createDate;
    private ReportStateEnum state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Long specialId) {
        this.specialId = specialId;
    }

    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public ReportStateEnum getState() {
        return state;
    }

    public void setState(ReportStateEnum state) {
        this.state = state;
    }
}
