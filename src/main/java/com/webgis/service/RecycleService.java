package com.webgis.service;

import com.webgis.web.BaseResult;

/**
 * @author 张志强
 * @version v0.1 2017/5/18.
 */
public interface RecycleService {
    /**
     * 将地图添加到回收站
     */
    BaseResult<Object> addMapToStation(int mapId);

    /**
     * 根据账户ID获取回收站内所有地图
     * @param accountId
     * @return
     */
    BaseResult<Object> getMapList(int accountId);
    /**
     * 在回收站中删除地图
     */
    BaseResult<Object> deleteMap(int mapId);
    /**
     * 恢复回收站信息
     */
    BaseResult<Object> recoverMap(int mapId);
}
