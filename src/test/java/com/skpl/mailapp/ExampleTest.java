package com.skpl.mailapp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ExampleTest {

    @Test
    public void test1() {
        int result = 6;
        assertEquals(6,result);
    }


}
