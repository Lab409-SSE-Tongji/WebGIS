package com.webgis.mongo;

import com.webgis.mongo.entity.MongoTask;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by CCMEOW on 2017/6/5.
 */
public interface MongoTaskRepository extends MongoRepository<MongoTask, String>{
    MongoTask findById(String id);
    List<MongoTask> findByCreatorId(int id);
}
