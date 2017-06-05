package com.webgis.controller;

import com.webgis.service.TaskService;
import com.webgis.utils.DateUtil;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CCMEOW on 2017/6/5.
 * 任务接口
 */
@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    /**
     * 创建任务
     * @param webTask
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public BaseResult<Object> createTask(@RequestBody WebTask webTask) {
        return taskService.addTask(webTask);
    }

    /**
     * 接受任务
     * @param taskId task id
     * @return
     */
    @RequestMapping(value = "/tasks/{taskId}/{date}/accepted", method = RequestMethod.PATCH)
    @ResponseBody
    public BaseResult<Object> acceptTask( @PathVariable("taskId") String taskId,@PathVariable("date") Long date,@RequestParam int accepterId){
        return taskService.acceptTask(taskId,date,accepterId);
    }


    /**
     * 完成任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/tasks/{taskId}/{date}/finished", method = RequestMethod.PATCH)
    @ResponseBody
    public BaseResult<Object> finishTask( @PathVariable("taskId") String taskId,@PathVariable("date") Long date){
        return taskService.finishTask(taskId,date);
    }


    /**
     * 付款
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/tasks/{taskId}/{date}/paid", method = RequestMethod.PATCH)
    @ResponseBody
    public BaseResult<Object> pay( @PathVariable("taskId") String taskId,@PathVariable("date") Long date){
        return null;
    }

    /**
     * 放弃任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/tasks/{taskId}/givenup", method = RequestMethod.PATCH)
    @ResponseBody
    public BaseResult<Object> giveUpTask( @PathVariable("taskId") String taskId){
        return taskService.giveUpTask(taskId);
    }

    /**
     * 取消任务
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/tasks/{taskId}/canceled", method = RequestMethod.PATCH)
    @ResponseBody
    public BaseResult<Object> cancelTask(@PathVariable("taskId") String taskId){
        return taskService.cancelTask(taskId);
    }

}
