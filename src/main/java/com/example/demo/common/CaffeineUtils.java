package com.example.demo.common;


import com.example.demo.util.CommonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CaffeineUtils {
public final static LoadingCache<String,Map<String, Map<String, Object>>> cache2 = Caffeine.newBuilder()
            .expireAfterWrite(1800, TimeUnit.DAYS)
            .maximumSize(1000)
            .build( key -> {
                String feature = FileUtil.readFile("/meta/licence_permission.json");
                TypeReference<Map<String, Map<String, Object>>> typeReference = new TypeReference<Map<String, Map<String, Object>>>(){};
                return CommonUtils.parse(feature, typeReference);
            });

    public static AsyncLoadingCache<String,Map<String, Map<String, Object>>> cache3 = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(100).executor(Executors.newSingleThreadExecutor())
            .buildAsync( key -> {
                String feature = FileUtil.readFile("/meta/licence_permission.json");
                TypeReference<Map<String, Map<String, Object>>> typeReference = new TypeReference<Map<String, Map<String, Object>>>(){};
                return CommonUtils.parse(feature, typeReference);
            });
    public static void main(String[] args) {
        // 支持直接get一组值，支持批量查找
      //  System.out.println(value1);
        System.out.println(cache2.get("licence_permission"));
     //   System.out.println(cache2.get("licence_permission"));
    }

}
