package com.webgis.controller;

import com.webgis.service.RepairService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebRepair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
