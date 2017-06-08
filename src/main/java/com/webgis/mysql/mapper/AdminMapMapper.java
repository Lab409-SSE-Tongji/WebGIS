package com.webgis.mysql.mapper;

import com.webgis.mysql.entity.AdminMapDO;
import org.apache.ibatis.annotations.*;

/**
 * Created by CCMEOW on 2017/6/8.
 */
@Mapper
public interface AdminMapMapper {

    /**
     *
     * @param adminMapDO
     * @return
     */
    @Insert("INSERT INTO admin_map (map_id, admin_id) " +
            "VALUES (#{map_id}, #{admin_id})")
    @Options(useGeneratedKeys=true, keyProperty = "id")
    int insert(AdminMapDO adminMapDO);

    @Select("SELECT * FROM admin_map WHERE map_id=#{map_id} AND admin_id=#{admin_id}")
    AdminMapDO getAdminMap(@Param("map_id") Integer map_id,@Param("admin_id") Integer admin_id);

    @Delete("DELETE FROM admin_map WHERE map_id=#{map_id} AND admin_id=#{admin_id}")
    int deleteOne(@Param("map_id") Integer map_id,@Param("admin_id") Integer admin_id);

    /**
     * 重置数据库
     * @return
     */
    @Delete("TRUNCATE TABLE admin_map")
    int resetDataBase();

}
