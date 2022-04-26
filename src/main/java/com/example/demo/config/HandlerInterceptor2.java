package com.example.demo.config;

import com.example.demo.util.ReqUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//order 里面的数字越小越优先执行
@Slf4j
@Order(2)
@Component
public class HandlerInterceptor2 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            log.info("HandlerInterceptor2========");
         /*   Map<String,String> map=new HashMap<>();
            map.put("code","200");
            map.put("code1","300");
            response.setStatus(403);
            response.setContentType("application/json");
            response.getWriter().write(JSONUtil.toJsonStr(map));*/
            String path = ReqUtils.getPath(request);
            log.info("==============:{}",path);
            return true;
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
