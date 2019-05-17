package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.DemoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Resource
	UserInfoMapper userInfoMaper;

	@Resource
	DemoService demoService;

	@Test
	public void contextLoads() {
		demoService.demo();
	}

	@Test
	public void findList(){
		PageHelper.startPage(4, 20);
		List<UserInfo> list = demoService.findList(null);
		PageInfo<UserInfo> page = new PageInfo<UserInfo>(list);
		System.out.println("得到的结果为："+JSON.toJSON(list));
	}




}
