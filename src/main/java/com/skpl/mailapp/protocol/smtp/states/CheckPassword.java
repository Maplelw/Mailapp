package com.skpl.mailapp.protocol.smtp.states;

import com.skpl.mailapp.protocol.smtp.context.SmtpContext;
import com.skpl.mailapp.service.UserService;
import com.skpl.mailapp.util.Md5Util;
import com.skpl.mailapp.util.SpringUtil;

import javax.annotation.Resource;
import java.util.Base64;


/**
 * @author maple
 * @Date 2020/5/9 23:26
 */

public class CheckPassword implements SMTPState {

    private SmtpContext smtpContext;

    @Resource
    private UserService userService;

    public CheckPassword(SmtpContext smtpContext) {
        this.smtpContext = smtpContext;
    }

    @Override
    public void handle(String data) {
        if (data.toUpperCase().startsWith("QUIT")) {
            smtpContext.sendData("221 Bye");
            smtpContext.disconnect();
        } else {
            // 获取系统对应的密码
            UserService userService = (UserService) SpringUtil.getBean("userService");
            String password = Md5Util.md5(new String(Base64.getDecoder().decode(data)));
            String correctPassword = userService.queryByEmail(smtpContext.getUserMail()).getU_password();
            // 密码正确
            if (password.equals(correctPassword)) {
                smtpContext.sendData("235 Authentication successful");
                // 设置下一个状态
                smtpContext.setCurrentState(new WaitForMailFromState(smtpContext));
            }
            // 密码错误
            else {
                smtpContext.sendData("535 Error: authentication failed");
                // 设置下一个状态
                smtpContext.setCurrentState(new AuthLoginState(smtpContext));
            }
        }
    }
}
