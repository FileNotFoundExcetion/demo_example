package com.example.demo.abstracts;

import com.example.demo.serviceImpl.TestService;

import javax.annotation.Resource;

public abstract class Test {
    @Resource
    protected TestService testService;


}
