package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.DemoService;
import com.example.demo.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Resource
    private DemoService userService;

    @RequestMapping("add")
    @ResponseBody
    public String add(){
        userService.demo();
        return "success";
    }

    @RequestMapping("select")
    @ResponseBody
    public Map<String, Object> select(long id){
        UserInfo userInfo = new UserInfo();
        Date s = DateUtil.parse("2019-05-16 00:00:00", "yyyy-MM-dd HH:mm:ss");
        userInfo.setStartTime(s);
        userInfo.setEndTime(DateUtil.parse("2019-05-31 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        userInfo.setUserId(id);
        UserInfo userInfo1 = userService.getUserInfoByUserId(userInfo);
        Map<String, Object> map = new HashMap();
        map.put("data", userInfo1);
        return map;
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(int page,int pageSize){
        UserInfo userInfo = new UserInfo();
        Date s = DateUtil.parse("2019-05-16 00:00:00", "yyyy-MM-dd HH:mm:ss");
        userInfo.setStartTime(s);
        userInfo.setEndTime(DateUtil.parse("2019-05-17 23:00:00", "yyyy-MM-dd HH:mm:ss"));

        PageHelper.startPage(page, pageSize);
        List<UserInfo> list = userService.findList(userInfo);
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(list);
        System.out.println("得到的结果为："+ JSON.toJSON(list));

        Map<String, Object> map = new HashMap();
        map.put("data", list);
        map.put("total",pageInfo.getTotal());
        return map;
    }


}
