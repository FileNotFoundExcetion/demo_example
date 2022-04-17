package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@SpringBootTest
class DemoApplicationTests {

	@Resource
	UserMapper userMapper;

	@Test
	void test() {
		User user=new User();
		user.setAge(50);
		user.setEmail("www.@test.com");
		user.setName("xiaobai123");
		user.setCreateTime(LocalDateTime.now());
		userMapper.insert(user);
		System.out.println(user);
	}

}
