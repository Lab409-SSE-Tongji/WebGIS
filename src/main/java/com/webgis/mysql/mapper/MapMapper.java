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
     * 从回收站恢复地图
     */
    @Insert("INSERT INTO map (id,account_id, name, folder, description, create_time, update_time) " +
            "VALUES (#{id},#{account_id}, #{name}, #{folder}, #{description}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
    // @Options(useGeneratedKeys=true, keyProperty = "id")
    int recover(MapDO mapDO);
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
     * 根据账户ID获取所有创建地图
     * @param accountId
     * @return
     */
    @Select("SELECT * FROM map WHERE account_id=#{accountId}")
    List<MapDO> getMapByAccountId(@Param("accountId") int accountId);

    /**
     * 根据账户ID及当前文件夹ID获取所有创建地图
     * @param accountId
     * @param folderId
     * @return
     */
    @Select("SELECT * FROM map WHERE account_id=#{accountId} and folder=#{folderId}")
    List<MapDO> getMapByAccountIdandFolderId(@Param("accountId") int accountId, @Param("folderId") int folderId);

    /**
     * 根据账户ID及当前文件夹ID及pageID分页获取所有创建地图
     * @param accountId
     * @param folderId
     * @param pageNow
     * @return
     */
    @Select("SELECT * FROM map WHERE account_id=#{accountId} and folder=#{folderId} limit #{pageNow},10")
    List<MapDO> getMapByAccountIdandFolderIdandPageId(@Param("accountId") int accountId, @Param("folderId") int folderId, @Param("pageNow") int pageNow);

    /**
     * 根据账户ID及当前文件夹ID及pageID分页获取所有创建地图
     * @param accountId
     * @param folderId
     * @return
     */
    @Select("SELECT count(*) FROM map WHERE account_id=#{accountId} and folder=#{folderId}")
    int getMapNumByAccountIdandFolderId(@Param("accountId") int accountId, @Param("folderId") int folderId);

    /**
     * 重置数据库
     * @return
     */
    @Delete("TRUNCATE TABLE map")
    int resetDataBase();

}
