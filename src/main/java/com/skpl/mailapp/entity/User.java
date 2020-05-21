package com.skpl.mailapp.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    public UserToApp toUserToWeb(User user) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String resTime = format.format(user.getU_time());
        return new UserToApp(user.getU_id(), user.getU_name(), resTime, user.getU_password(), user.getU_type(), resTime);
    }

    public class UserToApp {
        private Integer u_id;
        private String u_email;
        private String u_name;
        private String u_password;
        private String u_type;
        private String u_time;

        public UserToApp(Integer u_id, String u_email, String u_name, String u_password, String u_type, String u_time) {
            this.u_id = u_id;
            this.u_email = u_email;
            this.u_name = u_name;
            this.u_password = u_password;
            this.u_type = u_type;
            this.u_time = u_time;
        }

        public Integer getU_id() {
            return u_id;
        }

        public void setU_id(Integer u_id) {
            this.u_id = u_id;
        }

        public String getU_email() {
            return u_email;
        }

        public void setU_email(String u_email) {
            this.u_email = u_email;
        }

        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }

        public String getU_password() {
            return u_password;
        }

        public void setU_password(String u_password) {
            this.u_password = u_password;
        }

        public String getU_type() {
            return u_type;
        }

        public void setU_type(String u_type) {
            this.u_type = u_type;
        }

        public String getU_time() {
            return u_time;
        }

        public void setU_time(String u_time) {
            this.u_time = u_time;
        }
    }
}