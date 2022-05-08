package com.example.demo.util;

import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
//占位符解析
public class Utils {
    public static String replaceXmlElementValue(String xmlContent, Map<String, String> map){
        if(!StringUtils.hasText(xmlContent)){
            return xmlContent;
        }
        //定义${开头 ，}结尾的占位符
        PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("${", "}");
        //调用替换
        return propertyPlaceholderHelper.replacePlaceholders(xmlContent, map::get);
    }

    public static void main(String[] args)  {
     /*   Map<String ,String>  map = new HashMap<>();
        map.put("id","123123123");
        map.put("name","如花");
        map.put("age","18");
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<tx>\n" +
                "  <txbody>\n" +
                "    <id>${id}</id>\n" +
                "    <name>${name}</name>\n" +
                "    <age>${age}</age>\n" +
                "  </txbody>\n" +
                "</tx>";
        String nxmlContent = replaceXmlElementValue(content, map);
        System.out.println(nxmlContent);*/
        Map<String, String> values = new HashMap<>();
        values.put("请求id", "100");
        values.put("租户名", "yunfeng");
        values.put("回执邮件", "1261928688@qq.com");
        String receiptContent =" 你好{{回执邮件}}，你的个人请求：{{请求id}}我们已收悉。";
        PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("{{", "}}");
        String s = propertyPlaceholderHelper.replacePlaceholders(receiptContent, values::get);
        System.out.println(s);
    }

}
