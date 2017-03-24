package com.webgis.web.dto;

import com.webgis.mongo.entity.MongoLayer;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Justin on 2017/3/21.
 *
 * map 内容相关 data transfer object
 *
 */

public class WebMapContent implements Serializable{

    private int mapId;
    private List<MongoLayer> layerList;

    public WebMapContent() {
    }

    public WebMapContent(int mapId, List layerList) {
        this.mapId = mapId;
        this.layerList = layerList;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public List getLayerList() {
        return layerList;
    }

    public void setLayerList(List layerList) {
        this.layerList = layerList;
    }
}
