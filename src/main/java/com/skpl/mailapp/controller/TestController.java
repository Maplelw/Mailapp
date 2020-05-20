package com.skpl.mailapp.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 * @Author  maple
 * @Date 2020/5/7 15:49
 **/
@RestController
public class TestController {

    @GetMapping("ping")
    JSONObject ping() {
        JSONObject res = new JSONObject();
        res.put("status","success");
        return res;
    }
}
