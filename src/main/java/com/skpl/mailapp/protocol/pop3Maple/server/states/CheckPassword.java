package com.skpl.mailapp.protocol.pop3Maple.server.states;

import com.skpl.mailapp.protocol.pop3Maple.server.context.POP3Context;
import com.skpl.mailapp.service.UserService;
import com.skpl.mailapp.util.Md5Util;
import com.skpl.mailapp.util.SpringUtil;


/**
 * poo3协议验证密码状态
 *
 * @author maple
 * @Date 2020/5/9 23:26
 */

public class CheckPassword implements POP3State {

    private POP3Context pop3Context;

    public CheckPassword(POP3Context pop3Context) {
        this.pop3Context = pop3Context;
    }

    @Override
    public void handle(String data) {
        if (data.toLowerCase().startsWith("pass")) {
            // 获取系统对应的密码
            UserService userService = (UserService) SpringUtil.getBean("userService");
            String password = data.substring("pass ".length());
            String passwordCode = Md5Util.md5(password);
            String correctPassword = userService.queryByEmail(pop3Context.getUser().getU_email()).getU_password();
            // 密码正确
            if (passwordCode.equals(correctPassword)) {
                pop3Context.sendData("+OK 3 message(s)");
                // 设置下一个状态
                pop3Context.setCurrentState(new Operation(pop3Context));
            }
            // 密码错误
            else {
                pop3Context.sendData("error pass");
                // 设置下一个状态
                pop3Context.setCurrentState(new CheckUserName(pop3Context));
            }
        }
    }
}
