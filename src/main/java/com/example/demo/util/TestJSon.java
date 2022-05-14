package com.example.demo.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJSon {
    public static void main(String[] args) throws Exception {
        String json="{\n" +
                "            \"unprocessed\":\"{{回执邮件}}，\\n\\n你好，你的个人请求：{{请求id}}我们已收悉。你的请求内容如下：\\n\\n【字段】：【值】\\n\\n如果有了下一步进展，我们将会邮件告知你。\\n\\n{{租户名}}\",\n" +
                "            \"processed\":\"{{回执邮件}}，\\n\\n你好，你的个人请求：{{请求id}}我们已收悉，目前状态变更为：【状态】\\n\\n{{租户名}}\"\n" +
                "        }";
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.get("processed"));

        String json2="[{\"name\":\"requestUserName\",\"description\":\"\",\"isSystem\":true,\"type\":\"input\",\"label\":\"姓名\",\"required\":true},{\"name\":\"requestRole\",\"description\":\"\",\"isSystem\":true,\"label\":\"请求角色\",\"type\":\"select\",\"options\":[\"客户\",\"员工\",\"合作伙伴\"]},{\"name\":\"requestType\",\"description\":\"\",\"isSystem\":true,\"label\":\"请求类型\",\"type\":\"select\",\"options\":[\"查阅个人信息\",\"复制个人信息\",\"更正、扩充个人信息\",\"删除个人信息\",\"撤回授权同意\",\"注销账号\",\"其他数据权利问题\"],\"required\":true},{\"name\":\"receiptEmail\",\"description\":\"\",\"isSystem\":true,\"type\":\"input\",\"label\":\"回执邮件\",\"required\":true,\"receiptContent\":{\"unprocessed\":\"{{回执邮件}}：\\n你好，你的个人请求：{{个人请求id}}，我们已收悉。\\n\\n你请求的内容如下：\\n\\n{{img}}\\n\\n如有进展，我们将邮件告知。\",\"processed\":\"{{回执邮件}}：\\n你好，你的个人请求：{{个人请求id}}，我们已收悉。\\n\\n你请求的内容如下：\\n\\n{{img}}\\n\\n目前请求状态变更为：{{个人请求状态}}。\"},\"triggerPoint\":[\"submit\",\"processing\",\"rejected\",\"completed\"]}]";
        JsonNode jsonNode2 = objectMapper.readTree(json2);
        System.out.println(jsonNode2.get("triggerPoint"));
    }
}
