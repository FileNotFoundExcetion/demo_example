package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String helloWorld(){
        System.out.println("=========="+"helloWorld");
        System.out.println("=========="+1/0);
        return "hello world";
    }
}
