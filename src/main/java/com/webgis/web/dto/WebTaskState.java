package com.webgis.web.dto;

import com.webgis.enums.TaskStateEnum;

import java.io.Serializable;

/**
 * Created by CCMEOW on 2017/6/5.
 */
public class WebTaskState implements Serializable {

    TaskStateEnum taskState;
    String taskId;

    public WebTaskState(){

    }

    public WebTaskState(String taskId,TaskStateEnum taskState){
        this.taskId = taskId;
        this.taskState = taskState;
    }

    public TaskStateEnum getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskStateEnum taskState) {
        this.taskState = taskState;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
