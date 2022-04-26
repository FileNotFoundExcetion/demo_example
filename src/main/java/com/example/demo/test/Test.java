package com.example.demo.test;

import com.example.demo.common.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        String field="[{\"name\":\"requestUserName\",\"description\":\"\",\"isSystem\":true,\"type\":\"input\",\"label\":\"姓名\",\"required\":true},{\"name\":\"requestRole\",\"description\":\"\",\"isSystem\":true,\"label\":\"请求角色\",\"type\":\"select\",\"options\":[\"客户\",\"员工\",\"合作伙伴\"]},{\"name\":\"requestType\",\"description\":\"\",\"isSystem\":true,\"label\":\"请求类型\",\"type\":\"select\",\"options\":[\"查阅个人信息\",\"复制个人信息\",\"更正、扩充个人信息\",\"删除个人信息\",\"撤回授权同意\",\"注销账号\",\"其他数据权利问题\"],\"required\":true},{\"name\":\"custom_1649988604286\",\"type\":\"checkbox\",\"description\":\"\",\"isSystem\":false,\"label\":\"您想了解的？\",\"options\":[\"CPM\",\"DATAHUB\"],\"required\":true},{\"name\":\"custom_1649988632919\",\"type\":\"textarea\",\"description\":\"\",\"isSystem\":false,\"label\":\"描述\"}]";
        List<Map<String, Object>> fieldConfigList= JsonUtil.strToObj(field, List.class, Map.class);
        System.out.println(fieldConfigList);
        System.out.println("==========");
        Map<String, Boolean> configMap = fieldConfigList.stream().collect(Collectors.toMap(it -> it.get("name").toString(), it -> (Boolean) Optional.ofNullable(it.get("required")).orElse(false)));
        System.out.println(configMap);

    }
}
