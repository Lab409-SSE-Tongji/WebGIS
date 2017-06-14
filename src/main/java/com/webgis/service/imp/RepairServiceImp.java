package com.webgis.service.imp;

import com.webgis.domain.base.PointDomain;
import com.webgis.domain.cover.CoverDomain;
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

import java.sql.Timestamp;
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


    @Override
    public BaseResult<Object> addTask(WebRepair webRepair) {
        if(accountMapper.getAccountById(webRepair.getUserId())==null){
            return new BaseResult<>(500,"用户不存在");
        }
        MongoRepair mongoRepair = new MongoRepair(webRepair.getSpecialId(), webRepair.getLayerId(),
                webRepair.getUserId(), webRepair.getDesc(), webRepair.getState());

        mongoRepairRepository.save(mongoRepair);

        MongoLayer mongoLayer = mongoLayerRepository.findById(webRepair.getLayerId());
        if(mongoLayer==null){
            return new BaseResult<>(500,"图层不存在");
        }
        TypeEnum type = mongoLayer.getData().getType();
        if(type==TypeEnum.YJG){
            CoverDomain coverDomain =  (CoverDomain) mongoLayer.getData();
            List<PointDomain> pointDomainList = coverDomain.getPointList();
            boolean flag=true;
            for (PointDomain pointDomain:pointDomainList) {
                if(pointDomain.getSpecialId().equals(webRepair.getSpecialId())){
                    pointDomain.getRepairIds().add(mongoRepair.getId());
                    flag=false;
                    break;
                }
            }
            if(flag){
                return new BaseResult<>(500,"报修设施不存在");
            }
            mongoLayerRepository.save(mongoLayer);
        }
        return new BaseResult<>(mongoRepair);
    }

}
