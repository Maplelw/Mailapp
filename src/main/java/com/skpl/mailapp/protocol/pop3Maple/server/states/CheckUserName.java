package com.skpl.mailapp.protocol.pop3Maple.server.states;

import com.skpl.mailapp.protocol.pop3Maple.server.context.POP3Context;
import org.springframework.stereotype.Component;

/**
 * pop3协议验证用户名
 *
 * @author maple
 * @Date 2020/5/10 9:13
 */
@Component("checkUserName")
public class CheckUserName implements POP3State {

    private POP3Context pop3Context;

    public CheckUserName() {
    }

    public CheckUserName(POP3Context smtpContext) {
        this.pop3Context = smtpContext;
        pop3Context.sendData("+OK Welcome to skpl.com");
    }

    @Override
    public void handle(String data) {
        if (data.toLowerCase().startsWith("user")) {
//            UserService userService = (UserService) SpringUtil.getBean("userService");
//            String userMail = data.substring("user ".length());
//            System.out.println("输入账号：" + userMail);
//            if (userService.queryByEmail(userMail) != null) {
//                pop3Context.setUserMail(userMail);
//                pop3Context.sendData("+OK core mail");
//            } else {
//                pop3Context.sendData("501 error UserMail");
//            }
            pop3Context.setCurrentState(new CheckPassword(pop3Context));
        }
    }
}
