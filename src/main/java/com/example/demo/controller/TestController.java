package com.example.demo.controller;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.example.demo.common.CaffeineUtils;
import com.example.demo.common.IPUtils;
import com.example.demo.mapper.UserMapper;
import com.example.demo.request.RequestFormRequest;
import com.example.demo.serviceImpl.TestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class TestController {
    @Value("${access.limit:3}")
    private Long limit;

    private static final String IP_WHITE="IP:WHITE:";

    private static final String IP_BLACK="IP:BLACK:";
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
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("current") String current, HttpServletRequest request) throws IOException {
        String type = FileTypeUtil.getType(file.getInputStream());
        System.out.println("====="+type);
        String originalFilename = file.getOriginalFilename();
        System.out.println("====="+originalFilename);
        String remoteAddr = request.getRemoteAddr();
        String clientIP = ServletUtil.getClientIP(request, null);
        System.out.println("========"+remoteAddr);
        //https://blog.csdn.net/qq_34203492/article/details/113260288
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
   @Resource
   private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/hello")
    public Boolean judgeIp(HttpServletRequest request){
        String ipAddressAtService = IPUtils.getIpAddressAtService(request);
        System.out.println(ipAddressAtService);
        return judgeIPIsBlack(request.getRemoteAddr());
    }

    public Boolean judgeIPIsBlack(String ip){
        if(StringUtils.isEmpty(ip)){
            return false;
        }
        Boolean blackIp = redisTemplate.hasKey(IP_BLACK + ip);
        if(blackIp!=null&& blackIp){
            return false;
        }
        Long increment = redisTemplate.opsForValue().increment(IP_WHITE+ip, 1);
        boolean present = Optional.ofNullable(increment).isPresent();
        if(present){
            if(increment>limit){
                Long expire = Optional.ofNullable(redisTemplate.getExpire(IP_BLACK + ip)).orElse(0L);
                if(expire<0){
                    redisTemplate.opsForValue().set(IP_BLACK + ip,ip,1, TimeUnit.HOURS);
                }
                log.info("ip :{} 次数已达上限:{}",ip,increment);
                return false;
            }else {
                Long expire = Optional.ofNullable(redisTemplate.getExpire(IP_WHITE + ip)).orElse(0L);
                if(expire<=0){
                    redisTemplate.expireAt(IP_WHITE+ip, Instant.now().plusSeconds(3600));
                }
            }
        }
        return true;
    }
}
