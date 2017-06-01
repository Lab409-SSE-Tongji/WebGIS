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
     * 根据账户ID获取所有创建地图
     * @param accountId
     * @return
     */
    BaseResult<Object> getMapByAccountId(int accountId);

    /**
     * 根据地图ID获取历史地图ID和DATE
     * @param mapId
     * @return
     */
    BaseResult<Object> getHistoryIdandDateByMapId(int mapId);

    /**
     * 根据账户ID及当前文件夹ID获取所有创建地图
     * @param accountId
     * @param folderId
     * @return
     */
    BaseResult<Object> getMapByAccountIdandFolderId(int accountId, int folderId);


    /**
     * 根据账户ID及当前文件夹ID及pageID分页获取所有创建地图
     * @param accountId
     * @param folderId
     * @param pageId
     * @return
     */
    BaseResult<Object> getMapByAccountIdandFolderIdandPageId(int accountId, int folderId, int pageId);

    BaseResult<Object> getLayerIdAndType(int mapId);
}
