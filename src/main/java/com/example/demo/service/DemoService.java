package com.example.demo.service;


import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class DemoService {

    @Resource
    UserInfoMapper userInfoMapper;

    public static Long userId = 190L;

    public void demo() {
        System.out.println("Insert--------------");

        for (int i = 1; i <= 100; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setAccount("Account" + i);
            userInfo.setPassword("pass" + i);
            userInfo.setUserName("name" + i);
            userInfo.setSysTime(new Date());
            userId++;
            try {
                userInfoMapper.insertSelective(userInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println("over..........");
    }

    public UserInfo getUserInfoByUserId(UserInfo id){
       return userInfoMapper.selectByPrimaryKey(id);
    }

    public List<UserInfo> findList(UserInfo userInfo){
        return userInfoMapper.findList(userInfo);
    }
}
