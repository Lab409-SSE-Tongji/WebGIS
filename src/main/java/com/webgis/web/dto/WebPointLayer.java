package com.webgis.web.dto;

import com.webgis.domain.cover.CommonCoverDomain;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;

/**
 * Created by Justin on 2017/3/21.
 * Point 类型 Layer 相关 data transfer object
 */

public class WebPointLayer extends WebLayer {

    private CommonCoverDomain data;

    public WebPointLayer() {
    }

    public WebPointLayer(String layerId, CommonCoverDomain data) {
        super(layerId);
        this.data = data;
    }

    public CommonCoverDomain getData() {
        return data;
    }

    public void setData(CommonCoverDomain data) {
        this.data = data;
    }
}
