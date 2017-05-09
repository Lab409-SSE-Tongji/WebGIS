package com.webgis.mysql.mapper;


import com.webgis.mysql.entity.PageDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Yan Lieu on 2017/5/8.
 */
public interface PageMapper {
    /**
     * create
     */
    @Insert("INSERT INTO page (account_id, name, previous_page, create_time, update_time) " +
            "VALUES (#{account_id}, #{name}, #{upper_folder}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
    @Options(useGeneratedKeys=true, keyProperty = "id")
    int insert(PageDO pageDO);

    /**
     * delete
     */
    @Delete("DELETE FROM page WHERE id=#{id}")
    int deletePage(@Param("id") int id);

    /**
     * update
     */
    @Update("UPDATE folder SET name=#{name}, previous_page=#{previous_page}, update_time=CURRENT_TIMESTAMP WHERE id=#{id}")
    int update(PageDO pageDO);

    /**
     * get
     */
    @Select("SELECT * FROM page WHERE id=#{id}")
    PageDO getPageById(@Param("id") int id);

    @Select("SELECT * FROM folder WHERE account_id=#{accountId} and previous_page=#{previousPage}")
    List<PageDO> getPageByAccountIDandPreviousPage(@Param("accountId") int accountId, @Param("previousPage") int previousPage);

    /**
     * reset
     * @return
     */
    @Delete("TRUNCATE TABLE page")
    int resetDataBase();

}
