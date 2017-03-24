package com.webgis.mongo;

import com.webgis.mongo.entity.MongoHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Justin on 2017/3/23.
 * Mongo history 相关接口
 */

public interface MongoHistoryRepository extends MongoRepository<MongoHistory, String> {

    MongoHistory findById(String historyId);
}
