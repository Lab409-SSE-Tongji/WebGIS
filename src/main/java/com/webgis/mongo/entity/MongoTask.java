package com.webgis.mongo.entity;

import com.webgis.enums.TaskStateEnum;
import com.webgis.utils.DateUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;

/**
 * Created by CCMEOW on 2017/6/5.
 */
@Document(collection = "task")
public class MongoTask {

    @Id
    private String id;

    private double lat;
    private double lng;

    /**
     * 地址描述
     */
    private String addressDesc;

    /**
     * 任务描述
     */
    private String taskDesc;

    private String createTime;
    private String acceptTime;
    private String finishTime;
    private String payTime;
    private double value;
    private TaskStateEnum taskState;
    private int creatorId;
    private int accepterId;

    public MongoTask() {

    }

    @PersistenceConstructor
    public MongoTask(double lat,double lng,String addressDesc,String taskDesc,String createTime,double value,int creatorId ) throws ParseException {
        this.lat = lat;
        this.lng = lng;
        this.addressDesc = addressDesc;
        this.taskDesc = taskDesc;
        this.createTime = createTime;
        this.acceptTime = null;
        this.finishTime = null;
        this.payTime = null;
        this.value = value;
        this.taskState = TaskStateEnum.WAITING;
        this.creatorId=creatorId;
        this.accepterId=-1;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public TaskStateEnum getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskStateEnum taskState) {
        this.taskState = taskState;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getAccepterId() {
        return accepterId;
    }

    public void setAccepterId(int accepterId) {
        this.accepterId = accepterId;
    }


}
