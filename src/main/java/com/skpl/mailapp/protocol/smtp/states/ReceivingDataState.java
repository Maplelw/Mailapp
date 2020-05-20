package com.skpl.mailapp.protocol.smtp.states;

import com.skpl.mailapp.entity.Mail;
import com.skpl.mailapp.protocol.smtp.context.SmtpContext;
import com.skpl.mailapp.service.MailService;
import com.skpl.mailapp.util.SpringUtil;

import java.util.Date;
import java.util.Random;

/**
 * @author maple
 * @Date 2020/5/10 9:13
 */
public class ReceivingDataState implements SMTPState {

    private SmtpContext smtpContext;
    private StringBuilder stringBuilder;

    public ReceivingDataState(SmtpContext smtpContext) {
        this.smtpContext = smtpContext;
        this.stringBuilder = new StringBuilder();
    }

    @Override
    public void handle(String data) {
        //Needs enter
        if (data != null) {
            smtpContext.addTextToBody(data);
        }

        if (smtpContext.getTextBodyData() != null) {
            StringBuilder sb;
            sb = smtpContext.getTextBodyData();
            MailService mailService = (MailService)SpringUtil.getBean("mailService");
            if (sb.length() > 3) {
                String lastSegment = sb.substring(sb.length() - 3);
                if (lastSegment.equals("\n.\n")) {
                    smtpContext.sendData("250 OK: Queued as " + new Random().nextInt(200));
                    for (String item : smtpContext.getRecipient()){
                        Mail mail = new Mail(0,smtpContext.getMailFrom(),item,smtpContext.getSubject(),smtpContext.getTextBodyData().toString(),new Date(),0);
                        mailService.insert(mail);
                    }
                    smtpContext.setCurrentState(new WaitForRcptToState(smtpContext));
                }
            }
        }

    }
}
