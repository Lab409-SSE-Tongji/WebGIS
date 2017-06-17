package com.webgis.service.imp;

import com.webgis.enums.TypeEnum;
import com.webgis.mongo.MongoLayerRepository;
import com.webgis.mongo.MongoMapRepository;
import com.webgis.mongo.MongoHistoryRepository;
import com.webgis.mongo.entity.MongoLayer;
import com.webgis.mongo.entity.MongoMap;
import com.webgis.mysql.entity.MapDO;
import com.webgis.mysql.mapper.AdminMapMapper;
import com.webgis.mysql.mapper.MapMapper;
import com.webgis.service.MapService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebLayerType;
import com.webgis.web.dto.WebMapInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * Created by Justin on 2017/3/9.
 * 地图相关服务实现
 */

@Service
public class MapServiceImp implements MapService {

    @Autowired
    private MapMapper mapMapper;

    @Autowired
    private AdminMapMapper adminMapMapper;

    @Autowired
    private MongoMapRepository mongoMapRepository;

    @Autowired
    private MongoHistoryRepository mongoHistoryRepository;

    @Autowired
    private MongoLayerRepository mongoLayerRepository;

    /**
     * 新建地图
     * @param webMapInfo
     * @return
     */
    @Override
    public BaseResult<Object> addMap(WebMapInfo webMapInfo) {
        MapDO mapDo = new MapDO(webMapInfo);
        if(mapMapper.getMapNumByMapNameandFolderId(webMapInfo.getName(), webMapInfo.getFolder()).isEmpty() == false){
            return new BaseResult<>(501,"已存在同名地图");
        }
        mapMapper.insert(mapDo);

        MongoMap mongoMap = new MongoMap(mapDo.getId());
        mongoMapRepository.save(mongoMap);

        return new BaseResult<>(mapDo);
    }

    /**
     * 删除地图
     * @param mapId
     * @return
     */
    @Override
    public BaseResult<Object> deleteMap(int mapId) {
        if (mapMapper.getMapById(mapId) == null) {
            return new BaseResult<>(500, "该地图不存在");
        }
        mapMapper.deleteMap(mapId);
        mongoMapRepository.deleteByMapId(mapId);
        return new BaseResult<>();
    }

    /**
     * 更新地图信息
     * @param webMapInfo
     * @return
     */
    @Override
    public BaseResult<Object> updateMap(WebMapInfo webMapInfo) {
        if (mapMapper.getMapById(webMapInfo.getId()) == null) {
            return new BaseResult<>(500, "该地图不存在");
        }
        if(mapMapper.getMapNumByMapNameandFolderIdExceptMapId(webMapInfo.getName(), webMapInfo.getFolder(), webMapInfo.getId()).isEmpty() == false){
            return new BaseResult<>(501,"已存在同名文件夹");
        }
        mapMapper.update(new MapDO(webMapInfo));
        return new BaseResult<>();
    }

    /**
     * 获取地图信息
     * @param mapId
     * @return
     */
    @Override
    public BaseResult<Object> getMap(int mapId) {
        MapDO mapDO = mapMapper.getMapById(mapId);
        if (mapDO == null) {
            return new BaseResult<>(500, "该地图不存在");
        }
        mapDO.setLayerIds(mongoMapRepository.findByMapId(mapId).getLayerIds());
        return new BaseResult<>(mapDO);
    }


    /**
     * 根据账户ID获取所有创建地图
     * @param accountId
     * @return
     */
    @Override
    public BaseResult<Object> getMapByAccountId(int accountId) {
        List<MapDO> mapDOs = mapMapper.getMapByAccountId(accountId);
        return new BaseResult<>(mapDOs);
    }

    /**
     * 根据地图ID获取历史地图id
     * @param mapId
     * @return
     */
    @Override
    public BaseResult<Object> getHistoryIdandDateByMapId(int mapId) {
        MapDO mapDO = mapMapper.getMapById(mapId);
        if (mapDO == null) {
            return new BaseResult<>(500, "该地图不存在");
        }
        mapDO.setHistoryIds(mongoMapRepository.findByMapId(mapId).getHistoryIds());
        mapDO.setHistoryDates(mongoMapRepository.findByMapId(mapId).getHistoryDates());
        return new BaseResult<>(mapDO);
    }

