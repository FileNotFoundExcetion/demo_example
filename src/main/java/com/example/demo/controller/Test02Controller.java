package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class Test02Controller {

    @GetMapping("/test03")
    public void test(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("name","bob");
    }
}
