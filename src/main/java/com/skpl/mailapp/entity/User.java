package com.skpl.mailapp.entity;

import java.util.Date;
import java.io.Serializable;
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
    private String u_email;
    /**
    * 用户名
    */
    private String u_name;
    /**
    * 用户密码
    */
    private String u_password;
    /**
    * 普通用户 or  管理员
    */
    private String u_type;
    /**
    * 最近登录时间
    */
    private Date u_time;

}