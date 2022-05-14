package com.example.demo.serviceImpl;

import com.example.demo.abstracts.Test;
import org.springframework.stereotype.Component;

@Component
public class Test01 extends Test {

    public void test(){
     System.out.println(this.testService);
    }

}
