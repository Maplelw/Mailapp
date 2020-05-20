package com.skpl.mailapp.protocol.smtp.states;


import com.skpl.mailapp.protocol.smtp.context.SmtpContext;

public class WaitForMailFromState implements SMTPState {

    private SmtpContext smtpContext;

    public WaitForMailFromState(SmtpContext smtpContext) {
        this.smtpContext = smtpContext;
    }

    @Override
    public void handle(String data) {
        if (data.toUpperCase().startsWith("MAIL FROM")) {
            smtpContext.setMailFrom(data.substring(("Mail From:").length()));
            smtpContext.sendData("250 OK " + smtpContext.getMailFrom());
            smtpContext.setCurrentState(new WaitForRcptToState(smtpContext));
        } else if (data.toUpperCase().startsWith("QUIT")) {
            smtpContext.sendData("221 Bye");
            smtpContext.disconnect();
        } else {
            smtpContext.sendData("503 Error Command");
        }
    }
}
