package com.webgis.mysql.mapper;

import com.webgis.mysql.entity.FolderDO;
import com.webgis.mysql.entity.MapDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by cz on 2017/4/25.
 */

@Mapper
public interface FolderMapper {
    /**
     * 新建文件夹
     * @param folderDO
     * @return
     */
    @Insert("INSERT INTO folder (account_id, name, upper_folder, create_time, update_time) " +
            "VALUES (#{account_id}, #{name}, #{upper_folder}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
    @Options(useGeneratedKeys=true, keyProperty = "id")
    int insert(FolderDO folderDO);

    /**
     * 删除文件夹
     * @param id
     * @return
     */
    @Delete("DELETE FROM folder WHERE id=#{id}")
    int deleteFolder(@Param("id") int id);

    /**
     * 更新文件夹信息
     * @param folderDO
     * @return
     */
    @Update("UPDATE folder SET name=#{name}, upper_folder=#{upper_folder}, update_time=CURRENT_TIMESTAMP WHERE id=#{id}")
    int update(FolderDO folderDO);

    /**
     * 根据id获取文件夹
     * @param id
     * @return
     */
    @Select("SELECT * FROM folder WHERE id=#{id}")
    FolderDO getFolderById(@Param("id") int id);


    /**
     * 根据用户账户及上层文件夹信息获取对应的本级文件夹
     * @param accountId
     * @param upperFolder
     * @return
     */
    @Select("SELECT * FROM folder WHERE account_id=#{accountId} and upper_folder=#{upperFolder}")
    List<FolderDO> getFolderByAccountIDandUpperFolder(@Param("accountId") int accountId, @Param("upperFolder") int upperFolder);

    /**
     * 根据上层文件夹信息获取对应的本级文件夹
     * @param upperFolder
     * @return
     */
    @Select("SELECT id FROM folder WHERE upper_folder=#{upperFolder}")
    List<Integer> getFoldersByUpperFolder(@Param("upperFolder") int upperFolder);

    /**
     * 检测是否已存在该命名的文件夹
     * @param folderName
     * @param upperFolder
     * @return
     */
    @Select("SELECT id FROM folder WHERE name = #{folderName} and upper_folder=#{upperFolder}")
    List<Integer> getFolderNumByFolderNameandUpperFolder(@Param("folderName") String folderName, @Param("upperFolder") int upperFolder);

    /**
     * 检测是否除本身之外已存在该命名的地图
     * @param folderId
     * @param folderId
     * @param upperFolder
     * @return
     */
    @Select("SELECT id FROM folder WHERE name=#{folderName} and upper_folder=#{upperFolder} and id<>#{folderId}")
    List<Integer> getFolderNumByFolderNameandUpperFolderExceptMapId(@Param("folderName") String folderName, @Param("folderId") int folderId, @Param("upperFolder") int upperFolder);


    /**
     * 重置数据库
     * @return
     */
    @Delete("TRUNCATE TABLE folder")
    int resetDataBase();
}
