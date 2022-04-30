package com.example.demo.serviceImpl;

import com.example.demo.service.Count;
import com.example.demo.service.TestService;
import org.springframework.retry.RetryState;
import org.springframework.retry.policy.CircuitBreakerRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;

import javax.annotation.Resource;

public class TestCount implements Count {

    @Resource
    TestService testService;

    @Override
    public int count() {
        System.out.println("======"+fetchService());
        return 200;
    }

    public TestService fetchService(){
        return testService;
    }
    public static void main(String[] args) throws InterruptedException {
        RetryTemplate template = new RetryTemplate();
        CircuitBreakerRetryPolicy retryPolicy =
                new CircuitBreakerRetryPolicy(new SimpleRetryPolicy(3));
        retryPolicy.setOpenTimeout(5000);
        retryPolicy.setResetTimeout(20000);
        template.setRetryPolicy(retryPolicy);

        for (int i = 0; i < 10; i++) {
            try {
                Object key = "sss";
                boolean isForceRefresh = false;
                RetryState state = new DefaultRetryState(key, true);
                String result = template.execute(context -> {
                    System.out.println("retry count:" + context.getRetryCount());
                    Thread.sleep(2000);
                    throw new RuntimeException("timeout");
                }, context -> {
                    System.out.println("RecoveryCallback======"+context.getRetryCount());
                    return "default";
                }, state);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Thread.currentThread().join();
    }

}
