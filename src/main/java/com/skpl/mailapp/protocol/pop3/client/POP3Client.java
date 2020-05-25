package com.skpl.mailapp.protocol.pop3.client;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * POP3 client.
 */
public class POP3Client {

    private Socket socket = null;

    public static void main(String[] args) {
        String server = "localhost";
<<<<<<< HEAD
        String user = "tozsy@skpl.com";
        String password = "yuge666";
        int port =109;
//        String server = "pop.163.com";
//        String user = "gxdlw00";
//        String password = "AGDWXJYBRVULXDMN";  //授权码
//        int port = 110;
        POP3Client pop3Client = new POP3Client(server, port);
        pop3Client.receiveMail(user, password);
=======
        String user = "maple";
        String password = "123456";
        int port = 110;
        POP3Client pop3Client = new POP3Client(server,port);
        pop3Client.receiveMail(user,password);
>>>>>>> dev
    }

    /**
     * 构造函数
     */
<<<<<<< HEAD
    public POP3Client(String server, int port) {
        try {
            socket = new Socket(server, port);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
=======
    public  POP3Client(String server, int port){
        try{
            socket = new Socket(server, port);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
>>>>>>> dev
            System.out.println("成功与服务器建立连接.");
        }
    }

    /**
     * 得到服务器的一整行的命令
     */
<<<<<<< HEAD
    public String getTotalState(BufferedReader br) {
        String line = "";

        try {
            line = br.readLine();

            if (true) {
                System.out.println("当前服务器的状态为: " + line);
            }

        } catch (Exception e) {
=======
    public String getTotalState(BufferedReader br){
        String line = "";

        try{
            line = br.readLine();

            if(true){
                System.out.println("当前服务器的状态为: "+line);
            }

        }catch (Exception e){
>>>>>>> dev
            e.printStackTrace();
        }

        return line;
    }

    /**
     * 返回服务器的状态,StringTokenizer为以" "划分字符串然后返回第一个字符串，即为服务器的状态+OK或-ERR
     */
<<<<<<< HEAD
    public String getState(String line) {
        StringTokenizer st = new StringTokenizer(line, " ");
        return st.nextToken();
=======
    public String getState(String line){
        if(!line.equals(""))
        {
            StringTokenizer st = new StringTokenizer(line," ");
            return st.nextToken();
        }
        return "-ERR STOP Server";
>>>>>>> dev
    }

    /**
     * 发送命令
     */
<<<<<<< HEAD
    private String sendServer(String str, BufferedReader br, BufferedWriter bw) throws IOException {
        bw.write(str);//发送命令
        bw.newLine();//发送空行
        bw.flush();//清空缓冲区
        if (true) {
            System.out.println("已发送命令: " + str);
        }
        return getTotalState(br);
    }
=======
    private String sendServer(String str,BufferedReader br,BufferedWriter bw) throws IOException {
         bw.write(str);//发送命令
         bw.newLine();//发送空行
         bw.flush();//清空缓冲区
         if(true){
            System.out.println("已发送命令: "+str);
         }
         //当为quit时由于一端退出，但退出时并未关闭该连接，另一端如果在从连接中读数据则抛出该异常（Connection reset）。
         if(str.toLowerCase().equals("quit"))
         {
             br.close();
             bw.close();
             return getState("+OK 1703 Mail");
         }
         return getTotalState(br);
     }
>>>>>>> dev

    /**
     * 接收邮件
     */
<<<<<<< HEAD
    public void receiveMail(String user, String password) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            user(user, br, bw);
            pass(password, br, bw);
            int emailNum = stat(br, bw);
            npList(br, bw);
            pList(2, br, bw);
            retr(emailNum, br, bw);
            quit(br, bw);

        } catch (Exception e) {
=======
    public void receiveMail(String user, String password){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            user(user,br,bw);

            pass(password,br,bw);

            int emailNum = stat(br,bw);

            npList(br,bw);

            pList(2,br,bw);

            retr(emailNum,br,bw);

            quit(br,bw);

        }catch (Exception e){
>>>>>>> dev
            e.printStackTrace();
        }
    }

    /**
     * user命令
     */
    public void user(String user, BufferedReader br, BufferedWriter bw) throws IOException {

        String serverState = null;
        serverState = getState(getTotalState(br));

<<<<<<< HEAD
        if (!"+OK".equals(serverState)) {
            throw new IOException("连接服务器失败!");
        }

        serverState = getState(sendServer("user " + user, br, bw));
        if (!"+OK".equals(serverState)) {
=======
        if(!"+OK".equals(serverState)){
            throw new IOException("连接服务器失败!");
        }

        serverState = getState(sendServer("user "+user,br,bw));
        if(!"+OK".equals(serverState)){
>>>>>>> dev
            throw new IOException("用户名错误!");
        }
    }

    /**
     * pass命令
     */
<<<<<<< HEAD
    public void pass(String password, BufferedReader br, BufferedWriter bw) throws IOException {

        String serverState = null;
        serverState = getState(sendServer("pass " + password, br, bw));

        if (!"+OK".equals(serverState)) {
=======
    public void pass(String password, BufferedReader br, BufferedWriter bw) throws IOException{

        String serverState = null;
        serverState = getState(sendServer("pass "+password, br, bw));

        if(!"+OK".equals(serverState)){
>>>>>>> dev
            throw new IOException("密码错误!");
        }
    }

    /**
     * stat命令: 请求服务器发回关于邮箱的统计资料，如邮件总数和总字节数
     */
<<<<<<< HEAD
    public int stat(BufferedReader br, BufferedWriter bw) throws IOException {
=======
    public int stat(BufferedReader br, BufferedWriter bw) throws IOException{
>>>>>>> dev
        String serverState = null;
        String line = null;
        int mailNum = 0;

<<<<<<< HEAD
        line = sendServer("stat", br, bw);

        StringTokenizer st = new StringTokenizer(line, " ");
        serverState = st.nextToken();

        if (st.hasMoreTokens()) {
            mailNum = Integer.parseInt(st.nextToken());
        } else {
            mailNum = 0;
        }

        if (!"+OK".equals(serverState)) {
            throw new IOException("查看邮箱状态失败!");
        }

        System.out.println("当前有" + mailNum + "封邮件");
=======
        line = sendServer("stat",br,bw);

        StringTokenizer st = new StringTokenizer(line," ");
        serverState = st.nextToken();

        if(st.hasMoreTokens()){
            mailNum = Integer.parseInt(st.nextToken());
        }else{
            mailNum = 0;
        }

        if(!"+OK".equals(serverState)){
            throw new IOException("查看邮箱状态失败!");
        }

        System.out.println("当前有"+mailNum+"封邮件");
>>>>>>> dev
        return mailNum;
    }

    /**
     * 无参的list命令: 返回邮件数量和每个邮件的大小
     */
<<<<<<< HEAD
    public void npList(BufferedReader br, BufferedWriter bw) throws IOException {
        String message = "";
        String line = null;
        line = sendServer("list ", br, bw);

        //equalsIgnoreCase()方法用于将字符串与指定的对象比较，不考虑大小写
        while (!".".equalsIgnoreCase(line)) {
=======
    public void npList(BufferedReader br, BufferedWriter bw) throws IOException{
        String message = "";
        String line = null;
        line = sendServer("list ",br,bw);

        //equalsIgnoreCase()方法用于将字符串与指定的对象比较，不考虑大小写
        while(!".".equalsIgnoreCase(line)){
>>>>>>> dev
            message = message + line;
            line = br.readLine();
            System.out.println(line);
        }
        //System.out.println(message);
    }

    /**
     * 有参的list命令: 返回特定的一封邮件的数量和大小
     */
<<<<<<< HEAD
    public void pList(int mailNum, BufferedReader br, BufferedWriter bw) throws IOException {
        String serverState = null;
        serverState = getState(sendServer("list " + mailNum, br, bw));

        if (!"+OK".equals(serverState)) {
=======
    public void pList(int mailNum, BufferedReader br, BufferedWriter bw) throws IOException{
        String serverState = null;
        serverState = getState(sendServer("list "+mailNum,br,bw));

        if(!"+OK".equals(serverState)){
>>>>>>> dev
            throw new IOException("list错误!");
        }
    }

    /**
     * dele命令:  服务器将由参数标识的邮件标记为删除，由quit命令执行
     */
    public void deleteMail(int mailNum, BufferedReader br, BufferedWriter bw) throws IOException {
        String serverState = null;
<<<<<<< HEAD
        serverState = getState(sendServer("dele " + mailNum, br, bw));

        if (!"+OK".equals(serverState)) {
=======
        serverState = getState(sendServer("dele "+mailNum, br, bw));

        if(!"+OK".equals(serverState)){
>>>>>>> dev
            throw new IOException("删除邮件失败!");
        }
    }

    /**
     * rset命令:  服务器将重置所有标记为删除的邮件，用于撤消DELE命令
     */
    public void resetDeleteMail(int mailNum, BufferedReader br, BufferedWriter bw) throws IOException {
        String serverState = null;
        serverState = getState(sendServer("rset ", br, bw));

<<<<<<< HEAD
        if (!"+OK".equals(serverState)) {
=======
        if(!"+OK".equals(serverState)){
>>>>>>> dev
            throw new IOException("恢复删除邮件失败!");
        }
    }

    /**
     * 获取邮件的详细信息
     */
<<<<<<< HEAD
    public String getMessageDetail(BufferedReader br) throws UnsupportedEncodingException {
        String message = "";
        String line = null;

        try {
            line = br.readLine();
            while (!".".equalsIgnoreCase(line)) {
                message = message + line + "\r\n";
                line = br.readLine();
            }
        } catch (Exception e) {
=======
    public String getMessageDetail(BufferedReader br) throws UnsupportedEncodingException{
        String message = "";
        String line = null;

        try{
            line = br.readLine();
            while(!".".equalsIgnoreCase(line)){
                message = message + line + "\r\n";
                line = br.readLine();
            }
        }catch(Exception e){
>>>>>>> dev
            e.printStackTrace();
        }

        return message;
    }

    /**
     * retr 命令: 返回由参数标识的邮件的全部文本
     */
<<<<<<< HEAD
    public void retr(int mailNum, BufferedReader br, BufferedWriter bw) throws IOException, InterruptedException {
        String result = null;
        result = getState(sendServer("retr " + mailNum, br, bw));
        if (!"+OK".equals(result)) {
            throw new IOException("接收邮件出错!");
        }

        System.out.println("第" + mailNum + "封");
        System.out.println(getMessageDetail(br));
        Thread.sleep(3000);
    }
=======
     public void retr(int mailNum,BufferedReader br,BufferedWriter bw) throws IOException, InterruptedException{
        String result = null;
        result = getState(sendServer("retr "+ mailNum,br,bw));
        if(!"+OK".equals(result)){
            throw new IOException("接收邮件出错!");
        }

        System.out.println("第"+mailNum+"封");
        System.out.println(getMessageDetail(br)+".");
     }
>>>>>>> dev

    /**
     * quit退出
     */

<<<<<<< HEAD
    public void quit(BufferedReader br, BufferedWriter bw) throws IOException {
        String result;
        result = getState(sendServer("QUIT", br, bw));

        if (!"+OK".equals(result)) {
            throw new IOException("未能正确退出!");
        }
    }
=======
     public void quit(BufferedReader br,BufferedWriter bw) throws IOException{
        String result = null;
        result = getState(sendServer("quit",br,bw));
         br.close();
         bw.close();
        System.out.println("当前服务器的状态为: "+result+" 1703 Mail");

        if(!"+OK".equals(result)){
            throw new IOException("未能正确退出!");
        }

        System.exit(0);
      }
>>>>>>> dev

}
