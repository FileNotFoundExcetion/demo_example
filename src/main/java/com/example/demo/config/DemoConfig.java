package com.example.demo.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.example.demo.service.Count;
import com.example.demo.serviceImpl.MiniProGrame;
import com.example.demo.serviceImpl.TestCount;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.CircuitBreakerRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

//
@EnableRetry
@Configuration
public class DemoConfig {

    private static final int MAX_POOL_SIZE = 30;

    private static final int CORE_POOL_SIZE = 20;

    @Bean
    @ConditionalOnProperty(name="disable",prefix = "retry",havingValue = "true")
    public Count count01(){
        return new MiniProGrame();
    }

    @Bean
    @ConditionalOnMissingBean(name = "count01")
    public Count count(){
        return new TestCount();
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(2000L);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryTemplate.setRetryPolicy(retryPolicy);
       // retryTemplate.registerListener(new DefaultListenerSupport());
        return retryTemplate;
    }


    @Bean
    public RetryTemplate retryTemplate1(){
        RetryTemplate retryTemplate =  new  RetryTemplate();
        CircuitBreakerRetryPolicy retryPolicy =
                new  CircuitBreakerRetryPolicy(new SimpleRetryPolicy( 4 ));
        FixedBackOffPolicy fixedBackOffPolicy =  new  FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod( 300 );
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        retryPolicy.setOpenTimeout( 1500 );
        retryPolicy.setResetTimeout( 2000 );
        retryTemplate.setRetryPolicy(retryPolicy);
        return retryTemplate;
    }

    @PostConstruct
    private void initRules() throws Exception {
        FlowRule rule = new FlowRule();
        rule.setResource("test.hello");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);   // 每秒调用最大次数为 1 次
        List<FlowRule> rules = new ArrayList<>();
        rules.add(rule);
        // 将控制规则载入到 Sentinel
        com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager.loadRules(rules);
        DegradeRule degradeRule=new DegradeRule();
        degradeRule.setResource("test");
        degradeRule.setCount(1);
        List<DegradeRule> degradeRules = new ArrayList<>();
        degradeRules.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRules);
    }
    @Bean("asyncTaskExecutor")
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        asyncTaskExecutor.setThreadNamePrefix("cpm-retry");
        asyncTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }
}
