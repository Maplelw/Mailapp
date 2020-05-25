package com.skpl.mailapp.protocol.pop3.server;

import com.skpl.mailapp.entity.Filter;
import com.skpl.mailapp.service.FilterService;
import com.skpl.mailapp.util.Md5Util;
import com.skpl.mailapp.util.SpringUtil;
import sun.security.provider.MD5;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * JDBC工具类：将常用的函数/重复调用代码量大 封装到此，方便调用
 */
public class JDBCUtils {

    private String url = "jdbc:mysql://49.234.115.250:3306/Mailapp?serverTimezone=Asia/Shanghai";
    private String user = "root";
    private String password = "Dgza6666";

    public JDBCUtils() {
    }

    /**
     * 连接数据库
     *
     * @return Connection
     */
    public Connection getConnection() {
        Connection con = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
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
    public void closeAll(ResultSet rs, PreparedStatement pStmt, Connection con) {
        /*
        不能放到同一个try、catch语句中：
        如果某一个在close过程中出现异常抛出，后面的将无法关闭资源
         */
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pStmt != null) {
                pStmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看是否用户存在
     */
    public boolean isUserExist(String username) {
        String sql = "SELECT u_email FROM user WHERE u_email = ?";
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, username);
            rs = pStmt.executeQuery();
            if (rs != null) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 认证
     */
    public boolean authUser(String username, String password) {
        String sql = "SELECT u_password FROM user WHERE u_email = ? ";
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, username);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                String pw = rs.getString(1);
                System.out.println(pw);
                if (pw.equals(Md5Util.md5(password))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                jdbcUtils.closeAll(rs, pStmt, con);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 邮箱状态: 统计接收的邮件，不统计已发送的
     */
    public String getMailStatus(String username) {
        String sql = "SELECT m_id, m_content FROM mail WHERE m_to =  ?  ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, username);
            rs = pStmt.executeQuery();
            int count_mail = 0;
            int count_bytes = 0;
            while (rs.next()) {
                count_mail++;
                count_bytes += rs.getString(2).getBytes(StandardCharsets.UTF_8).length;
            }
            return count_mail + " " + count_bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return "0 0";
        }
    }

    /**
     * 邮箱邮件数量
     */
    public int getMailNumber(String userMail) {
        String sql = "SELECT count(*) FROM mail WHERE ( m_to = ?  OR  m_from = ? ) AND m_from NOT IN " +
                "(SELECT f_refuse_mail FROM filter WHERE f_mail = m_to )";
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, userMail);
            pStmt.setString(2, userMail);
            rs = pStmt.executeQuery();
            int mailNum = 0;
            while (rs.next()) {
                mailNum = rs.getInt(1);
            }
            return mailNum;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 邮件大小
     */
    public int getMailBytes(String username) {
        String sql = "SELECT m_content FROM mail WHERE m_to = ?  ";
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, username);
            rs = pStmt.executeQuery();
            int mail_bytes = 0;
            while (rs.next()) {
                mail_bytes += rs.getString(1).getBytes(StandardCharsets.UTF_8).length;
            }
            return mail_bytes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 邮件大小
     */
    public String getMailByte(String username, int id) {
        String sql = "SELECT m_content FROM mail WHERE m_to = ?  ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, username);
            rs = pStmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == id) {
                    return id + " " + rs.getString(1).getBytes(StandardCharsets.UTF_8).length;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id + " 0";
    }

    /**
     * 邮件内容
     */
    public String getMailContent(String username, int id) {
        String sql = "SELECT m_content,m_from FROM mail WHERE m_to =  ? AND m_id = ? ";
        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, username);
            pStmt.setInt(2, id);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取用户邮件的所有信件
     */
    public ArrayList<String> getMailListContents(String username) {
        ArrayList<String> list = new ArrayList<>();

        String sql = "SELECT m_content FROM mail WHERE m_to = ?  ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, username);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据userMail获取userName
     */
    public String getUserNameByMail(String userMail) {
        String userName = "";
        String sql = "SELECT u_name FROM user WHERE u_email = ?";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;

        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, userMail);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                userName = rs.getString("u_name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userName;
    }

    /**
     * 还原邮件的大致内容
     */
    public ArrayList<String> getMailTotalContent(String username, int id) {
        ArrayList<String> list = new ArrayList<>();

        String sql = "SELECT * FROM mail WHERE ( m_to = ?  OR  m_from = ? ) AND m_from NOT IN " +
                "(SELECT f_refuse_mail FROM filter WHERE f_mail = m_to ) ORDER BY m_time DESC ";

        JDBCUtils jdbcUtils = new JDBCUtils();
        Connection con = jdbcUtils.getConnection();
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            pStmt = con.prepareStatement(sql);
            pStmt.setString(1, username);
            pStmt.setString(2, username);
            rs = pStmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == id) {
                    String fromName = jdbcUtils.getUserNameByMail(username);
                    String toName = jdbcUtils.getUserNameByMail(rs.getString("m_to"));
                    list.add(rs.getString("m_from"));
                    list.add(rs.getString("m_to"));
                    list.add(rs.getString("m_subject"));
                    list.add(rs.getString("m_content"));
                    list.add(rs.getString("m_time"));
                    list.add(rs.getString("m_flag"));
                    list.add(rs.getString("m_id"));
                    list.add(fromName);
                    list.add(toName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}


