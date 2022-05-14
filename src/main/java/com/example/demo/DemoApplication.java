package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.example.demo.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.demo.service")
public class DemoApplication {
	//static {AspectLogEnhance.enhance();}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
