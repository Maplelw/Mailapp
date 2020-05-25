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
<<<<<<< HEAD
    static private int port = 109;
    static private ServerSocket serverSocket = null;
    private Executor pop3Service = null;

    /**
     * 构造函数
     *
     * @param port
     */
=======
    private static int port = 109;
    private static  ServerSocket serverSocket = null;
    private Executor pop3Service = null;

    public POP3Server() {

    }

>>>>>>> dev
    public POP3Server(int port) {
        this.port = port;
    }

<<<<<<< HEAD

=======
>>>>>>> dev
    /**
     * 服务器运行后监听
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程.
     */
    public boolean start() {
        boolean flag = true;
        try {
            if (serverSocket == null || serverSocket.isClosed()) {
<<<<<<< HEAD
                System.out.println("pop3服务开启成功");
                serverSocket = new ServerSocket(port);
                pop3Service = Executors.newCachedThreadPool();
                while (true) {
                    try {
                        if (!serverSocket.isClosed() && serverSocket != null) {
                            new POP3Context(serverSocket.accept()).start();
=======
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
>>>>>>> dev
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
<<<<<<< HEAD
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            System.out.println("Meet some problems......");
=======
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
>>>>>>> dev
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
<<<<<<< HEAD
                System.out.println("关闭pop3服务成功");
            } else {
                System.out.println("pop3服务已经关闭");
            }
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            System.out.println("Meet some problems......");
=======
                System.out.println("pop3服务关闭成功");
            } else {
                System.out.println("pop3服务已经关闭");
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
>>>>>>> dev
        }
        return flag;
    }

    public static void main(String[] args) {
<<<<<<< HEAD
        int port = 109;
        POP3Server pop3Server = new POP3Server(port);
=======
        POP3Server pop3Server = new POP3Server();
>>>>>>> dev
        pop3Server.start();
    }
}
