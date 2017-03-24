package com.webgis.service;

import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebHistory;

/**
 * Created by Justin on 2017/3/23.
 * history 相关service
 */

public interface HistoryService {

    /**
     * 新建历史版本接口
     * @param webHistory
     * @return
     */
    BaseResult<Object> addHistory(WebHistory webHistory);

    /**
     * 删除历史版本接口
     * @param mapId, historyId
     * @return
     */
    BaseResult<Object> deleteHistory(int mapId, String historyId);

    /**
     * 获取历史版本接口
     * @param historyId
     * @return
     */
    BaseResult<Object> getHistory(String historyId);
}
