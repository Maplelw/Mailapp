package com.skpl.mailapp.protocol.smtp.states;

import com.skpl.mailapp.protocol.smtp.context.SmtpContext;

/**
 * @author maple
 */
public class AuthLoginState implements SMTPState {

    private SmtpContext smtpContext;

    public AuthLoginState(SmtpContext smtpContext) {
        this.smtpContext = smtpContext;
    }

    @Override
    public void handle(String data) {
        if (data.toUpperCase().startsWith("AUTH LOGIN")) {
            //smtpContext.setHost(data.substring(5));
            smtpContext.sendData("334 dXNlcm5hbWU6 ");
            //Set new state and Pas by Ref to WaitForMailFromState.
            smtpContext.setCurrentState(new CheckUserName(smtpContext));
        } else if (data.toUpperCase().startsWith("QUIT")) {
            smtpContext.sendData("221 Bye");
            smtpContext.disconnect();
        } else {
            smtpContext.sendData("503 Error Command");
        }
    }
}