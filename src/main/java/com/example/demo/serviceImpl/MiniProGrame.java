package com.example.demo.serviceImpl;

import com.example.demo.service.Count;
import org.springframework.stereotype.Component;

@Component("cpm.switch.basicBusinessDataManag")
public class MiniProGrame implements Count {
    @Override
    public int count() {
        return 100;
    }
}
