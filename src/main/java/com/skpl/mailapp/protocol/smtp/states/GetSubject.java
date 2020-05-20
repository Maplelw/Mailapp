package com.skpl.mailapp.protocol.smtp.states;

import com.skpl.mailapp.protocol.smtp.context.SmtpContext;


/**
 * @author maple
 * @Date 2020/5/17 17:10
 */
public class GetSubject implements SMTPState {

    private SmtpContext smtpContext;

    public GetSubject(SmtpContext smtpContext) {
        this.smtpContext = smtpContext;
    }

    @Override
    public void handle(String data) {
        if (data.toUpperCase().startsWith("SUBJECT")) {
            smtpContext.setSubject(data.substring(("SUBJECT:").length()));
            smtpContext.sendData("354 OK subject");
            System.out.println(smtpContext.getSubject());
            //Set new state and Pas by Ref to WaitForMailFromState.
            smtpContext.setCurrentState(new ReceivingDataState(smtpContext));
        } else if (data.toUpperCase().startsWith("QUIT")) {
            smtpContext.sendData("221 Bye");
            smtpContext.disconnect();
        } else {
            smtpContext.sendData("503 Error Command");
        }
    }
}
