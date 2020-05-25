package com.skpl.mailapp.protocol.pop3.server.context;


<<<<<<< HEAD
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
=======
import com.skpl.mailapp.protocol.pop3.server.service.POP3ReceiveService;
import com.skpl.mailapp.protocol.pop3.server.state.LoginState;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * push the message to the client by a thread
 */
public class POP3Context implements Runnable{

    private Socket client = null;
    private BufferedReader br = null;
    private PrintWriter pw = null;
    private POP3ReceiveService pop3ReceiveService = null;

    public POP3Context(Socket client){
        this.client = client;
    }

    public POP3ReceiveService getPop3ReceiveService() {
        return this.pop3ReceiveService;
>>>>>>> dev
    }

    @Override
    public void run() {
<<<<<<< HEAD
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

=======
        try{
            this.client.setSoTimeout(30*1000);
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            pw = new PrintWriter(client.getOutputStream());

        }catch (Exception e){
            e.printStackTrace();
        }

        //记录连接过的地址
        System.out.println("Connection with the client "+this.client.getInetAddress().getHostAddress()+" created");

        //1.welcome
        this.feedbackToClient("+OK Welcome to 1703 Mail POP3 Server");

        //2.调用LoginState
        pop3ReceiveService = new POP3ReceiveService(new LoginState());

        while(true){
            try{
                String inputString = br.readLine();
                this.pop3ReceiveService.handleInputCommand(this,inputString);
                if(client == null){
                    break;
                }

            }catch (SocketTimeoutException ste){
                System.out.println("Server Time Out ......");
                break;
            }
            catch (Exception e){
                e.printStackTrace();
                break;
            }
        }

        this.closeConnection();
    }

    /**
     * feedback to client about the command and flush
     */
    public void feedbackToClient(String output){
        this.pw.println(output);
        this.pw.flush();
    }

    /**
     * close the connection
     */
    public void closeConnection(){
        try{
            if(client != null){
                client.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        client = null;
    }
>>>>>>> dev
}
