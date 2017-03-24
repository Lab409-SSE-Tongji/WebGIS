package com.webgis.web.dto;

import com.webgis.domain.cover.CommonCoverDomain;
import com.webgis.domain.pipe.CommonPipeDomain;

import java.io.Serializable;

/**
 * Created by Justin on 2017/3/21.
 * Point 类型 Layer 相关 data transfer object
 */

public class WebLineLayer extends WebLayer {

    private CommonPipeDomain data;

    public WebLineLayer() {
    }

    public WebLineLayer(String layerId, CommonPipeDomain data) {
        super(layerId);
        this.data = data;
    }

    public CommonPipeDomain getData() {
        return data;
    }

    public void setData(CommonPipeDomain data) {
        this.data = data;
    }
}
