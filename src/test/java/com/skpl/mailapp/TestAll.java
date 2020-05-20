package com.skpl.mailapp;

import com.skpl.mailapp.entity.Admin;
import com.skpl.mailapp.mapper.AdminDaoTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({ExampleTest.class, AdminDaoTest.class})
public class TestAll {

}
