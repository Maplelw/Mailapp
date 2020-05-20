package com.skpl.mailapp.protocol.smtp.states;

import com.skpl.mailapp.protocol.smtp.context.SmtpContext;

/**
 * @author maple
 * @Date 2020/5/10 9:13
 */
public class WaitForRcptToOrDataState implements SMTPState {

    private SmtpContext smtpContext;

    public WaitForRcptToOrDataState(SmtpContext smtpContext) {
        this.smtpContext = smtpContext;
    }

    @Override
    public void handle(String data) {
        if (data.toUpperCase().startsWith("RCPT TO")) {
            smtpContext.addRecipient(data.substring(("RCPT TO:").length()));
            smtpContext.sendData("250 OK-Add more RCPT");
            smtpContext.setCurrentState(new WaitForRcptToOrDataState(smtpContext));
        } else if (data.toUpperCase().startsWith("DATA")) {
            smtpContext.sendData("354 Start Mail. End with CRLF.CRLF ");
            smtpContext.setCurrentState(new GetSubject(smtpContext));
        } else if (data.toUpperCase().startsWith("QUIT")) {
            smtpContext.sendData("221 Bye");
            smtpContext.disconnect();
        } else {
            smtpContext.sendData("503 Error Command");
        }

    }
}
