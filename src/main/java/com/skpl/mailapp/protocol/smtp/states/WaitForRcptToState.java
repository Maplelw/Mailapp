package com.skpl.mailapp.protocol.smtp.states;

import com.skpl.mailapp.protocol.smtp.context.SmtpContext;
import com.skpl.mailapp.service.UserService;
import com.skpl.mailapp.util.SpringUtil;

/**
 * @author maple
 * @Date 2020/5/10 9:13
 */
public class WaitForRcptToState implements SMTPState {

    private SmtpContext smtpContext;

    public WaitForRcptToState(SmtpContext smtpContext) {
        this.smtpContext = smtpContext;
    }

    @Override
    public void handle(String data) { ;
        if (data.toUpperCase().startsWith("RCPT TO")) {
            UserService userService = (UserService) SpringUtil.getBean("userService");
            if(userService.queryByEmail(data.substring(("RCPT TO:").length()))!=null) {
                smtpContext.addRecipient(data.substring(("RCPT TO:").length()));
                smtpContext.sendData("250 OK completed");
                smtpContext.setCurrentState(new WaitForRcptToOrDataState(smtpContext));
            } else {
                smtpContext.sendData("502 error recipient");
            }
        } else if (data.toUpperCase().startsWith("QUIT")) {
            smtpContext.sendData("221 Bye");
            smtpContext.disconnect();
        } else {
            smtpContext.sendData("503 Error Command");
        }

    }
}
