package com.skpl.mailapp.mapper;

import com.skpl.mailapp.entity.Mail;
import com.skpl.mailapp.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@SpringBootTest
public class MailDaoTest {
    @Resource
    MailService mailService;

    @Test
    void filter() {
        int i=0;
        for (Mail mail :mailService.queryAllExFilter("1711754407@qq.com")) {
            i++;
        }
        System.out.println(i);
    }
}
