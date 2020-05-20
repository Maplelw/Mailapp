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
    static private int port = 109;
    static private ServerSocket serverSocket = null;
    private Executor pop3Service = null;

    /**
     * 构造函数
     *
     * @param port
     */
    public POP3Server(int port) {
        this.port = port;
    }


    /**
     * 服务器运行后监听
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程.
     */
    public void start() {
        try {
            if (serverSocket == null || serverSocket.isClosed()) {
                System.out.println("pop3服务开启成功");
                serverSocket = new ServerSocket(port);
                pop3Service = Executors.newCachedThreadPool();
                while (true) {
                    try {
                        if (!serverSocket.isClosed() && serverSocket != null) {
                            new POP3Context(serverSocket.accept()).start();
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
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Meet some problems......");
        }
    }

    /**
     * 服务器停止
     */
    public void stop() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("关闭pop3服务成功");
            } else {
                System.out.println("pop3服务已经关闭");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Meet some problems......");
        }
    }

    public static void main(String[] args) {
        int port = 109;
        POP3Server pop3Server = new POP3Server(port);
        pop3Server.start();
    }
}
