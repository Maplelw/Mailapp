package com.skpl.mailapp.protocol.pop3.server;

import com.skpl.mailapp.protocol.pop3.server.context.POP3Context;
import com.skpl.mailapp.protocol.smtp.context.SmtpContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * POP3 server.
 */
public class POP3Server {
    private static int port = 109;
    private static  ServerSocket serverSocket = null;
    private Executor pop3Service = null;

    public POP3Server() {

    }

    public POP3Server(int port) {
        this.port = port;
    }

    /**
     * 服务器运行后监听
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程.
     */
    public boolean start() {
        boolean flag = true;
        try {
            if (serverSocket == null || serverSocket.isClosed()) {
                serverSocket = new ServerSocket(port);
                pop3Service = Executors.newCachedThreadPool();
                System.out.println("POP3 Server is starting at port " + this.port + " ...");
                while (true) {
                    // 等待连接服务器
                    try {
                        if (!serverSocket.isClosed() && serverSocket != null ) {
                            Socket client = serverSocket.accept();
                            System.out.println("A Connection from " + client.getInetAddress().getHostAddress());
                            pop3Service.execute(new POP3Context(client));
                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("pop3服务已经关闭");
                    }
                }
            } else {
                System.out.println("pop3服务已经开启");
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 服务器停止
     */
    public boolean stop() {
        boolean flag = true;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("pop3服务关闭成功");
            } else {
                System.out.println("pop3服务已经关闭");
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    public static void main(String[] args) {
        POP3Server pop3Server = new POP3Server();
        pop3Server.start();
    }
}
