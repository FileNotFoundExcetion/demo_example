package com.example.demo.common;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class IPUtils {
        /**
         * 在服务中获取用户的ip地址
         * @param request
         * @return
         */
        public static String getIpAddressAtService(HttpServletRequest request) {
            String ipAddress;
            try {
                log.info("x-forwarded-for:{} Proxy-Client-IP:{} WL-Proxy-Client-IP:{} x-forwarded-for:{} remoteAddress:{}",request.getHeader("x-forwarded-for"),request.getHeader("Proxy-Client-IP"),request.getHeader("WL-Proxy-Client-IP"),request.getHeader("x-forwarded-for"),request.getRemoteAddr());
                ipAddress = request.getHeader("x-forwarded-for");
                if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getHeader("Proxy-Client-IP");
                }
                if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                    ipAddress = request.getRemoteAddr();
                    if (ipAddress.equals("127.0.0.1")||ipAddress.equals("0:0:0:0:0:0:0:1")) {
                        // 根据网卡取本机配置的IP
                        InetAddress inet = null;
                        try {
                            inet = InetAddress.getLocalHost();
                        } catch (UnknownHostException e) {
                            log.error("resolve IP error",e);
                        }
                        ipAddress = inet.getHostAddress();
                    }
                }
                // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
                if (ipAddress != null && ipAddress.indexOf(",") > 0) { // "***.***.***.***".length()
                       // = 15
                   ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            } catch (Exception e) {
                ipAddress="";
            }
            return ipAddress;
        }

}
