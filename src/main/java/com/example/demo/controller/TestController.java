package com.example.demo.controller;

import com.example.demo.common.CaffeineUtils;
import com.example.demo.mapper.UserMapper;
import com.example.demo.request.RequestFormRequest;
import com.example.demo.service.TestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class TestController {
     @Resource
     private UserMapper userMapper;
     @Resource
     private TestService testService;

    @PostMapping("/test")
    public Object helloWorld(@RequestBody RequestFormRequest request) throws JsonProcessingException {
        List<RequestFormRequest.CustomerFieldData> customFieldDatas = request.getCustomFieldData();
        Map<String,Object> customFieldDataMap = customFieldDatas.stream().filter(Objects::nonNull).collect(Collectors.toMap(RequestFormRequest.CustomerFieldData::getName, RequestFormRequest.CustomerFieldData::getValue));
        System.out.println(new ObjectMapper().writeValueAsString(customFieldDataMap));
        System.out.println(new ObjectMapper().writeValueAsString(request.getCustomFieldData()));
        System.out.println("===========");
        BeanMap beanMap = BeanMap.create(request);
        System.out.println(beanMap);
        System.out.println(beanMap.get("formId"));
        return testService.helloWorld();
    }

    @GetMapping("/test01/{id}")
    public Object user(@PathVariable("id") String id){
        return userMapper.selectList(null);
    }

    @GetMapping("/cpm/api/projects/document")
    public Object user01() throws Exception {
        log.info("============:{}","log1");
        log.info("============:{}","log2");
        return CaffeineUtils.cache2.get("licence_permission");
    }

}
