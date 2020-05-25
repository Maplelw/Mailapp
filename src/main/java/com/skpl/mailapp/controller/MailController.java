package com.skpl.mailapp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skpl.mailapp.entity.Mail;
import com.skpl.mailapp.entity.User;
import com.skpl.mailapp.service.MailService;
import com.skpl.mailapp.service.UserService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.SingleByte;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * (Mail)表控制层
 *
 * @author makejava
 * @since 2020-04-24 11:21:11
 */
@RestController
public class MailController {
    /**
     * 服务对象
     */
    @Resource
    private MailService mailService;

    @Resource
    private UserService userService;


    /**
     * 获取所有邮件
     *
     * @return
     * @author maple
     * @Date 2020/5/24 15:22
     * @Param [mailId]
     */
    @GetMapping("mail/user")
    public JSONObject mailList(HttpServletRequest request) {
        String userMail = new String(Base64.getDecoder().decode(request.getHeader("jwt").getBytes()));
        User user = userService.queryByEmail(userMail);
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            List<Mail> mails = mailService.queryAllExFilter(userMail);
            List<Mail.MailToAPP> resMails = new ArrayList<>();
            List<Mail.MailToAPP> sendMails = new ArrayList<>();
            if (mails != null && !mails.isEmpty()) {
                // 处理接收的邮件
                for (Mail mail : mails) {
                    Mail.MailToAPP resMail = mail.toMailToAPP(mail);
                    // 设置发送者信息
                    String senderName = userService.queryByEmail(mail.getM_from()).getU_name();
                    resMail.setSender(new Mail.Person(senderName, mail.getM_from()));
                    // 设置接收者信息
                    String receiverName = userService.queryByEmail(mail.getM_to()).getU_name();
                    List<Mail.Person> receiver = new ArrayList<>();
                    receiver.add(new Mail.Person(receiverName, mail.getM_to()));
                    resMail.setReceiver(receiver);
                    // 接收者是自己
                    if (mail.getM_to().equals(userMail)) {
                        resMails.add(resMail);
                        mail.setM_flag(1);
                        mailService.update(mail);
                    } else { //发送者是自己
                        sendMails.add(resMail);
                    }
                }
                // 处理发送的邮件
                for (int i = 0; i < sendMails.size(); i++) {
                    for (int j = i + 1; j < sendMails.size(); j++) {
                        Mail.MailToAPP item1 = sendMails.get(i);
                        Mail.MailToAPP item2 = sendMails.get(j);
                        if (item1.getTime().equals(item2.getTime()) && !item1.getMailId().equals(item2.getMailId())) {
                            sendMails.get(i).getReceiver().add(item2.getReceiver().get(0));
                            sendMails.get(i).setReceiver(sendMails.get(i).getReceiver());
                            sendMails.get(j).setTime("0");
                        }
                    }
                }
                for (int i = 0; i < sendMails.size(); i++) {
                    if ("0".equals(sendMails.get(i).getTime())) {
                        sendMails.remove(i);
                        i--;
                    }
                }
                resMails.addAll(sendMails);
                res.put("status", "success");
                data.put("list", resMails);
                res.put("data", data);
            } else {
                res.put("status", "success");
                data.put("list",mails);
                res.put("data",data);
            }
        } catch (Exception e) {
            res.put("status", "error");
            res.put("error", "程序遇到异常");
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 删除邮件
     *
     * @return
     * @author maple
     * @Date 2020/5/24 15:22
     * @Param [mailId]
     */
    @DeleteMapping("mail/user")
    public JSONObject mailList(HttpServletRequest request, @RequestBody Map map) {
        String userMail = new String(Base64.getDecoder().decode(request.getHeader("jwt").getBytes()));
        JSONObject res = new JSONObject();
        String mailID = (String) map.get("mailId");
        User user = userService.queryByEmail(userMail);
        try {
            Integer id = Integer.parseInt(mailID);
            Mail mail = mailService.queryById(id);
            if (mail != null) {
                if (!mail.getM_to().equals(userMail) && !mail.getM_from().equals(userMail)) {
                    res.put("status", "error");
                    res.put("error", "无法删除不属于你的邮件");
                } else {
                    mailService.deleteById(id);
                    res.put("status", "success");
                }
            } else {
                res.put("status", "error");
                res.put("error", "不存在的ID");
            }
        } catch (Exception e) {
            res.put("status", "error");
            res.put("error", "ID形式错误");
        }
        return res;
    }
}