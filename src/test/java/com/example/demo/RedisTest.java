package com.example.demo;

import com.example.demo.service.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {

    @Resource
    Animal cat;

    @Test
    public void TestRedis(){
        //System.out.println(cat);
        cat.run();
    }
}
