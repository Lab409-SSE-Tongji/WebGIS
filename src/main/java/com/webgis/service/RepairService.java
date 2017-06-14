package com.webgis.service;

import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebRepair;

/**
 * Created by CCMEOW on 2017/6/14.
 */
public interface RepairService {

    /**
     * 创建报修
     * @param webRepair
     * @return
     */
    BaseResult<Object> addTask(WebRepair webRepair);
}
