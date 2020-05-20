package com.skpl.mailapp.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 记录好友关系(Friendship)实体类
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friendship implements Serializable {
    private static final long serialVersionUID = -50083072157997629L;
    /**
    * 记录编号
    */
    private Integer fNo;
    /**
    * email xxx@xxx.com
    */
    private String uEmail1;
    /**
    * email xxx@xxx.com
    */
    private String uEmail2;
    /**
    * 给好友取的昵称
    */
    private String fNickname;


}