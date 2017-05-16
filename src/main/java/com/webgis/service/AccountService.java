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
    BaseResult<Object> register(WebAccount webAccount);

    /**
     * 删除用户
     */
    BaseResult<Object> deleteAccount(String userName);

    /**
     * 更新用户信息
     * @param webAccount
     * @return
     */
    BaseResult<Object> update(WebAccount webAccount);
}
