package com.example.demo.util;

import com.github.rholder.retry.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Retry {
        public static void main(String[] args) {
            //定义请求实现
            Callable<Boolean> callable = () -> {
                // do something useful here
                log.info("call...");
                throw new RuntimeException();
            };

            //定义重试机制
            Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                    // retryIf 重试条件
                    .retryIfResult(Objects::isNull)
                    //设置异常重试源
                    .retryIfExceptionOfType(IOException.class)
                    .retryIfRuntimeException()
                    .retryIfResult(res -> res = false)
                    //设置等待间隔时间
                    .withWaitStrategy(WaitStrategies.fixedWait(3, TimeUnit.SECONDS))
                    //设置最大重试次数
                    .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                    .build();
            try {
                retryer.call(callable);
            } catch (RetryException | ExecutionException e) {
                e.printStackTrace();
            }

        }


        public void test1(){
            //永远重试
            //创建一个可以永远重试的 Retryer，
            // 在每次失败的重试之后，以递增指数的间隔等待直到最多5分钟。5分钟后，每隔5分钟重试一次。
            Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                    .retryIfExceptionOfType(IOException.class)
                    .retryIfRuntimeException()
                    .withWaitStrategy(WaitStrategies.exponentialWait(100, 5, TimeUnit.MINUTES))
                    //时间限制 : 某次请求不得超过2s , 类似: TimeLimiter timeLimiter = new SimpleTimeLimiter();
                    .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))
                    .withStopStrategy(StopStrategies.neverStop())
                    .build();

            //创建一个可以永远重试的 Retryer，
            // 在每次失败的重试之后，增加斐波那契回退间隔，
            // 直到最多2分钟。2分钟后，每隔2分钟重试一次。

            Retryer<Boolean> retryer1 = RetryerBuilder.<Boolean>newBuilder()
                    .retryIfExceptionOfType(IOException.class)
                    .retryIfRuntimeException()
                    .withWaitStrategy(WaitStrategies.fibonacciWait(100, 2, TimeUnit.MINUTES))
                    .withStopStrategy(StopStrategies.neverStop())
                    .build();
        }
    public Boolean test() throws Exception {
        //定义重试机制
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                //retryIf 重试条件
                .retryIfException()
              //  .retryIfExceptionOfType(Exception.class)
            //    .retryIfException(Predicates.equalTo(new Exception()))
             //   .retryIfResult(Predicates.equalTo(false))
                //等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                //停止策略 : 尝试请求6次
                .withStopStrategy(StopStrategies.stopAfterAttempt(6))
                //时间限制 : 某次请求不得超过2s , 类似: TimeLimiter timeLimiter = new SimpleTimeLimiter();
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))
                //自定义阻塞策略：自旋锁
                .withBlockStrategy(new SpinBlockStrategy())
                //自定义重试监听器
                .withRetryListener(new RetryLogListener())
                .build();

        //定义请求实现
        Callable<Boolean> callable = new Callable<Boolean>() {
            int times = 1;
            @Override
            public Boolean call() throws Exception {
                log.info("call times={}", times);
                times++;
                if (times == 2) {
                    throw new NullPointerException();
                } else if (times == 3) {
                    throw new Exception();
                } else if (times == 4) {
                    throw new RuntimeException();
                } else if (times == 5) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        //利用重试器调用请求
        return  retryer.call(callable);
    }
}
