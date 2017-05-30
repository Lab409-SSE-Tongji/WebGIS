package com.webgis.web.dto;

import com.webgis.enums.TypeEnum;

/**
 * Created by Justin on 2017/5/30.
 */

public class WebLayerType{

    private String id;
    private TypeEnum type;

    public WebLayerType(String id, TypeEnum type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }
}
