package com.skpl.mailapp.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
import java.util.stream.DoubleStream;

import com.sun.org.apache.regexp.internal.RE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.DocFlavor;

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
        return new MailToAPP(mail.getM_id(),mail.m_from,mail.getM_to(),mail.getM_subject(),mail.getM_content(),resTime,mail.getM_flag());
    }

    public class MailToAPP {
        private Integer m_id;
        private String m_from;
        private String m_to;
        private String m_subject;
        private String m_content;
        private String m_time;
        private Integer m_flag;

        public MailToAPP(Integer m_id, String m_from, String m_to, String m_subject, String m_content, String m_time, Integer m_flag) {
            this.m_id = m_id;
            this.m_from = m_from;
            this.m_to = m_to;
            this.m_subject = m_subject;
            this.m_content = m_content;
            this.m_time = m_time;
            this.m_flag = m_flag;
        }

        public Integer getM_id() {
            return m_id;
        }

        public void setM_id(Integer m_id) {
            this.m_id = m_id;
        }

        public String getM_from() {
            return m_from;
        }

        public void setM_from(String m_from) {
            this.m_from = m_from;
        }

        public String getM_to() {
            return m_to;
        }

        public void setM_to(String m_to) {
            this.m_to = m_to;
        }

        public String getM_subject() {
            return m_subject;
        }

        public void setM_subject(String m_subject) {
            this.m_subject = m_subject;
        }

        public String getM_content() {
            return m_content;
        }

        public void setM_content(String m_content) {
            this.m_content = m_content;
        }

        public String getM_time() {
            return m_time;
        }

        public void setM_time(String m_time) {
            this.m_time = m_time;
        }

        public Integer getM_flag() {
            return m_flag;
        }

        public void setM_flag(Integer m_flag) {
            this.m_flag = m_flag;
        }
    }
}