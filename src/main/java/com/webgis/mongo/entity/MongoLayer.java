package com.webgis.mongo.entity;

import com.webgis.domain.base.BaseDomain;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by Justin on 2017/3/21.
 *
 * Mongo layer collection object.
 *
 */

@Document(collection = "layer")
public class MongoLayer {

    @Id
    private String id;

    private BaseDomain data;
    private Date createTime;
    private Date updateTime;

    public MongoLayer() {
    }

    public MongoLayer(BaseDomain data, Date createTime, Date updateTime) {
        this.id = id;
        this.data = data;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @PersistenceConstructor
    public MongoLayer(String id, BaseDomain data, Date createTime, Date updateTime) {
        this.id = id;
        this.data = data;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaseDomain getData() {
        return data;
    }

    public void setData(BaseDomain data) {
        this.data = data;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
