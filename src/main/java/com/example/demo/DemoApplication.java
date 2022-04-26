package com.example.demo;

import com.yomahub.tlog.core.enhance.AspectLogEnhance;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.demo.mapper")
@SpringBootApplication
public class DemoApplication {
	static {AspectLogEnhance.enhance();}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
