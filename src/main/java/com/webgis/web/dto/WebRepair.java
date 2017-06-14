package com.webgis.web.dto;

import com.webgis.enums.ReportStateEnum;

/**
 * Created by CCMEOW on 2017/6/14.
 */
public class WebRepair {

    private String id;
    private Long specialId;
    private String layerId;
    private int userId;

    private String desc;
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

    public ReportStateEnum getState() {
        return state;
    }

    public void setState(ReportStateEnum state) {
        this.state = state;
    }

}
