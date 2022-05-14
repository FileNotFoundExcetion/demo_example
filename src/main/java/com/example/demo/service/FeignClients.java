package com.example.demo.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ip",url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php")
public interface FeignClients {
     @GetMapping
     ResponseEntity fetchIP(@RequestParam("format")String format,@RequestParam("ip")String ip);

}
