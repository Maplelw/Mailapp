package com.skpl.mailapp.protocol.smtp;


import com.skpl.mailapp.protocol.smtp.context.SmtpContext;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;

class SmtpThread extends Thread {

    private ServerSocket serverSocket;

    private int port;

    public SmtpThread(ServerSocket socket, int port) {
        this.serverSocket = socket;
        this.port = port;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            // 等待连接服务器
            try {
                if (!serverSocket.isClosed() && serverSocket != null) {
                    new SmtpContext(serverSocket.accept()).start();
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("smtp服务已经关闭");
            }
        }
    }
}

@Component
public class SMTPServer {
    private static final int port = 24;

    private static ServerSocket serverSocket;

    public static boolean startServer() {
        boolean flag = true;
        try {
            if (serverSocket == null || serverSocket.isClosed()) {
                serverSocket = new ServerSocket(port);
                SmtpThread thread = new SmtpThread(serverSocket, port);
                thread.start();
                System.out.println("smtp服务开启成功");
            } else {
                System.out.println("smtp服务已经开启");
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
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("smtp服务关闭成功");
            } else {
                System.out.println("smtp服务已经关闭");
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public static void main(String[] args) {
        SMTPServer.startServer();
    }
}
