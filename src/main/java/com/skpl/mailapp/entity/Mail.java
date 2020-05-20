package com.skpl.mailapp.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Mail)实体类
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail implements Serializable {
    private static final long serialVersionUID = 439991188578310455L;
    /**
    * 邮件编号
            
    */
    private Integer mId;
    /**
    * email xxx@xxx.com
    */
    private String mFrom;
    
    private String mTo;
    /**
    * 邮件标题
    */
    private String mSubject;
    /**
    * 邮件正文
    */
    private String mContent;
    /**
    * 发邮件日期时间
    */
    private Date mTime;
    /**
    * 是否已读
    */
    private Integer mFlag;


}