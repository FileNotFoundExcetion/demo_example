package com.example.demo;

import com.example.demo.service.FeignClients;
import com.example.demo.serviceImpl.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootTest
public class RetryTest {

    @Resource
    RetryTemplate retryTemplate;

    @Resource
    TestService testService;

    @Test
    public void Test(){

      boolean result=retryTemplate.execute((retryContext)->{
            testService.helloWorld();
            return true;
        },(recover)->{
           Throwable lastThrowable = recover.getLastThrowable();
           log.error("error"+lastThrowable.getMessage());
           return false;
      });
      System.out.println("-----------"+result);
    }

    @Resource
    ThreadPoolTaskExecutor asyncTaskExecutor;

    @Test
    public void testasync() throws InterruptedException {
        for (int i=0;i<3;i++){
            CompletableFuture.supplyAsync(()->
                                retryTemplate.execute((retryContext)->{
                                testService.helloWorld();
                                return true;
                            },(recover)->{
                                Throwable lastThrowable = recover.getLastThrowable();
                                log.error("error"+lastThrowable.getMessage());
                                return false;
                            }),asyncTaskExecutor)
                        .thenCompose(result -> CompletableFuture.runAsync(() -> {
                        System.out.println("========="+result);
                    }));
        }
        Thread.currentThread().join();
    }
    @Autowired
    private FeignClients feignClients;
    @Test
    public void feign(){
        ResponseEntity json = feignClients.fetchIP("json", "218.192.3.42");
        System.out.println(json);
    }
}
