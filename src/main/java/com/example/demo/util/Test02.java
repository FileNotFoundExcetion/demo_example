package com.example.demo.util;

import cn.hutool.core.convert.impl.StringConverter;
import org.springframework.util.StringUtils;

public class Test02 {
    private final String placeholderPrefix="{{";
    private final String placeholderSuffix="}}";
    private final  String simplePrefix="{";
    public static void main(String[] args) {
        String receiptContent =" 你好{{回执邮件}}，你的个人请求：{{请求id}}我们已收悉。";
        System.out.println(receiptContent.indexOf("{{"));
      //  System.out.println(new Test02().findPlaceholderEndIndex(receiptContent, receiptContent.indexOf("{{")));
        StringConverter attachNumberValue=new StringConverter();
        String value = attachNumberValue.convert(null, "0");
        System.out.println(value);
    }

    private  int findPlaceholderEndIndex(CharSequence buf, int startIndex) {
        int index = startIndex + this.placeholderPrefix.length();
        int withinNestedPlaceholder = 0;

        while(index < buf.length()) {
            if (StringUtils.substringMatch(buf, index, this.placeholderSuffix)) {
                if (withinNestedPlaceholder <= 0) {
                    return index;
                }

                --withinNestedPlaceholder;
                index += this.placeholderSuffix.length();
            } else if (StringUtils.substringMatch(buf, index, this.simplePrefix)) {
                ++withinNestedPlaceholder;
                index += this.simplePrefix.length();
            } else {
                ++index;
            }
        }

        return -1;
    }

}

