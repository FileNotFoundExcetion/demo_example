package com.example.demo.serviceImpl;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String helloWorld(){
        System.out.println("=========="+"helloWorld");
        return "hello world";
    }
}
