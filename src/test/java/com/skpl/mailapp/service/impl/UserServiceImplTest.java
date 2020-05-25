package com.skpl.mailapp.service.impl;

import com.skpl.mailapp.entity.User;
import com.skpl.mailapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void queryById() {
        System.out.println(userService.queryAll());
        System.out.println(userService);

    }

    @Test
    void queryByEmail() {
    }

    @Test
    void queryAllByLimit() {
    }

    @Test
    void queryAll() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void testQueryById() {
    }

    @Test
    void testQueryByEmail() {
    }

    @Test
    void testQueryAllByLimit() {
    }

    @Test
    void testQueryAll() {
    }

    @Test
    void testInsert() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void queryALLBySearch() {
        List<User> list = userService.queryALLBySearch("tozsy");
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    void testDeleteById() {
    }
}