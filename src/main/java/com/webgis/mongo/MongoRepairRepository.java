package com.webgis.mongo;

import com.webgis.mongo.entity.MongoRepair;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface MongoRepairRepository extends MongoRepository<MongoRepair,String> {
    MongoRepair findById(String id);
    List<MongoRepair> findByUserId(int userId);
}

