package com.example.demo.serviceImpl;

import com.example.demo.service.Count;

//@Order(2)
//@Component("cpm.switch.basicBusinessDataManag")
public class MiniProGrame implements Count {
    @Override
    public int count() {
        return 100;
    }
}
