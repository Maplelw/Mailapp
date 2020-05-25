package com.skpl.mailapp.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
import java.util.List;
import java.util.stream.DoubleStream;

import com.sun.org.apache.regexp.internal.RE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.DocFlavor;
import javax.xml.soap.Detail;

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
    private Integer m_id;
    /**
     * email xxx@xxx.com
     */
    private String m_from;

    private String m_to;
    /**
     * 邮件标题
     */
    private String m_subject;
    /**
     * 邮件正文
     */
    private String m_content;
    /**
     * 发邮件日期时间
     */
    private Date m_time;
    /**
     * 是否已读
     */
    private Integer m_flag;

    public MailToAPP toMailToAPP(Mail mail) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String resTime = format.format(mail.getM_time());
        boolean flag = false;
        if(mail.m_flag == 1) {
            flag = true;
        }
        return new MailToAPP(mail.m_id,null,null, new Detail(mail.m_subject, m_content),resTime,flag);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class MailToAPP {
        private Integer mailId;
        private Person sender;
        private List<Person> receiver;
        private Detail data;
        private String time;
        private Boolean isRead;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Person {
        String name;
        String mail;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {
        String title;
        String content;
    }
}