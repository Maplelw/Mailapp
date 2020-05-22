package com.skpl.mailapp.protocol.pop3.server.context;


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
    }

    @Override
    public void run() {
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
}
