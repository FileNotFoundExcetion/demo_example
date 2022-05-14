package com.example.demo.serviceImpl;

import com.example.demo.service.Animal;
import org.springframework.stereotype.Service;

//@Primary
@Service
public class Dog implements Animal {
    @Override
    public void run() {
        System.out.println("dog run");
    }
}
