package com.webgis.service.imp;

import com.webgis.mongo.MongoMapRepository;
import com.webgis.mongo.entity.MongoMap;
import com.webgis.mysql.entity.MapDO;
import com.webgis.mysql.mapper.MapMapper;
import com.webgis.service.MapService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebMapInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Justin on 2017/3/9.
 * 地图相关服务实现
 */

@Service
public class MapServiceImp implements MapService {

    @Autowired
    private MapMapper mapMapper;

    @Autowired
    private MongoMapRepository mongoMapRepository;

    /**
     * 新建地图
     * @param webMapInfo
     * @return
     */
    @Override
    public BaseResult<Object> addMap(WebMapInfo webMapInfo) {
        MapDO mapDo = new MapDO(webMapInfo);
        mapMapper.insert(mapDo);

        MongoMap mongoMap = new MongoMap(mapDo.getId());
        mongoMapRepository.save(mongoMap);

        return new BaseResult<>();
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
}