    /**
     * 根据账户ID及当前文件夹ID获取所有创建地图
     * @param accountId
     * @param folderId
     * @return
     */
    @Override
    public BaseResult<Object> getMapByAccountIdandFolderId(int accountId, int folderId) {
        List<MapDO> mapDOs = mapMapper.getMapByAccountIdandFolderId(accountId, folderId);
        return new BaseResult<>(mapDOs);

    }

    /**
     * 根据账户ID及当前文件夹ID及pageID分页获取地图总数及当前页数下的地图
     * @param accountId
     * @param folderId
     * @param pageId
     * @return
     */
    @Override
    public BaseResult<Object> getMapByAccountIdandFolderIdandPageId(int accountId, int folderId, int pageId){
        Map m1 = new HashMap();
        int sum = mapMapper.getMapNumByAccountIdandFolderId(accountId, folderId);
        int pageNum;
        if(sum % 10 == 0) {
            pageNum = sum / 10;
        }else
        {
            pageNum = sum / 10 + 1;
        }
        int pageNow = (pageId - 1) * 10;
        m1.put("pageNum", pageNum);
        List<MapDO> mapDOs = mapMapper.getMapByAccountIdandFolderIdandPageId(accountId, folderId, pageNow);
        m1.put("map", mapDOs);
        return new BaseResult<>(m1);
    }

    @Override
    public BaseResult<Object> getLayerIdAndType(int mapId) {
        List<WebLayerType> layerTypeList = new ArrayList<>();

        MongoMap mongoMap = mongoMapRepository.findByMapId(mapId);
        List<String> layerIds = mongoMap.getLayerIds();
        for (String layerId : layerIds) {
            MongoLayer mongoLayer = mongoLayerRepository.findById(layerId);

            layerTypeList.add(new WebLayerType(layerId, mongoLayer.getData().getType()));
        }

        return new BaseResult(layerTypeList);
    }

    @Override
    public BaseResult<Object> getMapByAdminId(int adminId){
        List<Integer> mapIdList = adminMapMapper.getMapIdByAdminId(adminId);
        List<MapDO> mapDOs = new ArrayList<>();
        for(int mapId : mapIdList){
            mapDOs.add(mapMapper.getMapById(mapId));
        }
        return new BaseResult<>(mapDOs);
    }

    @Override
    public BaseResult<Object> getMapByAdminAndFolder(int adminId,int folderId){
        List<Integer> mapIdList = adminMapMapper.getMapIdByAdminId(adminId);
        List<MapDO> mapDOs = new ArrayList<>();
        for(int mapId : mapIdList){
            mapDOs.add(mapMapper.getMapByIdAndFolderId(mapId,folderId));
        }
        return new BaseResult<>(mapDOs);
    }

    @Override
    public BaseResult<Object> getMapByAdminIdAndFolderAndPage(int adminId,int folderId,Integer currPage){
        if(currPage<1){
            return new BaseResult<>(500,"当前页码不能小于1");
        }
        List<Integer> mapIdList = adminMapMapper.getMapIdByAdminId(adminId);
        List<MapDO> mapDOs = new ArrayList<>();
        for(int mapId : mapIdList){
            mapDOs.add(mapMapper.getMapByIdAndFolderId(mapId,folderId));
        }
        Map m = new HashMap();
        int sum = mapDOs.size();
        int pageNum;
        pageNum = sum%10==0?sum/10:sum/10+1;
        pageNum = pageNum==0?1:pageNum;
        if(currPage>pageNum){
            return new BaseResult<>(500,"当前页码超过总页数");
        }
        int currIndex = (currPage - 1) * 10;

        m.put("pageNum", pageNum);
        int toIndex = currIndex+10<=sum?currIndex+10:sum;
        List<MapDO> pageMapDos = mapDOs.subList(currIndex,toIndex);
        m.put("map", pageMapDos);
        return new BaseResult<>(m);
    }

}
