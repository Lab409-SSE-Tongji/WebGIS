package com.webgis.service.imp;

import com.webgis.domain.base.PointDomain;
import com.webgis.domain.cover.CoverDomain;
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


    @Override
    public BaseResult<Object> addTask(WebRepair webRepair) {
        if(accountMapper.getAccountById(webRepair.getUserId())==null){
            return new BaseResult<>(500,"用户不存在");
        }
        MongoRepair mongoRepair = new MongoRepair(webRepair.getSpecialId(), webRepair.getLayerId(),
                webRepair.getUserId(), webRepair.getDesc(), ReportStateEnum.getEnum(webRepair.getState()));

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

    @Override
    public BaseResult<Object> changeState(String id, String state){
        MongoRepair mongoRepair = mongoRepairRepository.findOne(id);
        if(mongoRepair == null){
            return new BaseResult<>(500,"报修不存在");
        }
        mongoRepair.setState(ReportStateEnum.getEnum(state));
        mongoRepairRepository.save(mongoRepair);
        return new BaseResult<>(mongoRepair);
    }

    @Override
    public BaseResult<Object> deleteFromLayer(String layerId,Long specialId,String repairId){
        MongoLayer mongoLayer = mongoLayerRepository.findById(layerId);
        if(mongoLayer==null){
            return new BaseResult<>(500,"图层不存在");
        }
        TypeEnum type = mongoLayer.getData().getType();
        if(type==TypeEnum.YJG){
            CoverDomain coverDomain =  (CoverDomain) mongoLayer.getData();
            List<PointDomain> pointDomainList = coverDomain.getPointList();
            boolean flag=true;
            for (PointDomain pointDomain:pointDomainList) {
                System.out.println(pointDomain.getSpecialId());
                if(pointDomain.getSpecialId().equals(specialId)){
                    System.out.println("true");
                    Iterator<String> iterator = pointDomain.getRepairIds().iterator();
                    while(iterator.hasNext()){
                        String id = iterator.next();
                        System.out.println(id);
                        if(id.equals(repairId)){
                            iterator.remove();
                            flag=false;
                            break;
                        }
                    }
                }
            }
            if(flag){
                return new BaseResult<>(500,"报修不存在");
            }
            mongoLayerRepository.save(mongoLayer);
        }
        return new BaseResult<>(null);
    }
}
