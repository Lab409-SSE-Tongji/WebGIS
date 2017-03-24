package com.webgis.web.dto;

import java.io.Serializable;

/**
 * Created by Justin on 2017/3/22.
 */

public abstract class WebLayer implements Serializable{

    private String layerId;

    public WebLayer() {
    }

    public WebLayer(String layerId) {
        this.layerId = layerId;
    }

    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }
}
