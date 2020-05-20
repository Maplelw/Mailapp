package com.skpl.mailapp.protocol.smtp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maple
 * @Date 2020/5/10 9:14
 */
public class MailObject {
    private String userEmail;
    private String userPassword;
    private String hostName;
    private String mailFrom;
    private String subject;
    private List<String> recipientTo = new ArrayList<>();
    private StringBuilder body = new StringBuilder();

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public List<String> getRecipientTo() {
        return recipientTo;
    }

    public void setRecipientTo(String recipientTo) {
        this.recipientTo.add(recipientTo);
    }

    public StringBuilder getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body.append(body);
    }
}
