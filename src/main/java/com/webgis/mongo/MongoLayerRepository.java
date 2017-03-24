package com.webgis.mongo;

import com.webgis.mongo.entity.MongoLayer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Justin on 2017/3/21.
 * Mongo layer 相关接口
 */

public interface MongoLayerRepository extends MongoRepository<MongoLayer, String> {

    MongoLayer findById(String id);
}
