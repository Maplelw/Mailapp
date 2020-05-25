package com.skpl.mailapp.protocol;

import com.skpl.mailapp.protocol.pop3.server.JDBCUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.support.JdbcUtils;

@SpringBootTest
public class JDBCUtilsTest {

    @Test
    void getMailNumber() {
        JDBCUtils jdbcUtils = new JDBCUtils();
        System.out.println(jdbcUtils.getMailNumber("1711754407@qq.com"));
    }

    @Test
    void getMailById() {
        JDBCUtils jdbcUtils = new JDBCUtils();
        System.out.println(jdbcUtils.getMailTotalContent("1711754407@qq.com",2));
    }
}
