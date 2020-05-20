package com.skpl.mailapp.protocol.smtp.states;

import com.skpl.mailapp.protocol.smtp.context.SmtpContext;
import com.skpl.mailapp.service.UserService;
import com.skpl.mailapp.util.SpringUtil;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * @author maple
 * @Date 2020/5/10 9:13
 */
public class CheckUserName implements SMTPState {

    private SmtpContext smtpContext;

    public CheckUserName() {
    }

    public CheckUserName(SmtpContext smtpContext) {
        this.smtpContext = smtpContext;
    }

    @Override
    public void handle(String data) {
        if (data.toUpperCase().startsWith("QUIT")) {
            smtpContext.sendData("221 Bye");
            smtpContext.disconnect();

        } else {
            UserService userService = (UserService) SpringUtil.getBean("userService");
            String userMail = new String(Base64.getDecoder().decode(data));
            System.out.println("输入账号：" + userMail);
            if (userService.queryByEmail(userMail) != null) {
                smtpContext.setUserMail(userMail);
                smtpContext.sendData("334 OK mail exist");
            } else {
                smtpContext.sendData("501 error UserMail");
            }
            smtpContext.setCurrentState(new CheckPassword(smtpContext));
        }
    }
}
