package com.webgis.service.imp;

import com.webgis.domain.base.LineDomain;
import com.webgis.domain.base.PointDomain;
import com.webgis.domain.cover.CoverDomain;
import com.webgis.domain.lamp.LampDomain;
import com.webgis.domain.pipe.PipeDomain;
import com.webgis.enums.ReportStateEnum;
import com.webgis.enums.TypeEnum;
import com.webgis.mongo.MongoLayerRepository;
import com.webgis.mongo.MongoRepairRepository;
import com.webgis.mongo.entity.MongoLayer;
import com.webgis.mongo.entity.MongoRepair;
import com.webgis.mysql.mapper.AccountMapper;
import com.webgis.mysql.mapper.MapMapper;
import com.webgis.service.RepairService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebRepair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.Pipe;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;


/**
 * Created by CCMEOW on 2017/6/14.
 */
@Service
public class RepairServiceImp implements RepairService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private MongoRepairRepository mongoRepairRepository;

    @Autowired
    private MongoLayerRepository mongoLayerRepository;


    //TODO:重构点线的集合...
    @Override
    public BaseResult<Object> addRepair(WebRepair webRepair) {
        if (accountMapper.getAccountById(webRepair.getUserId()) == null) {
            return new BaseResult<>(500, "用户不存在");
        }
        MongoRepair mongoRepair = new MongoRepair(webRepair.getSpecialId(), webRepair.getLayerId(),
                webRepair.getUserId(), webRepair.getDesc(), webRepair.getUrl(), ReportStateEnum.getEnum(webRepair.getState()));

        mongoRepairRepository.save(mongoRepair);

        MongoLayer mongoLayer = mongoLayerRepository.findById(webRepair.getLayerId());
        if (mongoLayer == null) {
            return new BaseResult<>(500, "图层不存在");
        }
        TypeEnum type = mongoLayer.getData().getType();

        boolean flag = true;
        switch (type){
            case YJG:
                CoverDomain coverDomain = (CoverDomain) mongoLayer.getData();
                List<PointDomain> pointDomainList = coverDomain.getPointList();

                for (PointDomain pointDomain : pointDomainList) {
                    if (pointDomain.getSpecialId().equals(webRepair.getSpecialId())) {
                        pointDomain.getRepairIds().add(mongoRepair.getId());
                        flag = false;
                        break;
                    }
                }
                break;
            case LD:
                LampDomain lampDomain = (LampDomain)mongoLayer.getData();
                List<PointDomain> lampDomainList = lampDomain.getPointList();

                for (PointDomain pointDomain : lampDomainList) {
                    if (pointDomain.getSpecialId().equals(webRepair.getSpecialId())) {
                        pointDomain.getRepairIds().add(mongoRepair.getId());
                        flag = false;
                        break;
                    }
                }
                break;
            case XSG:
                PipeDomain pipeDomain = (PipeDomain) mongoLayer.getData();
                List<LineDomain> lineDomainList = pipeDomain.getLineList();
                for (LineDomain lineDomain : lineDomainList) {
                    if (lineDomain.getSpecialId().equals(webRepair.getSpecialId())) {
                        lineDomain.getRepairIds().add(mongoRepair.getId());
                        flag = false;
                        break;
                    }
                }
        }
        if (flag) {
            return new BaseResult<>(500, "报修设施不存在");
        }
        mongoLayerRepository.save(mongoLayer);
        return new BaseResult<>(mongoRepair);
    }

    @Override
    public BaseResult<Object> changeState(String id, String state) {
        MongoRepair mongoRepair = mongoRepairRepository.findOne(id);
        if (mongoRepair == null) {
            return new BaseResult<>(500, "报修不存在");
        }
        mongoRepair.setState(ReportStateEnum.getEnum(state));
        mongoRepairRepository.save(mongoRepair);
        return new BaseResult<>(mongoRepair);
    }

    //TODO:重构代码，加入BaseSectionDomain
    @Override
    public BaseResult<Object> deleteFromLayer(String layerId, Long specialId, String repairId) {
        MongoLayer mongoLayer = mongoLayerRepository.findById(layerId);
        if (mongoLayer == null) {
            return new BaseResult<>(500, "图层不存在");
        }
        TypeEnum type = mongoLayer.getData().getType();
        boolean flag = true;
        List<String> repairIdList = null;
        switch (type) {
            case LD:
                LampDomain lampDomain = (LampDomain) mongoLayer.getData();
                List<PointDomain> pointDomainList = lampDomain.getPointList();
                for (PointDomain pointDomain : pointDomainList) {
                    if (pointDomain.getSpecialId().equals(specialId)) {
                        repairIdList = pointDomain.getRepairIds();
                        break;
                    }
                }
                break;
            case YJG:
                CoverDomain coverDomain = (CoverDomain) mongoLayer.getData();
                List<PointDomain> lampDomainList = coverDomain.getPointList();
                for (PointDomain pointDomain : lampDomainList) {
                    if (pointDomain.getSpecialId().equals(specialId)) {
                        repairIdList = pointDomain.getRepairIds();
                        break;
                    }
                }
                break;
            case XSG:
                PipeDomain pipeDomain = (PipeDomain) mongoLayer.getData();
                List<LineDomain> lineDomainList = pipeDomain.getLineList();
                for (LineDomain lineDomain : lineDomainList) {
                    if (lineDomain.getSpecialId().equals(specialId)) {
                        repairIdList = lineDomain.getRepairIds();
                        break;
                    }
                }
            default:
                break;
        }
        if (repairIdList == null) {
            return new BaseResult<>(500, "报修设施不存在");
        } else {
            Iterator<String> iterator = repairIdList.iterator();
            while (iterator.hasNext()) {
                String id = iterator.next();
                if (id.equals(repairId)) {
                    iterator.remove();
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            return new BaseResult<>(500, "报修不存在");
        }
        mongoLayerRepository.save(mongoLayer);
        return new BaseResult<>(null);
    }
}
