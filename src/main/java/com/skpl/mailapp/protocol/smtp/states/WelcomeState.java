package com.skpl.mailapp.protocol.smtp.states;

import com.skpl.mailapp.protocol.smtp.context.SmtpContext;

/**
 * @author maple
 * @Date 2020/5/10 9:13
 */
public class WelcomeState implements SMTPState {

    private SmtpContext smtpContext;

    public WelcomeState(SmtpContext smtpContext) {
        this.smtpContext = smtpContext;
        smtpContext.sendData("220 Welcome to skpl.com SMTP server!");
    }

    @Override
    public void handle(String data) {
        if (data.toUpperCase().startsWith("HELO")) {
            //smtpContext.setHost(data.substring(5));
            smtpContext.sendData("250 Hello " + smtpContext.getHost() + ", I am glad to meet you");
            // 设置下一状态
            smtpContext.setCurrentState(new AuthLoginState(smtpContext));
        } else if (data.toUpperCase().startsWith("QUIT")) {
            smtpContext.sendData("221 Bye");
            smtpContext.disconnect();
        } else {
            smtpContext.sendData("502 Error Command");
        }
    }
}
