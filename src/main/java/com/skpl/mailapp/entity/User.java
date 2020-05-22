package com.skpl.mailapp.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 记录用户的信息(User)实体类
 *
 * @author makejava
 * @since 2020-05-07 17:52:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 400222264088349182L;
    /**
     * 在系统中的编号
     */
    private Integer u_id;
    /**
     * email xxx@xxx.com
     */
    @JsonProperty("userName")
    private String u_email;
    /**
     * 用户名
     */
    private String u_name;
    /**
     * 用户密码
     */
    @JsonProperty("userPassword")
    private String u_password;
    /**
     * 普通用户 or  管理员
     */
    private String u_type;
    /**
     * 最近登录时间
     */
    private Date u_time;

    public UserToApp toUserToApp(User user) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String resTime = format.format(user.getU_time());
        return new UserToApp(user.getU_id(), user.getU_name(), resTime, user.getU_password(), user.getU_type(), resTime);
    }

    public UserToWeb toUserToWeb(User user) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String resTime = format.format(user.getU_time());
        return new UserToWeb(user.getU_id(), user.getU_name(), resTime, user.getU_password(), user.getU_type(), resTime);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class UserToWeb {
        private Integer u_id;
        private String u_email;
        private String u_name;
        private String u_password;
        private String u_type;
        private String u_time;
    }

    public class UserToApp {
        private Integer userId;
        private String userMail;
        private String userName;
        private String userPassword;
        private String userType;
        private String time;

        public UserToApp(Integer userId, String userMail, String userName, String userPassword, String userType, String time) {
            this.userId = userId;
            this.userMail = userMail;
            this.userName = userName;
            this.userPassword = userPassword;
            this.userType = userType;
            this.time = time;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserMail() {
            return userMail;
        }

        public void setUserMail(String userMail) {
            this.userMail = userMail;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}