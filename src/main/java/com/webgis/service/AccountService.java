package com.webgis.service;

import com.webgis.security.model.token.JwtToken;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebAccount;

/**
 * Created by Justin on 2017/3/8.
 * 用户相关服务接口
 */

public interface AccountService {

    /**
     * 用户注册
     */
    BaseResult<Object> register(WebAccount webAccount,String role);

    /**
     * 删除用户
     */
    BaseResult<Object> deleteAccount(String userName);

    /**
     * 删除管理员
     */
    BaseResult<Object> deleteAdmin(int id);

    /**
     * 更新用户信息
     * @param webAccount
     * @return
     */
    BaseResult<Object> update(WebAccount webAccount);

    /**
     * 给管理员分配地图
     * @param mapId
     * @param adminId
     * @return
     */
    BaseResult<Object> assignMap(int mapId,int adminId);

    /**
     * 删除管理员的地图
     * @param mapId
     * @param adminId
     * @return
     */
    BaseResult<Object> deleteMapOfAdmin(int mapId, int adminId);

    /**
     * 获取地图的管理员
     * @param mapId
     * @return
     */
    BaseResult<Object> getAdminOfMap(int mapId);

    /**
     * 创建管理员
     * @param webAccount
     * @return
     */
//    BaseResult<Object> createAdmin(WebAccount webAccount);

    /**
     * 获取超级管理员管理的管理员
     * @param superAdminId
     * @return
     */
    BaseResult<Object> getAdminsBySuperAdmin(int superAdminId);
}
