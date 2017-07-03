package com.webgis.controller;

import com.webgis.domain.base.LineDomain;
import com.webgis.domain.base.PointDomain;
import com.webgis.domain.cover.CommonCoverDomain;
import com.webgis.domain.pipe.CommonPipeDomain;
import com.webgis.enums.StatusEnum;
import com.webgis.mongo.MongoMapRepository;
import com.webgis.mysql.mapper.AccountMapper;
import com.webgis.security.AuthenticationRequest;
import com.webgis.mysql.mapper.MapMapper;
import com.webgis.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 2017/3/8.
 *
 * 测试用 !慎重!
 *
 */

@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${value}")
    private String value;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private MapMapper mapMapper;

    @Autowired
    private MongoMapRepository mongoMapRepository;


    @RequestMapping("/clean")
    public String clean() {
        System.out.println("清空并重置数据库 OK");
//        accountMapper.resetDataBase();
        mapMapper.resetDataBase();
        return "清空并重置数据库 OK";
    }

    /**
     * 测试后端服务器状态接口
     * @return
     */
    @RequestMapping(value = "/state", method = RequestMethod.GET)
    public String state() {
        return value;
//        return "Hello backend is starting. xtdddd.";
    }
}
