package com.skpl.mailapp.mapper;

import com.skpl.mailapp.dao.AdminDao;
import com.skpl.mailapp.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AdminDaoTest {
    @Autowired
    AdminDao adminDao;

    @Test
    public void deleteById() {
        //adminDao.deleteById("001");
    }

//    @Test
//    public void insert() {
//        System.out.println(adminDao);
//        adminDao.insert(new Admin("001","001","super"));
//    }
//
//    @Test
//    public void update() {
//        adminDao.update(new Admin("001","001","superstar"));
//    }
//
//    @Test
//    public void queryById() {
//        System.out.println(adminDao.queryById("001"));
//    }
//
//    @Test
//    public void queryAll() {
//        System.out.println(adminDao.queryAll(new Admin("001","001","superstar")));
//    }

}
