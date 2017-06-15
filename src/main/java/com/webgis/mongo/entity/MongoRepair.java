package com.webgis.mongo.entity;

import com.webgis.enums.ReportStateEnum;
import com.webgis.web.dto.WebRepair;
import com.webgis.web.dto.WebTask;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

@Document(collection = "repair")
public class MongoRepair {
    @Id
    private String id;

    private Long specialId;
    private String layerId;
    private int userId;
    private String url;
    private String desc;
    private Date createDate;
    private ReportStateEnum state;

    @PersistenceConstructor
    public MongoRepair(long specialId,String layerId,int userId,
                       String desc,String url,ReportStateEnum state){
        this.specialId = specialId;
        this.layerId = layerId;
        this.userId = userId;
        this.desc = desc;
        this.createDate = new Date();
        this.url=url;
        this.state = state;
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
