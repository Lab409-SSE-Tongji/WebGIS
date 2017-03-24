package com.webgis.web.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Justin on 2017/3/23.
 * web history data transfer object
 */

public class WebHistory implements Serializable {
    private int mapId;
    private String description;

    public WebHistory() {
    }

    public WebHistory(int mapId, String description, Date createTime) {
        this.mapId = mapId;
        this.description = description;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
