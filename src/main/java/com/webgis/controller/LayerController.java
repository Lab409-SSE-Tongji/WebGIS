package com.webgis.controller;

import com.webgis.enums.TypeEnum;
import com.webgis.service.LayerService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebLineLayer;
import com.webgis.web.dto.WebPointLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Justin on 2017/3/16.
 *
 * 图层相关接口
 *
 */

@CrossOrigin
@RestController
@RequestMapping("/layer")
public class LayerController {

    @Autowired
    private LayerService layerService;

    /**
     * 新建图层接口
     * @param file
     * @param mapId
     * @param type
     * @return
     */
    @RequestMapping(value = "/layers", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> addLayer(@RequestParam(value = "file") MultipartFile file, @RequestParam("mapId") int mapId, @RequestParam("type") String type) {
        return layerService.addLayer(file, mapId, TypeEnum.getEnum(type));
    }

    /**
     * 新建空的图层接口
     * @param mapId
     * @param type
     * @return
     */
    @RequestMapping(value = "/emptyLayers", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> addEmptyLayer(@RequestParam("mapId") int mapId, @RequestParam("type") String type) {
        return layerService.addLayer(null, mapId, TypeEnum.getEnum(type));
    }

    /**
     * 更新点类图层接口
     * @param webPointLayer
     * @return
     */
    @RequestMapping(value = "/layers/point/id", method = RequestMethod.PATCH)
    @ResponseBody
    public BaseResult<Object> updatePointLayer(@RequestBody WebPointLayer webPointLayer) {
        return layerService.updateLayer(webPointLayer);
    }

    /**
     * 更新线类图层接口
     * @param webLineLayer
     * @return
     */
    @RequestMapping(value = "/layers/line/id", method = RequestMethod.PATCH)
    @ResponseBody
    public BaseResult<Object> updateLineLayer(@RequestBody WebLineLayer webLineLayer) {
        return layerService.updateLayer(webLineLayer);
    }

    /**
     * 删除图层接口
     * @param mapId
     * @param layerId
     * @return
     */
    @RequestMapping(value = "/layers/id", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseResult<Object> deleteLayer(@RequestParam("mapId") int mapId, @RequestParam("layerId") String layerId) {
        return layerService.deleteLayer(mapId, layerId);
    }

    /**
     * 获取图层数据接口
     * @param mapId
     * @return
     */
    @RequestMapping(value = "/layers", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getLayer(@RequestParam("mapId") int mapId) {
        return layerService.getLayer(mapId);
    }


    /**
     * 获取图层数据接口
     * @return
     */
    @RequestMapping(value = "/layers/all", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getAllLayer() {
        return layerService.getAllLayer();
    }


    /**
     * 获取单个图层数据接口
     * @param layerId
     * @return
     */
    @RequestMapping(value = "/layer", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getSingleLayer(@RequestParam("layerId") String layerId) {
        return layerService.getLayer(layerId);
    }

}
