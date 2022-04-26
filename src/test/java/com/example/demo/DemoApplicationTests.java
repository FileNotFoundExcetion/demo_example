package com.example.demo;

import com.example.demo.config.SpringContextUtil;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
class DemoApplicationTests {

	@Resource
	UserMapper userMapper;

	@Resource
	UserServiceImpl userServiceImpl;

	@Test
	void test() {
		User user=new User();
		user.setAge(70);
		user.setEmail("www.@test.com");
		user.setName("xiaobai123");
		user.setCreateTime(LocalDateTime.now());
		userMapper.insert(user);
		System.out.println(user);
	}

	@Test
	public void test01(){
		userServiceImpl.test();
	}


	@Test
	public void testName(){
		System.out.println(SpringContextUtil.containsBean("cpm"));
	//	Count bean =(Count) SpringContextUtil.getBean("cpm.switch.basicBusinessDataManag");
	//	System.out.println(bean.count());
	}

}
