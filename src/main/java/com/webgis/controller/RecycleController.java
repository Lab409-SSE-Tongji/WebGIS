package com.webgis.controller;

import com.webgis.service.RecycleService;
import com.webgis.web.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 张志强
 * @version v0.1 2017/5/18.
 */
@CrossOrigin
@RestController
@RequestMapping("/recycle")
public class RecycleController {
    @Autowired
    private RecycleService recycleService;
    /**
     * 项目删除添加到回收站
     */
    @RequestMapping(value = "/recycles/id", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> addMapToStation(@RequestParam("mapId") int mapId){
           return recycleService.addMapToStation(mapId);
    }
    /**
     * 获取回收站内所有地图列表
     */
    @RequestMapping(value = "/recycles/accountId", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getMapList(@RequestParam("accountId") int accountId){
       return recycleService.getMapList(accountId);
    }
    /**
     * 在回收站里删除地图
     */
    @RequestMapping(value = "/recycles/id", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResult<Object> deleteMap(@RequestParam("mapId") int mapId){
        return recycleService.deleteMap(mapId);
    }
    /**
     * 在回收站中恢复地图
     */
    @RequestMapping(value = "/recycles/mapid", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> recoverMap(@RequestParam("mapId") int mapId){
        return recycleService.recoverMap(mapId);
    }
}
