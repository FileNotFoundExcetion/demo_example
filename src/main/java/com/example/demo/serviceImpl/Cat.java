package com.example.demo.serviceImpl;

import com.example.demo.service.Animal;
import org.springframework.stereotype.Service;

@Service
public class Cat implements Animal {
    private TestService testService;
    @Override
    public void run() {
        System.out.println("testService"+testService);
        System.out.println("cat run");
    }

    public void setTestService(TestService testService) {
        this.testService=testService;
    }
}
