package com.webgis.mongo;

import com.webgis.mongo.entity.MongoMap;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Justin on 2017/3/13.
 *
 * Mongo数据库 map collection 接口
 *
 */

public interface MongoMapRepository extends MongoRepository<MongoMap, Integer> {

    MongoMap findByMapId(Integer mapId);

    int deleteByMapId(Integer mapId);

}
