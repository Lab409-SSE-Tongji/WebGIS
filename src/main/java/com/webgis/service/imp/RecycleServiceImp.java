package com.webgis.service.imp;

import com.webgis.mongo.MongoMapRepository;
import com.webgis.mysql.entity.MapDO;
import com.webgis.mysql.mapper.MapMapper;
import com.webgis.mysql.mapper.RecycleMapper;
import com.webgis.service.RecycleService;
import com.webgis.web.BaseResult;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author 张志强
 * @version v0.1 2017/5/18.
 */
@Service
public class RecycleServiceImp implements RecycleService {
    @Autowired
    private MapMapper mapMapper;
    @Autowired
    private MongoMapRepository mongoMapRepository;
    @Autowired
    private RecycleMapper recycleMapper;
    /**
     * 将地图添加到回收站
     */
    @Override
    public BaseResult<Object> addMapToStation(int mapId){
        if (mapMapper.getMapById(mapId) == null) {
            return new BaseResult<>(500, "该地图不存在");
        }
        MapDO mapDO = mapMapper.getMapById(mapId);
        recycleMapper.insert(mapDO);
        mapMapper.deleteMap(mapId);
        return new BaseResult<>();
    }
    /**
     * 根据账户ID获取回收站内所有地图
     * @param accountId
     * @return
     */
    @Override
    public BaseResult<Object> getMapList(int accountId) {
        List<MapDO> mapDOs = recycleMapper.getMapByAccountId(accountId);
        return new BaseResult<>(mapDOs);
    }
    /**
     * 在回收站中删除地图
     */
    @Override
    public BaseResult<Object> deleteMap(int mapId){
        if (recycleMapper.getMapById(mapId) == null) {
            return new BaseResult<>(500, "该地图不存在");
        }
        recycleMapper.deleteMap(mapId);
        mongoMapRepository.deleteByMapId(mapId);
        return new BaseResult<>();
    }
    /**
     *恢复被删除信息
     */
    @Override
    public BaseResult<Object> recoverMap(int mapId){
        if (recycleMapper.getMapById(mapId) == null) {
            return new BaseResult<>(500, "该地图不存在");
        }
        MapDO mapDO=recycleMapper.getMapById(mapId);
        mapMapper.insert(mapDO);
        recycleMapper.deleteMap(mapId);
        return new BaseResult<>();
    }
}
