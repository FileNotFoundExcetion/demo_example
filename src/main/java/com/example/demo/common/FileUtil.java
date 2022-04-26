package com.example.demo.common;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    public static String readFile(String filename){
        ClassPathResource classPathResource=new ClassPathResource(filename);
        InputStream inputStream = classPathResource.getStream();
        Assert.notNull(inputStream,"inputStream exception ");
        return IoUtil.read(inputStream, StandardCharsets.UTF_8);
    }

}
