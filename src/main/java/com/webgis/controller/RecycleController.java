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
    @RequestMapping(value = "/recycles/{mapId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> addMapToStation( @PathVariable("mapId") int mapId){
      // return recycleService.addMapToStation(Integer.getInteger(mapId));
        return recycleService.addMapToStation(mapId);
    }
    /**
     * 获取回收站内所有地图列表
     */
    @RequestMapping(value = "/recycles/{accountId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getMapList(@PathVariable("accountId") int accountId){
       return recycleService.getMapList(accountId);
    }
    /**
     * 在回收站里删除地图
     */
    @RequestMapping(value = "/recycles/{mapId}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResult<Object> deleteMap(@PathVariable("mapId") int mapId){
        return recycleService.deleteMap(mapId);
    }
    /**
     * 在回收站中恢复地图
     */
    @RequestMapping(value = "/recover/{mapId}", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> recoverMap(@PathVariable("mapId") int mapId){
        return recycleService.recoverMap(mapId);
    }

    @RequestMapping(value="/test/{mapId}",method = RequestMethod.GET)
    public void test(@PathVariable("mapId") Integer mapId){System.out.println("test"+mapId);}
}
