package com.webgis.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.webgis.enums.TypeEnum;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebLayer;
import com.webgis.web.dto.WebLineLayer;
import com.webgis.web.dto.WebPointLayer;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Justin on 2017/3/16.
 *
 * 图层相关服务
 *
 */

public interface LayerService {


    /**
     * 新建图层
     */
    BaseResult<Object> addLayer(MultipartFile file, int MapId, TypeEnum type);

    /**
     * 更新图层
     */
    BaseResult<Object> updateLayer(WebLayer webLayer);

    /**
     * 删除图层
     */
    BaseResult<Object> deleteLayer(int mapId, String layerId);

    /**
     * 获取全部图层
     */
    BaseResult<Object> getLayer(int mapId);

    /**
     * 获取单个图层
     */
    BaseResult<Object> getLayer(String layerId);

    /**
     * 获取所有存在的地图
     */
    BaseResult<Object> getAllLayer();
}
