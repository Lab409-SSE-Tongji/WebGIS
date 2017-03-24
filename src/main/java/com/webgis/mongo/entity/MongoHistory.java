package com.webgis.mongo.entity;

import com.webgis.domain.base.BaseDomain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Justin on 2017/3/14.
 *
 * Mongo数据库 history 对象
 *
 */

/* 指定collection名称 */
@Document(collection = "history")
public class MongoHistory {

    @Id
    private String id;

    private String description;
    private List<BaseDomain> data;

    public MongoHistory() {
    }

    public MongoHistory(String description, List<BaseDomain> data) {
        this.description = description;
        this.data = data;
    }

    public MongoHistory(String id, String description, List<BaseDomain> data) {

        this.id = id;
        this.description = description;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BaseDomain> getData() {
        return data;
    }

    public void setData(List<BaseDomain> data) {
        this.data = data;
    }
}
