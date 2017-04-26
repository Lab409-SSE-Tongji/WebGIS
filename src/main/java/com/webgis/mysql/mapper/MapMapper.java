package com.webgis.mysql.mapper;

import com.webgis.mysql.entity.MapDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;

import java.util.List;

/**
 * Created by Justin on 2017/3/9.
 * map表 数据库接口
 */

@Mapper
public interface MapMapper {

    /**
     * 新建地图
     * @param mapDO
     * @return
     */
    @Insert("INSERT INTO map (account_id, name, folder, description, create_time, update_time) " +
            "VALUES (#{account_id}, #{name}, #{folder}, #{description}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
    @Options(useGeneratedKeys=true, keyProperty = "id")
    int insert(MapDO mapDO);

    /**
     * 删除地图
     * @param id
     * @return
     */
    @Delete("DELETE FROM map WHERE id=#{id}")
    int deleteMap(@Param("id") int id);

    /**
     * 更新地图信息
     * @param mapDO
     * @return
     */
    @Update("UPDATE map SET name=#{name}, folder=#{folder}, description=#{description}, update_time=CURRENT_TIMESTAMP WHERE id=#{id}")
    int update(MapDO mapDO);

    /**
     * 根据id获取map
     * @param id
     * @return
     */
    @Select("SELECT * FROM map WHERE id=#{id}")
    MapDO getMapById(@Param("id") int id);


    /**
     * 根据用户账户及文件夹获取对应的获取地图
     * @param accountID
     * @return
     */
    @Select("SELECT * FROM map WHERE account_id=#{accountID} and folder=#{folder}")
    List<MapDO> getMapByAccountIDandFolderID(@Param("accountID") int accountID, @Param("folder") int folder);








    /**
     * 重置数据库
     * @return
     */
    @Delete("TRUNCATE TABLE map")
    int resetDataBase();

}
