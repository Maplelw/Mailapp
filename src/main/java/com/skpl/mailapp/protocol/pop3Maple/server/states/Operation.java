package com.skpl.mailapp.protocol.pop3Maple.server.states;

import com.skpl.mailapp.protocol.pop3Maple.server.context.POP3Context;

/**
 * pop3操作类
 * 增删改查邮件
 *
 * @author maple
 * @Date 2020/5/21 21:19
 */
public class Operation implements POP3State {

    private POP3Context pop3Context;

    public Operation(POP3Context pop3Context) {
        this.pop3Context = pop3Context;
    }

    @Override
    public void handle(String data) {
        String num = data.substring("retr ".length());
        System.out.println(num);
        // 接收邮件命令
        if (data.toLowerCase().startsWith("retr")) {

        }
        // 接收邮件命令
        if(data.toLowerCase().startsWith("dele")) {// 删除邮件命令

        }
    }
}
