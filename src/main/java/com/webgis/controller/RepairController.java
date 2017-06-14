package com.webgis.controller;

import com.webgis.enums.ReportStateEnum;
import com.webgis.service.RepairService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebRepair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

/**
 * Created by CCMEOW on 2017/6/14.
 */
@CrossOrigin
@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    RepairService repairService;

    /**
     * 新建报修
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/repair",method = RequestMethod.POST)
    public BaseResult<Object> createRepair(@RequestBody WebRepair webRepair){
        return repairService.addTask(webRepair);
    }

    @ResponseBody
    @RequestMapping(value = "/repair/state/{repairId}",method = RequestMethod.PATCH)
    public BaseResult<Object> updateRepair(@PathVariable("repairId") String repairId, @RequestParam String state){
        return repairService.changeState(repairId,state);
    }

    @ResponseBody
    @RequestMapping(value = "/repair/layer/{layerId}/{specialId}/{repairId}",method = RequestMethod.DELETE)
    public BaseResult<Object> deleteRepairFromLayer(@PathVariable("layerId")String layerId,
                                                    @PathVariable("specialId") Long specialId,
                                                    @PathVariable("repairId") String repairId){
        return repairService.deleteFromLayer(layerId,specialId,repairId);
    }

}
