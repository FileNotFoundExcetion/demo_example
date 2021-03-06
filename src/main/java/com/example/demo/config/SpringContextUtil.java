package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }


    public static boolean containsBean(String name){
        return applicationContext.containsBean(name);
    }

}
