package com.webgis.controller;

import com.webgis.service.HistoryService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Justin on 2017/3/23.
 * 历史版本相关接口
 */

@RestController
@RequestMapping("history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    /**
     * 新建历史版本接口
     * @param webHistory
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> addHistory(@RequestBody WebHistory webHistory) {
        return historyService.addHistory(webHistory);
    }

    /**
     * 删除历史版本接口
     * @param historyId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> deleteHistory(@RequestParam("mapId") int mapId, @RequestParam("historyId") String historyId) {
        return historyService.deleteHistory(mapId, historyId);
    }

    /**
     * 获取历史版本接口
     * @param historyId
     * @return
     */
    @RequestMapping(value = "get", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Object> getHistory(@RequestParam("historyId") String historyId) {
        return historyService.getHistory(historyId);
    }
}
