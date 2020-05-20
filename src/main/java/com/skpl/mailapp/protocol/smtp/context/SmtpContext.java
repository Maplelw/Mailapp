package com.skpl.mailapp.protocol.smtp.context;


import com.skpl.mailapp.protocol.smtp.entity.MailObject;
import com.skpl.mailapp.protocol.smtp.states.SMTPState;
import com.skpl.mailapp.protocol.smtp.states.WelcomeState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * @author maple
 * @Date 2020/5/10 9:46
 */
public class SmtpContext extends Thread {

    private SMTPState currentState;
    private Socket socket;
    private PrintWriter printWriter;
    private MailObject mailObject;


    public SmtpContext(Socket socket) {
        super("ContextThread");
        this.socket = socket;
        mailObject = new MailObject();
        CreateOutputPr();
        currentState = new WelcomeState(this);
        run();
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String fromClient;
            while ((fromClient = bufferedReader.readLine()) != null) {
                //Print our data from client
                System.out.println("C: " + fromClient);
                //If statement equals Exit.
                if (fromClient.equals("QUIT")) {
                    sendData("221 Bye");
                    disconnect();
                    break;
                }
                //Pass to our State, so it can process the data.
                currentState.handle(fromClient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CreateOutputPr() {
        try {
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCurrentState(SMTPState currentState) {
        this.currentState = currentState;
    }

    public void sendData(String data) {
        printWriter.println(data);
        System.out.println("S: " + data);
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSubject() {
        return this.mailObject.getSubject();
    }

    public void setSubject(String subject) {
        this.mailObject.setSubject(subject);
    }

    public void setHost(String hostname) {
        this.mailObject.setHostName(hostname);
    }

    public String getHost() {
        return this.mailObject.getHostName();
    }

    public void setMailFrom(String mailFrom) {
        this.mailObject.setMailFrom(mailFrom);
    }

    public String getMailFrom() {
        return this.mailObject.getMailFrom();
    }


    public void addRecipient(String recipient) {
        mailObject.setRecipientTo(recipient);
    }

    public List<String> getRecipient() {
        return mailObject.getRecipientTo();
    }

    public void addTextToBody(String text) {
        mailObject.setBody(text + "\n");
    }

    public StringBuilder getTextBodyData() {
        return mailObject.getBody();
    }

    public void setUserMail(String userMail) {
        this.mailObject.setUserEmail(userMail);
    }

    public String getUserMail() {
        return this.mailObject.getUserEmail();
    }

    public void setUserPassword(String userPassword) {
        this.mailObject.setUserEmail(userPassword);
    }

    public void getUserPassword() {
        this.mailObject.getUserPassword();
    }
}
