package com.skpl.mailapp.protocol.pop3.server;


import com.skpl.mailapp.protocol.pop3.server.domain.User;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * 连接数据库验证身份.
 */
public class Manager {

    /*
     * 用户是否存在
     */
    public static boolean isUserExist(String username){
        JDBCUtils jdbcUtils = new JDBCUtils();
        boolean flag = jdbcUtils.isUserExist(username);
        return flag;
    }

    /**
     * 验证身份
     */
    public static boolean authUser(User user) {
        JDBCUtils jdbcUtils = new JDBCUtils();
        boolean flag = jdbcUtils.authUser(user.getUsername(), user.getPassword());
        //System.out.println(user.getUsername()+" "+user.getPassword());
        //jdbcUtils.closeAll();
        return flag;
    }

    /**
     * 获取邮箱状态
     */
    public static String getMailStatus(String username){
        JDBCUtils jdbcUtils = new JDBCUtils();
        String mailStatus = jdbcUtils.getMailStatus(username);
        //jdbcUtils.closeAll();
        return mailStatus;
    }

    /**
     * 获取邮箱状态
     */
    public static int getMailNumber(String username){
        JDBCUtils jdbcUtils = new JDBCUtils();
        int mailNumber = jdbcUtils.getMailNumber(username);
        //jdbcUtils.closeAll();
        return mailNumber;
    }

    /**
     * 获得全部文件大小
     */
    public static int getMailBytes(String username){
        JDBCUtils jdbcUtils = new JDBCUtils();
        int mailBytes = jdbcUtils.getMailBytes(username);
        //jdbcUtils.closeAll();
        return mailBytes;
    }

    /**
     * 获得全部文件大小
     */
    public static String getMailByte(String username, int id){
        JDBCUtils jdbcUtils = new JDBCUtils();
        String mailByte = jdbcUtils.getMailByte(username,id);
        //jdbcUtils.closeAll();
        return mailByte;
    }

    /**
     * 无参list命令
     */
    public static ArrayList<Integer> getMailList(String username){
        JDBCUtils jdbcUtils = new JDBCUtils();
        ArrayList<String> mailContent = jdbcUtils.getMailListContents(username);
        ArrayList<Integer> mailList = new ArrayList<>();
        for(int i = 0; i < mailContent.size(); i++){
            mailList.add(mailContent.get(i).getBytes(StandardCharsets.UTF_8).length);
        }
        //jdbcUtils.closeAll();
        return mailList;
    }

    /**
     * retr命令
     */
    public static String getMailTotalContent(String username, int id){
        JDBCUtils jdbcUtils = new JDBCUtils();
        ArrayList<String> list = jdbcUtils.getMailTotalContent(username,id);
        //jdbcUtils.closeAll();
        String totalMail = "";
        totalMail += "Return-Path  : <"+list.get(0)+">"+"\r\n";
        totalMail += "Delivered-To : <"+list.get(1)+">"+"\r\n";
        totalMail += "Received     : (1703 Mail Server invoked for SMTP delivery); "+list.get(4)+" 0800 \r\n";
        totalMail += "From         : <"+list.get(0)+">"+"\r\n";
        totalMail += "To           : <"+list.get(1)+">"+"\r\n";
        totalMail += "Date         : "+list.get(4)+"\r\n";
        totalMail += "Subject      : <"+list.get(2)+">"+"\r\n";
        totalMail += "--------------------------------"+"\r\n";
        totalMail += "Content-Type : text/plain; charset=UTF-8"+"\r\n";
        totalMail += "\r\n";
        totalMail += list.get(3)+"\r\n";
        totalMail += "--------------------------------";
        return totalMail;
    }

}
