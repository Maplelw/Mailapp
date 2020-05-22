package com.skpl.mailapp.protocol.pop3.server;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;

/**
 * JDBC工具类：将常用的函数/重复调用代码量大 封装到此，方便调用
 */
public class JDBCUtils {
    
    private String url = "jdbc:mysql://49.234.115.250:3306/Mailapp?serverTimezone=Asia/Shanghai";
    private String user = "root";
    private String password = "Dgza6666";

    public JDBCUtils(){
    }

    public static void main(String[] args) {
        JDBCUtils j = new JDBCUtils();
        System.out.println(j.getMailContent("maple",8));
    }

    /**
     * 连接数据库
     * @return Connection
     */
    public Connection getConnection() {
        Connection con = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //建立连接
            con = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    /*
     * @Description:  关闭资源函数：由于按照Connection、Statement、ResultSet的顺序创建，所以要从后面开始关闭
     * @Param resultSet  statement  connection
     * @Return: void
     */
    public void closeAll(ResultSet rs, PreparedStatement pStmt , Connection con)
    {
        /*
        不能放到同一个try、catch语句中：
        如果某一个在close过程中出现异常抛出，后面的将无法关闭资源
         */
        try
        {
            if (rs != null) rs.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (pStmt != null) pStmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (con != null) con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查看是否用户存在
     */
    public boolean isUserExist(String username){
        String sql = "select u_mail from user ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                System.out.println("========" + rs.getString(1));
                if(rs.getString(1).equals(username)){
                    return true;
                }
            }
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 认证
     */
    public boolean authUser(String username, String password) {
        String sql = "select u_password from user where u_name = ? ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1,username);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                String pw = rs.getString(1);
                if (pw.equals(password)) {
                    return true;
                }else {
                    return false;
                }
            }else {
                jdbcUtils.closeAll(rs,pStmt,con);
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 邮箱状态: 统计接收的邮件，不统计已发送的
     */
    public String getMailStatus(String username){
        String sql = "select m_id, m_content from mail where m_to = (select u_email from user where u_name = ? ) ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1,username);
            rs = pStmt.executeQuery();
            int count_mail = 0;
            int count_bytes = 0;
            while (rs.next()) {
                count_mail++;
                count_bytes += rs.getString(2).getBytes(StandardCharsets.UTF_8).length;
            }
            return count_mail+" "+count_bytes;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "0 0";
        }
    }

    /**
     * 邮箱邮件数量
     */
    public int getMailNumber(String username){
        String sql = "select count(*) from mail where m_to = (select u_email from user where u_name = ? ) ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1,username);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }else {
                return 0;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 邮件大小
     */
    public int getMailBytes(String username){
        String sql = "select m_content from mail where m_to = (select u_email from user where u_name = ? ) ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try{
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1,username);
            rs = pStmt.executeQuery();
            int mail_bytes = 0;
            while(rs.next()){
                mail_bytes += rs.getString(1).getBytes(StandardCharsets.UTF_8).length;
            }
            return mail_bytes;

        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 邮件大小
     */
    public String getMailByte(String username, int id){
        String sql = "select m_content from mail where m_to = (select u_email from user where u_name = ? ) ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try{
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1,username);
            rs = pStmt.executeQuery();
            int i = 0;
            while(rs.next()){
                i++;
                if(i == id){
                    return id+" "+rs.getString(1).getBytes(StandardCharsets.UTF_8).length;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return id+" 0";
    }

    /**
     * 邮件内容
     */
    public String getMailContent(String username, int id){
        String sql = "select m_content from mail where m_to = (select u_email from user where u_name = ? ) and m_id = ? ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try{
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1,username);
            pStmt.setInt(2,id);
            rs = pStmt.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取用户邮件的所有信件
     */
    public static ArrayList<String> getMailListContents(String username){
        ArrayList<String> list = new ArrayList<>();

        String sql = "select m_content from mail where m_to = (select u_email from user where u_name = ? ) ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try{
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1,username);
            rs = pStmt.executeQuery();
            while(rs.next()){
                list.add(rs.getString(1));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 还原邮件的大致内容
     */
    public static ArrayList<String> getMailTotalContent(String username, int id){
        ArrayList<String> list = new ArrayList<>();

        String sql = "select * from mail where m_to = (select u_email from user where u_name = ? ) ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try{
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1,username);
            rs = pStmt.executeQuery();
            int i = 0;
            while(rs.next()){
                i++;
                if(i == id){
                    list.add(rs.getString("m_from"));
                    list.add(rs.getString("m_to"));
                    list.add(rs.getString("m_subject"));
                    list.add(rs.getString("m_content"));
                    list.add(rs.getString("m_time"));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}


