package com.webgis.service.imp;

import com.webgis.domain.base.BaseDomain;
import com.webgis.mongo.MongoHistoryRepository;
import com.webgis.mongo.MongoLayerRepository;
import com.webgis.mongo.MongoMapRepository;
import com.webgis.mongo.entity.MongoHistory;
import com.webgis.mongo.entity.MongoLayer;
import com.webgis.mongo.entity.MongoMap;
import com.webgis.service.HistoryService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebHistory;
import com.webgis.web.dto.WebLayerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Justin on 2017/3/23.
 *
 * history 相关实现
 */

@Service
public class HistroyServiceImp implements HistoryService {

    @Autowired
    private MongoMapRepository mongoMapRepository;

    @Autowired
    private MongoLayerRepository mongoLayerRepository;

    @Autowired
    private MongoHistoryRepository mongoHistoryRepository;

    /**
     * 新建历史版本
     * @param webHistory
     * @return
     */
    @Override
    public BaseResult<Object> addHistory(WebHistory webHistory) {
        List<WebLayerType> data = new ArrayList<>();

        MongoMap mongoMap = mongoMapRepository.findByMapId(webHistory.getMapId());
        for (String layerId : mongoMap.getLayerIds()) {
            MongoLayer mongoLayer = mongoLayerRepository.findById(layerId);

            MongoLayer mongoLayerCopy = new MongoLayer();
            mongoLayerCopy.setCreateTime(mongoLayer.getCreateTime());
            mongoLayerCopy.setUpdateTime(mongoLayer.getUpdateTime());
            mongoLayerCopy.setData(mongoLayer.getData());

            mongoLayerCopy = mongoLayerRepository.save(mongoLayerCopy);
            data.add(new WebLayerType(mongoLayerCopy.getId(), mongoLayerCopy.getData().getType()));
        }

        MongoHistory mongoHistory = new MongoHistory(webHistory.getDescription(), new Date(), data);
        mongoHistory = mongoHistoryRepository.save(mongoHistory);

        mongoMap.getHistoryDates().add(new Date());
        mongoMap.getHistoryIds().add(mongoHistory.getId());
        mongoMapRepository.save(mongoMap);

        return new BaseResult<>();
    }

    /**
     * 删除历史版本
     * @param mapId, historyId
     * @param historyId
     * @return
     */
    @Override
    public BaseResult<Object> deleteHistory(int mapId, String historyId) {
        mongoHistoryRepository.delete(historyId);
        MongoMap mongoMap = mongoMapRepository.findByMapId(mapId);

        int index = mongoMap.getHistoryIds().indexOf(historyId);
        mongoMap.getHistoryIds().remove(index);
        mongoMap.getHistoryDates().remove(index);

        mongoMapRepository.save(mongoMap);

        return new BaseResult<>();
    }

    /**
     * 获取历史版本
     * @param historyId
     * @return
     */
    @Override
    public BaseResult<Object> getHistory(String historyId) {
        return new BaseResult<>(mongoHistoryRepository.findById(historyId));
    }

    /**
     * 获取所有历史版本接口
     * @param mapId
     * @return
     */
    @Override
    public BaseResult<Object> getAllHistory(int mapId) {
        List<MongoHistory> list = new ArrayList<>();
        MongoMap mongoMap = mongoMapRepository.findByMapId(mapId);
        for (String historyId : mongoMap.getHistoryIds()) {
            MongoHistory mongoHistory = mongoHistoryRepository.findById(historyId);
            list.add(mongoHistory);
        }

        return new BaseResult<>(list);
    }
}
