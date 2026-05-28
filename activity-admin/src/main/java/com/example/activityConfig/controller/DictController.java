package com.example.activityConfig.controller;

import com.example.activityConfig.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/dict")
public class DictController {

    @GetMapping("/departments")
    public Result<List<Map<String, Object>>> departments() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(dept("部门A", "联系人A"));
        list.add(dept("部门B", "联系人B"));
        list.add(dept("部门C", "联系人C"));
        list.add(dept("部门D", "联系人D"));
        return Result.success(list);
    }

    @GetMapping("/products")
    public Result<List<Map<String, Object>>> products() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(product("0302", "产品A"));
        list.add(product("PLON", "产品B"));
        list.add(product("0303", "产品C"));
        list.add(product("0305", "产品D"));
        list.add(product("0307", "产品E"));
        list.add(product("SFCL", "产品F"));
        return Result.success(list);
    }

    @GetMapping("/industries")
    public Result<List<String>> industries() {
        return Result.success(Arrays.asList("教育科研", "金融保险", "IT互联网", "制造业", "医疗卫生"));
    }

    @GetMapping("/organizations")
    public Result<List<Map<String, Object>>> organizations() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> org1 = new HashMap<>();
        org1.put("orgCode", "101");
        org1.put("orgName", "机构A");
        list.add(org1);
        Map<String, Object> org2 = new HashMap<>();
        org2.put("orgCode", "301");
        org2.put("orgName", "机构B");
        list.add(org2);
        return Result.success(list);
    }

    private Map<String, Object> dept(String name, String contact) {
        Map<String, Object> m = new HashMap<>();
        m.put("deptName", name);
        m.put("contactName", contact);
        return m;
    }

    private Map<String, Object> product(String code, String name) {
        Map<String, Object> m = new HashMap<>();
        m.put("productCode", code);
        m.put("productName", name);
        return m;
    }
}
