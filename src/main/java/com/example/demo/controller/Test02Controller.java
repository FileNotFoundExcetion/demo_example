package com.example.demo.controller;

import com.example.demo.common.IPUtils;
import com.example.demo.util.IPUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class Test02Controller {

    @GetMapping("/test03")
    public void test(HttpServletRequest httpServletRequest){
        System.out.println(IPUtil.getIpFromRequest(httpServletRequest));
        String ipAddressAtService = IPUtils.getIpAddressAtService(httpServletRequest);
        System.out.println(ipAddressAtService);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("name","bob");
    }
}
