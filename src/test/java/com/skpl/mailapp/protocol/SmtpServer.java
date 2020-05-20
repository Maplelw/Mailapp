package com.skpl.mailapp.protocol;

import com.skpl.mailapp.protocol.smtp.context.SmtpContext;

import java.io.IOException;
import java.net.ServerSocket;

public class SmtpServer {
    private static final int port = 25;

    private static ServerSocket serverSocket;

    public static boolean startServer() {
        boolean flag = true;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("start SMTP server success!");
            while (true) {
                // 等待连接服务器
                try {
                    if(!serverSocket.isClosed()) {
                        new SmtpContext(serverSocket.accept()).start();
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("SMTP服务已经关闭");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public static boolean stopServer() {
        boolean flag = true;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            System.out.println("Stop SMTP server success!");
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public static void main(String[] args) throws IOException {
        startServer();
    }
}
