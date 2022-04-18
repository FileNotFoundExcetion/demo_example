package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping("/test")
    public Object helloWorld(){
       return testService.helloWorld();
    }

    @GetMapping("/test01")
    public Object user(){
        User user  = new User();
        user.setCreateTime(LocalDateTime.now());
        user.setEmail("126@qq.com");
        return user;
    }
}
