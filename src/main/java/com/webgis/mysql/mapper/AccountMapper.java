package com.webgis.mysql.mapper;

import com.webgis.mysql.entity.AccountDO;
import org.apache.ibatis.annotations.*;

/**
 * Created by Justin on 2017/3/8.
 * account表 数据库接口
 */

@Mapper
public interface AccountMapper {

    /**
     * 新建用户
     * @param accountDO
     * @return
     */
    @Insert("INSERT INTO account (name, username, password, create_time, update_time) " +
            "VALUES (#{name}, #{username}, #{password}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
    int insert(AccountDO accountDO);

    /**
     * 删除用户
     * @param userName
     * @return
     */
    @Delete("DELETE FROM account WHERE username=#{username}")
    int deleteAccount(@Param("username") String userName);

    /**
     * 更新用户信息
     * @param accountDO
     * @return
     */
    @Update("UPDATE account SET name=#{name}, password=#{password}, update_time=CURRENT_TIMESTAMP WHERE username=#{username}")
    int update(AccountDO accountDO);

    /**
     * 获取用户ID
     * @param userName
     * @return
     */
    @Select("SELECT * FROM account WHERE username=#{username}")
    AccountDO getAccountByUsername(@Param("username") String userName);









    /**
     * 重置数据库
     * @return
     */
    @Delete("TRUNCATE TABLE account")
    int resetDataBase();
}
