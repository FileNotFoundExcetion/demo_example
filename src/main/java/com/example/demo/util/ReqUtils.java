package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicReference;

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
        AtomicReference<Boolean> webhookMessageDeliveryAtomicReference = new AtomicReference<>();
        webhookMessageDeliveryAtomicReference.set(true);
        // webhookMessageDeliveryAtomicReference.compareAndSet(true,true);
        System.out.println(webhookMessageDeliveryAtomicReference.get());
    }
}
