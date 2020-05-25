package com.skpl.mailapp.protocol.pop3.server;

import com.skpl.mailapp.protocol.pop3.server.domain.User;
import com.skpl.mailapp.service.UserService;
import com.skpl.mailapp.util.SpringUtil;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

/**
 * 连接数据库验证身份.
 */
public class Manager {

    /*
     * 用户是否存在
     */
    public static boolean isUserExist(String username) {
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
        if(flag) {
            UserService userService = (UserService)SpringUtil.getBean("userService");
            com.skpl.mailapp.entity.User userTemp = userService.queryByEmail(user.getUsername());
            userTemp.setU_time(new Date());
            userService.update(userTemp);
        }
        //System.out.println(user.getUsername()+" "+user.getPassword());
        //jdbcUtils.closeAll();
        return flag;
    }

    /**
     * 获取邮箱状态
     */
    public static String getMailStatus(String username) {
        JDBCUtils jdbcUtils = new JDBCUtils();
        String mailStatus = jdbcUtils.getMailStatus(username);
        //jdbcUtils.closeAll();
        return mailStatus;
    }

    /**
     * 获取邮箱状态
     */
    public static int getMailNumber(String username) {
        JDBCUtils jdbcUtils = new JDBCUtils();
        int mailNumber = jdbcUtils.getMailNumber(username);
        //jdbcUtils.closeAll();
        return mailNumber;
    }

    /**
     * 获得全部文件大小
     */
    public static int getMailBytes(String username) {
        JDBCUtils jdbcUtils = new JDBCUtils();
        int mailBytes = jdbcUtils.getMailBytes(username);
        //jdbcUtils.closeAll();
        return mailBytes;
    }

    /**
     * 获得全部文件大小
     */
    public static String getMailByte(String username, int id) {
        JDBCUtils jdbcUtils = new JDBCUtils();
        String mailByte = jdbcUtils.getMailByte(username, id);
        //jdbcUtils.closeAll();
        return mailByte;
    }

    /**
     * 无参list命令
     */
    public static ArrayList<Integer> getMailList(String username) {
        JDBCUtils jdbcUtils = new JDBCUtils();
        ArrayList<String> mailContent = jdbcUtils.getMailListContents(username);
        ArrayList<Integer> mailList = new ArrayList<>();
        for (int i = 0; i < mailContent.size(); i++) {
            mailList.add(mailContent.get(i).getBytes(StandardCharsets.UTF_8).length);
        }
        //jdbcUtils.closeAll();
        return mailList;
    }

    /**
     * retr命令
     */
    public static String getMailTotalContent(String username, int id) {
        System.out.println(id);
        JDBCUtils jdbcUtils = new JDBCUtils();
        ArrayList<String> list = jdbcUtils.getMailTotalContent(username, id);
        //jdbcUtils.closeAll();
        String totalMail = "";
        boolean isRead = true;
        System.out.println(list.get(5));
        if (list.get(5).equals("0")) {
            isRead = false;
        }
        boolean isSend = true;
        if (list.get(1).equals(username)) {
            isSend = false;
        }
        totalMail += "From         : <" + list.get(0) + ">" + "\r\n";
        totalMail += "To           : <" + list.get(1) + ">" + "\r\n";
        totalMail += "MailID       : <" + list.get(6) + ">" + "\r\n";
        totalMail += "Date         : " + list.get(4) + "\r\n";
        totalMail += "IsRead       : " + isRead + "\r\n";
        totalMail += "IsSend       : " + isSend + "\r\n";
        totalMail += "NameFrom     : " + list.get(7) + "\r\n";
        totalMail += "NameTo       : " + list.get(8) + "\r\n";
        totalMail += "Subject      : <" + list.get(2) + ">" + "\r\n";
        totalMail += "Content      : " + "\r\n";
        totalMail += list.get(3) + "\r\n";
        return totalMail;
    }

}
