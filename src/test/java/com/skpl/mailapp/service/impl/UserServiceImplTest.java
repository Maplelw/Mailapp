package com.skpl.mailapp.service.impl;

import com.skpl.mailapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}