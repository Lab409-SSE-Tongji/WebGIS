package com.webgis.service.imp;

import com.mongodb.Mongo;
import com.webgis.enums.TaskStateEnum;
import com.webgis.mongo.MongoTaskRepository;
import com.webgis.mongo.entity.MongoTask;
import com.webgis.mysql.mapper.AccountMapper;
import com.webgis.service.TaskService;
import com.webgis.utils.DateUtil;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebTask;
import com.webgis.web.dto.WebTaskState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CCMEOW on 2017/6/5.
 */
@Service
public class TaskServiceImp implements TaskService {
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    private MongoTaskRepository mongoTaskRepository;

    @Override
    public BaseResult<Object> addTask(WebTask webTask) {
        try {
            String ts = DateUtil.toTimestamp(webTask.getCreateDate());
            if (accountMapper.getAccountById(webTask.getCreatorId()) == null) {
                return new BaseResult<>(500, "创建者不存在！");
            }
            MongoTask mongoTask = new MongoTask(webTask.getLat(), webTask.getLng(), webTask.getAddressDesc(),
                    webTask.getTaskDesc(), ts, webTask.getValue(), webTask.getCreatorId());
            mongoTaskRepository.save(mongoTask);

            return new BaseResult<>(new WebTaskState(mongoTask.getId(), mongoTask.getTaskState()));
        } catch (ParseException e) {
            return new BaseResult<>(400, "参数错误");
        }

    }

    public BaseResult<Object> acceptTask(String taskId, Long date, int accepterId) {
        try {
            String ts = DateUtil.toTimestamp(date);
            if (accountMapper.getAccountById(accepterId) == null) {
                return new BaseResult<>(500, "接受者不存在！");
            }
            MongoTask mongoTask = mongoTaskRepository.findById(taskId);
            if (mongoTask == null) {
                return new BaseResult<>(500, "任务不存在！");
            }
            if (mongoTask.getTaskState() == TaskStateEnum.ACCEPTED || mongoTask.getTaskState() == TaskStateEnum.FINISHED || mongoTask.getTaskState() == TaskStateEnum.CANCELED) {
                return new BaseResult<>(206, "任务状态不正确");
            }
            mongoTask.setTaskState(TaskStateEnum.ACCEPTED);
            mongoTask.setAcceptTime(ts);
            mongoTask.setAccepterId(accepterId);
            mongoTaskRepository.save(mongoTask);
            return new BaseResult<>(new WebTaskState(mongoTask.getId(), mongoTask.getTaskState()));
        } catch (ParseException e) {
            return new BaseResult<>(400, "参数错误");
        }
    }

    public BaseResult<Object> finishTask(String taskId, Long date) {
        try {
            String ts = DateUtil.toTimestamp(date);
            MongoTask mongoTask = mongoTaskRepository.findById(taskId);
            if (mongoTask == null) {
                return new BaseResult<>(500, "任务不存在！");
            }
            if (mongoTask.getTaskState() != TaskStateEnum.ACCEPTED) {
                return new BaseResult<>(206, "任务状态不正确");
            }
            mongoTask.setTaskState(TaskStateEnum.FINISHED);
            mongoTask.setFinishTime(ts);
            mongoTaskRepository.save(mongoTask);
            return new BaseResult<>(new WebTaskState(mongoTask.getId(), mongoTask.getTaskState()));
        } catch (ParseException e) {
            return new BaseResult<>(400, "参数错误");
        }
    }

    public BaseResult<Object> giveUpTask(String taskId) {
        MongoTask mongoTask = mongoTaskRepository.findById(taskId);
        if (mongoTask == null) {
            return new BaseResult<>(500, "任务不存在！");
        }
        if (mongoTask.getTaskState() != TaskStateEnum.ACCEPTED) {
            return new BaseResult<>(206, "任务状态不正确");
        }
        mongoTask.setTaskState(TaskStateEnum.GIVENUP);
        mongoTask.setAccepterId(-1);
        mongoTaskRepository.save(mongoTask);
        return new BaseResult<>(new WebTaskState(mongoTask.getId(), mongoTask.getTaskState()));
    }

    public BaseResult<Object> cancelTask(String taskId) {
        MongoTask mongoTask = mongoTaskRepository.findById(taskId);
        if (mongoTask == null) {
            return new BaseResult<>(500, "任务不存在！");
        }
        if (mongoTask.getTaskState() == TaskStateEnum.ACCEPTED) {
            return new BaseResult<>(206, "任务正在进行！");
        }
        mongoTask.setTaskState(TaskStateEnum.CANCELED);
        mongoTaskRepository.save(mongoTask);
        return new BaseResult<>(new WebTaskState(mongoTask.getId(), mongoTask.getTaskState()));
    }


    public BaseResult<Object> getAllTasks() {
        List<MongoTask> tasks = mongoTaskRepository.findAll();
        ArrayList<WebTask> results = new ArrayList<>();
        for (MongoTask task : tasks) {
            try {
                results.add(new WebTask(task));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new BaseResult<>(results);
    }

}
