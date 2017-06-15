package com.webgis.web.dto;

import com.webgis.domain.lamp.CommonLampDomain;

/**
 * Created by CCMEOW on 2017/6/16.
 */
public class WebLampLayer  extends WebLayer {
    private CommonLampDomain data;

    public WebLampLayer() {
    }

    public WebLampLayer(String layerId, CommonLampDomain data) {
        super(layerId);
        this.data = data;
    }

    public CommonLampDomain getData() {
        return data;
    }

    public void setData(CommonLampDomain data) {
        this.data = data;
    }
}
