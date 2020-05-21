package com.skpl.mailapp.mapper;

import com.skpl.mailapp.dao.UserDao;
import com.skpl.mailapp.entity.User;
import com.skpl.mailapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    void queryById() {
        User user = userDao.queryById(1);
        System.out.println(user.getU_time());
        System.out.println("==============");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(user.getU_time()));
        System.out.println();

    }

    @Test
    void insert() {
    }
}
