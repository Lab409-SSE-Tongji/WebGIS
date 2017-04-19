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
@RequestMapping("/root")
public class RootController {


    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private MapMapper mapMapper;

    @Autowired
    private MongoMapRepository mongoMapRepository;

    @Autowired
    private ExcelService excelService;

    @RequestMapping("/clean")
    public String clean() {
        System.out.println("清空并重置数据库 OK");
//        accountMapper.resetDataBase();
        mapMapper.resetDataBase();
        return "清空并重置数据库 OK";
    }



    @RequestMapping("/login")
    public String login(){
        return "Login Page";
    }

    @RequestMapping("/index")
    public String goToIndex(){
        return "Index Page";
    }


    @RequestMapping("/test2")
    public String test2() {
        List<PointDomain> list = new ArrayList<>();
        list.add(new PointDomain(1,2,3,StatusEnum.GOOD));
        list.add(new PointDomain(1,2,3,StatusEnum.BAD));
        list.add(new PointDomain(3,2,1,StatusEnum.GOOD));
        list.add(new PointDomain(3,2,4,StatusEnum.GOOD));
        CommonCoverDomain commonCoverDomain = new CommonCoverDomain(list);
        System.out.println(commonCoverDomain);

        List<LineDomain> list1 = new ArrayList<>();
        list1.add(new LineDomain(1,2,3,4,5,6,StatusEnum.GOOD));
        list1.add(new LineDomain(1,2,3,4,5,6,StatusEnum.GOOD));
        list1.add(new LineDomain(1,2,3,4,5,6,StatusEnum.GOOD));
        list1.add(new LineDomain(1,2,3,4,5,6,StatusEnum.GOOD));
        CommonPipeDomain commonPipeDomain = new CommonPipeDomain(list1);
        System.out.println(commonPipeDomain);

        return "Rain test2.";
    }

    @RequestMapping("/test3")
    public String test3(@RequestParam(value = "file") MultipartFile file) {
        CommonCoverDomain commonCoverDomain = excelService.pointExcelAnalysis(file);
        System.out.println(commonCoverDomain.toString());
        return "Rain test3.";
    }

    @RequestMapping("/test4")
    public String test4(@RequestParam(value = "file") MultipartFile file) {
        CommonPipeDomain commonPipeDomain  = excelService.lineExcelAnalysis(file);
        System.out.println(commonPipeDomain.toString());
        return "Rain test4.";
    }
}
