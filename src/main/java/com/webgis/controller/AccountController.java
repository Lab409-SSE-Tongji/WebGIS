package com.webgis.controller;

import com.webgis.enums.RoleEnum;
import com.webgis.security.model.token.JwtTokenFactory;
import com.webgis.service.AccountService;
import com.webgis.web.BaseResult;
import com.webgis.web.dto.WebAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Justin on 2017/3/8.
 * 用户相关web接口
 */

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenFactory tokenFactory;
    /**
     * 用户注册
     * @param webAccount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public BaseResult<Object> register(@RequestBody WebAccount webAccount) {
        return accountService.register(webAccount, RoleEnum.USER.toString());
    }

    /**
     * 创建管理员
     * @param webAccount
     * @return
     */
    @RequestMapping(value = "/accounts/admin",method = RequestMethod.POST)
    public BaseResult<Object> createAdmin(@RequestBody WebAccount webAccount){
        return accountService.register(webAccount,RoleEnum.ADMIN.toString());
    }

//    /**
//     * 用户登录
//     * @param authenticationRequest
//     * @return
//     * @throws AuthenticationException
//     */
//    @RequestMapping(value = "/token", method = RequestMethod.POST)
//    public String auth(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response) throws AuthenticationException {
//        // Perform the security
//        System.out.println("login controller");
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authenticationRequest.getUsername(),
//                        authenticationRequest.getPassword()
//                )
//        );
//
//        System.out.println("authenticate in controller");
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        JwtToken jwtToken =  accountService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword());
//        System.out.println("generate jwtToken");
//        response.setStatus(HttpStatus.OK.value());
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setHeader(SecurityConfiguration.HEADER_TOKEN, jwtToken.getToken());
//        return "Login successfully";
//    }

    /**
     * 删除用户
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/accounts", method = RequestMethod.DELETE)
    public BaseResult<Object> deleteAccount(@RequestParam("username") String userName){
        return accountService.deleteAccount(userName);
    }

    @ResponseBody
    @RequestMapping(value = "/account/admin/{id}", method = RequestMethod.DELETE)
    public BaseResult<Object> deleteAdmin(@PathVariable("id") Integer id){
        return accountService.deleteAdmin(id);
    }

    /**
     * 更新用户信息
     * @param webAccount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/accounts", method = RequestMethod.PATCH)
    public BaseResult<Object> update(@RequestBody WebAccount webAccount) {

        return accountService.update(webAccount);
    }

    /**
     * 分配地图给管理员
     * @param mapId
     * @param adminId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/accounts/map",method = RequestMethod.POST)
    public BaseResult<Object> assignMap(@RequestParam(required = true) Integer mapId,@RequestParam(required = true) Integer adminId){
        return accountService.assignMap(mapId,adminId);
    }

    /**
     * 删除管理员的地图
     * @param mapId
     * @param adminId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/accounts/map",method = RequestMethod.DELETE)
    public BaseResult<Object> deleteMapOfAdmin(@RequestParam Integer mapId,@RequestParam Integer adminId){
        return accountService.deleteMapOfAdmin(mapId,adminId);
    }

    /**
     * 获取地图的管理者
     * @param mapId
     * @return
     */
    @RequestMapping(value = "/accounts/map",method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getAdminsOfMap(@RequestParam Integer mapId){
        return accountService.getAdminOfMap(mapId);
    }

    /**
     * 获取超级管理员管理的管理员
     * @param superAdminId
     * @return
     */
    @RequestMapping(value = "/accounts/admin",method = RequestMethod.GET)
    @ResponseBody
    public BaseResult<Object> getAdminsBySuperAdmin(@RequestParam Integer superAdminId){
        return accountService.getAdminsBySuperAdmin(superAdminId);
    }
}
