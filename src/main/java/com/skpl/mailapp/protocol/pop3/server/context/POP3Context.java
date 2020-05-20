package com.skpl.mailapp.protocol.pop3.server.context;


import com.skpl.mailapp.entity.User;
import com.skpl.mailapp.protocol.pop3.server.states.CheckUserName;
import com.skpl.mailapp.protocol.pop3.server.states.POP3State;
import com.skpl.mailapp.protocol.smtp.entity.MailObject;
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
public class POP3Context extends Thread {

    private POP3State currentState;
    private Socket socket;
    private PrintWriter printWriter;
    private User user;


    public POP3Context(Socket socket) {
        super("ContextThread");
        this.socket = socket;
        user = new User();
        CreateOutputPr();
        currentState = new CheckUserName(this);
        run();
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String fromClient;
            while ((fromClient = bufferedReader.readLine()) != null) {
                System.out.println("C: " + fromClient);
                if (fromClient.equalsIgnoreCase("QUIT")) {
                    sendData("+OK core mail");
                    disconnect();
                    break;
                }
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

    public void setCurrentState(POP3State currentState) {
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

    public void setUserMail(String mail) {
        user.setU_email(mail);
    }

    public User getUser() {
        return this.user;
    }

}
