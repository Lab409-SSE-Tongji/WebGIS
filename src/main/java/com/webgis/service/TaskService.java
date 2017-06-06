package com.webgis.service;

import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebTask;

import java.util.Date;

/**
 * Created by CCMEOW on 2017/6/5.
 */
public interface TaskService {

    /**
     * get task list
     * @param creatorId 发布者 id
     * @return
     */
    BaseResult<Object> getAllTasks(Integer creatorId);

    /**
     * 创建任务
     * @param webTask 任务信息
     * @return
     */
    BaseResult<Object> addTask(WebTask webTask);

    /**
     * 接受任务
     * @param taskId
     * @param date
     * @return
     */
    BaseResult<Object> acceptTask(String taskId, Long date,int accepterId);

    /**
     * 完成任务
     * @param taskId
     * @param date
     * @return
     */
    BaseResult<Object> finishTask(String taskId, Long date);

    /**
     * 放弃任务
     * @param taskId
     * @return
     */
    BaseResult<Object> giveUpTask(String taskId);

    /**
     * 取消任务
     * @param taskId
     * @return
     */
    BaseResult<Object> cancelTask(String taskId);


}
