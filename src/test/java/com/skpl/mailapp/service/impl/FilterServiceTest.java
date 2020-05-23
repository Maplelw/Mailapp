package com.skpl.mailapp.service.impl;

import com.skpl.mailapp.entity.Filter;
import com.skpl.mailapp.service.FilterService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@SpringBootTest
public class FilterServiceTest {
    @Resource
    FilterService filterService;

    @Test
    void add() {
        Filter filter = new Filter(0, "1", "123@qq.com", "123");
        filterService.queryAllByLimit(0,1,filter);
    }
}
