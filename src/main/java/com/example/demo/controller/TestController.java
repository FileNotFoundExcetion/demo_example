package com.example.demo.controller;

import cn.hutool.core.io.FileTypeUtil;
import com.example.demo.common.CaffeineUtils;
import com.example.demo.mapper.UserMapper;
import com.example.demo.request.RequestFormRequest;
import com.example.demo.service.TestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
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

    @RequestMapping(value = "/file/upload",method = {RequestMethod.POST,RequestMethod.GET})
    public String upload(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "";
        }else {
            if(file.getSize()>5*1024*1024){


            }
        }
        String type = FileTypeUtil.getType(file.getInputStream());
        System.out.println("====="+type);
        String originalFilename = file.getOriginalFilename();
        System.out.println("====="+originalFilename);
      /*  InputStream inputStream = file.getInputStream();
        byte[] bytes = IoUtil.readBytes(inputStream);
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int v=bytes[i]&0xff;
            String hv=Integer.toHexString(v);//十六进制
            stringBuilder.append(hv);
        }*/
        //当前的时间戳
        //type:png
        //key:cpm
        //name：v2-88a8d10171dda46e479ed62808699a7f_r.jpg
  //      String s = stringBuilder.toString().toUpperCase(Locale.ROOT);
      //  System.out.println(s);
        return "success";
    }
//https://zhuanlan.zhihu.com/p/431392700

    @RequestMapping(value = "/file/upload/batch",method = {RequestMethod.POST,RequestMethod.GET})
    public String upload(@RequestParam("files") List<MultipartFile> files) throws IOException {
         if(!CollectionUtils.isEmpty(files)){
             files.forEach(file-> System.out.println(file.getOriginalFilename()));
         }
        return "";
    }
}
