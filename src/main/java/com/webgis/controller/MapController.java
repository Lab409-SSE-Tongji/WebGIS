package com.webgis.controller;

import com.webgis.mysql.entity.MapDO;
import com.webgis.service.MapService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebMapInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 2017/3/8.
 * 地图相关web接口
 */

@CrossOrigin
@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    private MapService mapService;

    /**
     * 新建地图
     * @param webMapInfo
     * @return
     */
    @RequestMapping(value = "/maps", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> addMap(@RequestBody WebMapInfo webMapInfo) {
        return mapService.addMap(webMapInfo);
    }

    /**
     * 删除地图
     * @param mapId
     * @return
     */
    @RequestMapping(value = "/maps/id", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResult<Object> deleteMap(@RequestParam("mapId") int mapId) {
        return mapService.deleteMap(mapId);
    }

    /**
     * 更新地图
     * @param webMapInfo
     * @return
     */
    @RequestMapping(value = "/maps/id", method = RequestMethod.PATCH)
    @ResponseBody
    public BaseResult<Object> updateMap(@RequestBody WebMapInfo webMapInfo) {
        return mapService.updateMap(webMapInfo);
    }

    /**
     * 获取地图信息
     * @param mapId
     * @return
     */
    @RequestMapping(value = "/maps/id", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getMap(@RequestParam("mapId") int mapId) {
        return mapService.getMap(mapId);
    }

    /**
     * 根据账户ID获取所有创建地图
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/maps/accountid", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getMapByAccountId(@RequestParam("accountId") int accountId) {
        return mapService.getMapByAccountId(accountId);
    }

    /**
     * 根据地图ID获取历史地图ID和DATE
     * @param mapId
     * @return
     */
    @RequestMapping(value = "/maps/mapid", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getHistoryIdByMapId(@RequestParam("mapId") int mapId) {
        return mapService.getHistoryIdandDateByMapId(mapId);
    }

    /**
     * 根据账户ID及当前文件夹ID获取所有创建地图
     * @param accountId
     * @param folderId
     * @return
     */
    @RequestMapping(value = "/maps/accountidandfolderid", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getMapByAccountIdandFolderId(@RequestParam("accountId") int accountId, @Param("folderId") int folderId) {
        return mapService.getMapByAccountIdandFolderId(accountId, folderId);
    }

    /**
     * 根据账户ID及当前文件夹ID及pageID分页获取所有创建地图
     * @param accountId
     * @param folderId
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/maps/accountidandfolderidandpageid", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getMapByAccountIdandFolderIdandPageId(int accountId, int folderId ,int pageId) {
        return mapService.getMapByAccountIdandFolderIdandPageId(accountId, folderId, pageId);
    }

    /**
     * 根据mapId获取图层Id和对应类型
     */
    @RequestMapping(value = "/maps/layerId...type", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getLayerIdAndType(@RequestParam("mapId") int mapId) {
        return mapService.getLayerIdAndType(mapId);
    }

    /**
     * 获取管理员管理的地图
     * @param adminId
     * @return
     */
    @RequestMapping(value="/maps/admin",method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getMapByAdminId(@RequestParam("adminId") Integer adminId){
        return mapService.getMapByAdminId(adminId);
    }


}
