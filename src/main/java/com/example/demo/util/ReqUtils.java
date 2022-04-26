package com.example.demo.util;

import cn.hutool.core.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class ReqUtils {
    public static String getPath(HttpServletRequest request) {
        String path = "";

        if (StringUtils.hasText(request.getContextPath())) {
            path += request.getContextPath();
        }

        if (StringUtils.hasText(request.getServletPath())) {
            path += request.getServletPath();
        }

        if (StringUtils.hasText(request.getPathInfo())) {
            path += request.getPathInfo();
        }
        return path;
    }

    public static void main(String[] args) {
        System.out.println(NumberUtil.compare(5L, 6L)>0);
    }
}
