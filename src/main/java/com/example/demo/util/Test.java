package com.example.demo.util;

import org.springframework.util.PropertyPlaceholderHelper;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private static final String placeholderPrefix="\\r\\n";
    public static void main(String[] args) {
        String json="{\n" +
                "            \"unprocessed\":\"{{回执邮件}}，\\n\\n你好，你的个人请求：{{请求id}}我们已收悉。你的请求内容如下：\\n\\n【字段】：【值】\\n\\n如果有了下一步进展，我们将会邮件告知你。\\n\\n{{租户名}}\",\n" +
                "            \"processed\":\"{{回执邮件}}，\\n\\n你好，你的个人请求：{{请求id}}我们已收悉，目前状态变更为：【状态】\\n\\n{{租户名}}\"\n" +
                "        }";

      //  Map<String, String> receiptContentMap = JsonUtil.strToObj(json, new TypeReference<Map<String, String>>() {});
      //  System.out.println(receiptContentMap.get("processed"));

      //  String message="{{回执邮件}}：\\n你好，你的个人请求：{{个人请求id}}，我们已收悉。\\n\\n你请求的内容如下：\\n\\n{{img}}\\n\\n如有进展，我们将邮件告知。\\n\\n";
        String message2="{{回执邮件}}：\n" +
                "你好，你的个人请求：{{个人请求id}}，我们已收悉。\n" + "\n" +
                "你请求的内容如下：\n\n" + "{{img}}\n" +
                "\n" +
                "如有进展，我们将邮件告知。";
        StringBuilder stringBuilder=new StringBuilder();
        String s1 = message2.replaceAll("\\n", "?");
        String[] split = s1.split("\\?");
        for (String s : split) {
            if(s.contains("img")){
                stringBuilder.append(composeImage(s));
                stringBuilder.append(System.lineSeparator());
            }else {
                stringBuilder.append(composeP(s));
                stringBuilder.append(System.lineSeparator());
            }
        }
        System.out.println(stringBuilder);
        Map<String, String> values = new HashMap<>();
        values.put("个人请求id", "100");
        values.put("img", "www.baidu.com");
        values.put("回执邮件", "1261928688@qq.com");
        PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("{{", "}}");
        String s = propertyPlaceholderHelper.replacePlaceholders(stringBuilder.toString(), values::get);
        System.out.println("===========");
        System.out.println(s);
    }

  public static String composeP(String message){
        return "<p>"+ message + "</p>";
  }

    public static String composeImage(String image){
        return "<img  " +
                "src=" + "\"" + image + "\"" +
                " " +
                "style=" +
                "\"" + "width: 50%; max-width: 300px;" + "\"" +
                ">";
    }
}
