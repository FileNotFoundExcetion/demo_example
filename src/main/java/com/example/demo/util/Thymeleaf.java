package com.example.demo.util;

import cn.hutool.core.convert.impl.StringConverter;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Thymeleaf {
    public static void main(String[] args) {
   /*     TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());
//假设我们引入的是Beetl引擎，则：
        Template template = engine.getTemplate("Hello ${name}");
//Dict本质上为Map，此处可用Map
        String result = template.render(Dict.create().set("name", "Hutool"));
        System.out.println(result);
        String message="{{回执邮件}} 你好，你的个人请求：{{请求id}}我们已收悉。";
        Pattern compile = Pattern.compile("\\{\\{(.*?)\\}\\}");
        Matcher matcher = compile.matcher(message);
        while (matcher.find()){
            String group = matcher.group();
            System.out.println(group);
        }*/
        Map<String, String> values = new HashMap<>();
        values.put("{{请求id}}", "100");
        values.put("{{租户名}}", "yunfeng");
        values.put("{{回执邮件}}", "1261928688@qq.com");
        String[] split = "1,2,3,".split(",");
        System.out.println(split.length);
        String receiptContent = "{{回执邮件}} 你好，你的个人请求：{{请求id}}我们已收悉。";
        Pattern compile = Pattern.compile("\\{\\{(.*?)\\}\\}");
        Matcher matcher = compile.matcher(receiptContent);
        while (matcher.find()) {
            String group = matcher.group();
            receiptContent = receiptContent.replace(group, values.get(group));
        }
        System.out.println(receiptContent);
        System.out.println(1048576/1024/1024);
        System.out.println(1024 * 1024);
        String testStr = "test中文";
        byte[] key = "password".getBytes();
        HMac mac = new HMac(HmacAlgorithm.HmacMD5, key);
        String macHex1 = mac.digestHex(testStr);
        System.out.println(macHex1);
        Object s= "sss";
        StringConverter stringConverter=new StringConverter();
        String convert = stringConverter.convert(s, "");
        System.out.println(convert);
    }
}
