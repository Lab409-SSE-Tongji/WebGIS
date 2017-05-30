package com.webgis.mysql.mapper;

import com.webgis.mysql.entity.MapDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 张志强
 * @version v0.1 2017/5/18.
 */
@Mapper
public interface RecycleMapper {
    /**
     * 将地图添加到回收站
     */
    @Insert("INSERT INTO recyclestation (id,account_id, name, folder, description, create_time, update_time) " +
            "VALUES (#{id},#{account_id}, #{name}, #{folder}, #{description}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
   // @Options(useGeneratedKeys=true, keyProperty = "id")
    int insert(MapDO mapDO);
    /**
     * 根据账户ID获取回收站内所有地图
     * @param accountId
     * @return
     */
    @Select("SELECT * FROM recyclestation WHERE account_id=#{accountId}")
    List<MapDO> getMapByAccountId(@Param("accountId") int accountId);
    /**
     * 根据id获取map
     * @param id
     * @return
     */
    @Select("SELECT * FROM recyclestation WHERE id=#{id}")
    MapDO getMapById(@Param("id") int id);
    /**
     * 删除地图
     * @param id
     * @return
     */
    @Delete("DELETE FROM recyclestation WHERE id=#{id}")
    int deleteMap(@Param("id") int id);


}
