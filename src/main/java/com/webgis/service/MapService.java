package com.webgis.service;

import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebMapInfo;

/**
 * Created by Justin on 2017/3/9.
 * 地图相关服务接口
 */

public interface MapService {

    /**
     * 新建地图接口
     */
    BaseResult<Object> addMap(WebMapInfo webMapInfo);

    /**
     * 更新地图信息
     * @param webMapInfo
     * @return
     */
    BaseResult<Object> updateMap(WebMapInfo webMapInfo);

    /**
     * 删除地图
     * @param mapId
     * @return
     */
    BaseResult<Object> deleteMap(int mapId);

    /**
     * 获取地图
     * @param mapId
     * @return
     */
    BaseResult<Object> getMap(int mapId);


    /**
     * 根据用户账户获取对应的获取地图
     * @param accountID
     * @return
     */
    BaseResult<Object> getMapByAccountID(int accountID);
}
