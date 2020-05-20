package com.skpl.mailapp.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 系统管理员  服务端管理员(Admin)实体类
 *
 * @author makejava
 * @since 2020-05-20 22:08:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    private static final long serialVersionUID = 867240706444120089L;
    /**
    * 系统管理员编号
    */
    private Integer a_no;
    
    private String a_name;
    /**
    * 管理员密码
    */
    private String a_password;
    /**
    * 管理员类型，目前只有一种，可以拓展
    */
    private String a_type;


}