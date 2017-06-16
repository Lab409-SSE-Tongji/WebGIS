package com.webgis.service;

import com.webgis.enums.ReportStateEnum;
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
    BaseResult<Object> addRepair(WebRepair webRepair);

    /**
     * 修改报修状态
     * @param id
     * @param state
     * @return
     */
    BaseResult<Object> changeState(String id, String state);

    /**
     * 删除图层中存的报修id，但保留数据库中的报修
     * @param layerId
     * @param specialId
     * @param repairId
     * @return
     */
    BaseResult<Object> deleteFromLayer(String layerId,Long specialId,String repairId);

    /**
     * 根据userId，获取所有的报修信息
     * @param userId
     * @return
     */
    BaseResult<Object> findAllByUserId(int userId);

    BaseResult<Object> findAll();
}
