package com.skpl.mailapp;
import com.skpl.mailapp.util.Md5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ExampleTest {

    @Test
    public void test1() {
        System.out.println(Md5Util.md5("123"));
    }



}
