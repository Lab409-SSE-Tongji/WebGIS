package com.webgis.mongo;

import com.webgis.mongo.entity.MongoRepair;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoRepairRepository extends MongoRepository<MongoRepair,String> {
    MongoRepair findById(String id);
}

