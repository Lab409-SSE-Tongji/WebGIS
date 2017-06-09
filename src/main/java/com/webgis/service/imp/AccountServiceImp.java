package com.webgis.service.imp;

import com.webgis.enums.RoleEnum;
import com.webgis.mysql.entity.AccountDO;
import com.webgis.mysql.entity.AdminMapDO;
import com.webgis.mysql.mapper.AccountMapper;
import com.webgis.mysql.mapper.AdminMapMapper;
import com.webgis.mysql.mapper.MapMapper;
import com.webgis.security.model.token.JwtTokenFactory;
import com.webgis.service.AccountService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 2017/3/8.
 * 用户相关服务实现
 */

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private MapMapper mapMapper;

    @Autowired
    private AdminMapMapper adminMapMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenFactory tokenFactory;

    /**
     * 用户注册
     * @param webAccount
     * @return
     */
    @Override
    public BaseResult<Object> register(WebAccount webAccount,String role) {

        if (accountMapper.getAccountByUsername(webAccount.getUsername()) != null) {
            return new BaseResult<>(500, "用户已经存在");
        }
        accountMapper.insert(new AccountDO(webAccount, role));
        return new BaseResult<>();
    }

    /**
     * 更新用户信息
     * @param webAccount
     * @return
     */
    @Override
    public BaseResult<Object> update(WebAccount webAccount) {
        if (accountMapper.getAccountByUsername(webAccount.getUsername()) == null) {
            return new BaseResult<>(500, "该用户不存在");
        }
        accountMapper.update(new AccountDO(webAccount));
        return new BaseResult<>();
    }

    /**
     * 删除用户
     * @param userName
     * @return
     */
    @Override
    public BaseResult<Object> deleteAccount(String userName) {
        if (accountMapper.getAccountByUsername(userName) == null) {
            return new BaseResult<>(500, "该用户不存在");
        }
        accountMapper.deleteAccount(userName);
        return new BaseResult<>();
    }

    @Override
    public BaseResult<Object> assignMap(int mapId,int adminId){
        if (accountMapper.getAdminById(adminId) == null) {
            return new BaseResult<>(500, "管理员不存在或该用户不是管理员");
        }
        if(mapMapper.getMapById(mapId)==null){
            return new BaseResult<>(500,"地图不存在");
        }
        if(adminMapMapper.getAdminMap(mapId,adminId)!=null){
            return new BaseResult<>(500,"管理员已管理该地图");
        }
        adminMapMapper.insert(new AdminMapDO(mapId,adminId));
        return new BaseResult<>();
    }

    @Override
    public BaseResult<Object> deleteMapOfAdmin(int mapId, int adminId){
        if(adminMapMapper.getAdminMap(mapId,adminId)==null){
            return new BaseResult<>(500,"管理员不管理该地图");
        }
        adminMapMapper.deleteOne(mapId,adminId);
        return new BaseResult<>();
    }

    @Override
    public BaseResult<Object> getAdminOfMap(int mapId){
        List<Integer> adminIdList=adminMapMapper.getAdminIdByMapId(mapId);
        List<AccountDO> accountDOList=new ArrayList<>();
        for(int adminId :adminIdList){
            accountDOList.add(accountMapper.getAdminById(adminId));
        }
        return new BaseResult<>(accountDOList);
    }

}
