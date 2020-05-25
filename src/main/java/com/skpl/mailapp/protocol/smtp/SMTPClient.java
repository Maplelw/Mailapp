package com.skpl.mailapp.protocol.smtp;

import java.util.Base64;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author liuwang
 */
public class SMTPClient {
    String mailServer;
    String from;
    String to;
    String subject;
    String content;
    String lineFeet = "\r\n";
    private int port = 25;

    Socket client;
    BufferedReader bf;
    DataOutputStream dos;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMailServer() {
        return mailServer;
    }

    public void setMailServer(String mailServer) {
        this.mailServer = mailServer;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String sub) {
        this.subject = sub;
    }

    /**
     * 初始化
     * 建立连接
     *
     * @return
     */
    private boolean init() {
        System.out.println("init be invoke");
        boolean res = true;
        if (mailServer == null || "".equals(mailServer)) {
            System.out.println("请设置服务器ip地址");
            return false;
        }
        try {
            client = new Socket(mailServer, port);
            System.out.println(mailServer);
            bf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            dos = new DataOutputStream(client.getOutputStream());
            String isConnect = getResponse();
            if (!isConnect.startsWith("220")) {
                System.out.println("建立连接失败： " + isConnect);
                res = false;
            }
        } catch (UnknownHostException e) {
            System.out.println("建立连接失败！");
            e.printStackTrace();
            res = false;
        } catch (IOException e) {
            System.out.println("读取流数据失败！");
            e.printStackTrace();
            res = false;
        }
        System.out.println("init result = " + res);
        return res;
    }

    /**
     * 发送smtp指令，并返回服务器响应信息
     *
     * @param msg
     * @return
     */
    private String sendCommand(String msg) {
        String result = null;
        try {
            dos.writeBytes(msg);
            dos.flush();
            result = getResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取服务器端响应信息
     *
     * @return
     */
    private String getResponse() {
        String result = null;
        try {
            result = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 关闭连接
     */
    private void close() {
        try {
            dos.close();
            bf.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean sendHelo() {
        String result = sendCommand("HELO " + mailServer + lineFeet);
        System.out.println("S:" + result);
        if (!isStartWith(result, "250")) {
            System.out.println("==>握手失败：");
            return false;
        }
        return true;
    }

    private boolean AUTH_LOGIN(String userPassword) {
        //发送AUTH LOGIN
        String result = sendCommand("AUTH LOGIN " + lineFeet);
        System.out.println(result);
        if (!isStartWith(result, "334")) {
            System.out.println("==>AUTH LOGIN失败");
            return false;
        }
        //发送用户名
        result = sendCommand(Base64.getEncoder().encode(from.getBytes()) + lineFeet);
        System.out.println( result);
        if (!isStartWith(result, "334")) {
            System.out.println("==>用户名错误");
            return false;
        }
        //发送密码
        result = sendCommand(Base64.getEncoder().encode(userPassword.getBytes()) + lineFeet);
        System.out.println(result);
        if (!isStartWith(result, "235")) {
            System.out.println("==>密码错误");
            return false;
        }
        return true;
    }

    private boolean sendFrom() {
        String result = sendCommand("Mail From:" + from + "" + lineFeet);
        System.out.println("S:" + result);
        if (!isStartWith(result, "250")) {
            System.out.println("==>发送Mail From错误");
            return false;
        }
        return true;
    }

    private boolean sendRCPT() {
        //发送 RCPT TO
        String result = sendCommand("RCPT TO:" + to +  lineFeet);
        System.out.println("S:" + result);
        if (!isStartWith(result, "250")) {
            System.out.println("==>发送RCPT TO错误");
            return false;
        }
        return true;
    }

    private boolean sendData() {
        String result = sendCommand("DATA" + lineFeet);
        System.out.println("S:" + result);
        if (!isStartWith(result, "354")) {
            System.out.println("==>发送DATA错误");
            return false;
        }
        return true;
    }

    private boolean sendSubject(String subject) {
        String result = sendCommand("Subject:" + subject + lineFeet);
        System.out.println("S:" + result);
        if (!isStartWith(result, "354")) {
            System.out.println("==>发送Subject错误");
            return false;
        }
        return true;
    }

    private boolean sendBody() {
        //发送邮件正文
        StringBuilder sb = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now = simpleDateFormat.format(date);
        System.out.println();
        sb.append(content);
        sb.append(lineFeet + "." + lineFeet);
        String result = sendCommand(sb.toString());
        System.out.println("S:" + result);
        if (!isStartWith(result, "250")) {
            System.out.println("==>发送邮件正文错误：");
            return false;
        }
        return true;
    }

    private boolean sendQuit() {
        String result = sendCommand("QUIT" + lineFeet);
        System.out.println("S:" + result);
        if (!isStartWith(result, "221")) {
            System.out.println("==>发送quit错误");
            return false;
        }
        close();
        return true;
    }

    /**
     * 发送根据SMTP协议邮件的主体函数
     *
     * @return
     */
    public boolean sendMail(String userPassword) {
        //初始化
        if (client == null && !init()) {
            System.out.println("===>初始化失败");
            return false;
        }
        //判断 from, to
        if (from == null || from.isEmpty() || to == null || to.isEmpty()) {
            System.out.println("===>from 或者 to 为空");
            return false;
        }
//        if (sendHelo() && sendFrom() && sendRCPT() && sendData() && sendBody() && sendQuit())
//            return true;
        if (sendHelo() && AUTH_LOGIN(userPassword) && sendFrom() && sendRCPT() && sendData() && sendSubject(subject) && sendBody() && sendQuit())
            return true;
        return false;
    }

    /**
     * 检查字符串开头
     */
    private boolean isStartWith(String res, String with) {
        return res.startsWith(with);
    }

    /**
     * 主函数测试
     */
    public static void main(String[] args) {
        SMTPClient mail = new SMTPClient();
        mail.setMailServer("localhost");
        mail.setFrom("tozsy@skpl.com");
        mail.setTo("1711754407@qq.com");
        mail.setSubject("Hello Email");
        mail.setContent("Hello,this is a test mail, please replay me if you have received it");
        boolean boo = mail.sendMail("yuge666");
        if (boo) {
            System.out.println("邮件发送成功");
        } else {
            System.out.println("邮件发送失败");
        }
    }
}
