package com.skpl.mailapp.protocol.pop3.server.state;


import com.skpl.mailapp.protocol.pop3.server.Manager;
import com.skpl.mailapp.protocol.pop3.server.context.POP3Context;
import com.skpl.mailapp.protocol.pop3.server.domain.User;

import java.util.ArrayList;

/**
 * 登陆后的一些操作.
 */
public class ApplyState implements POP3State{

    private User user = null;

    public ApplyState(User user){
        this.user = user;
    }

    @Override
    public void handle(POP3Context pop3Context, String[] args) {
        String command = args[0].toLowerCase();
        switch (command){
            case "stat":
                this.statState(pop3Context,args);
                break;
            case "list":
                this.listState(pop3Context,args);
                break;
            case "retr":
                this.retrState(pop3Context,args);
                break;
            case "dele":
                this.deleState(pop3Context,args);
                break;
            case "rset":
                this.rsetState(pop3Context,args);
                break;
            default:
                pop3Context.feedbackToClient("-ERR Unknown Command");
                break;
        }

    }

    //执行删除操作的
    @Override
    public void updateDB() {

    }

    /**
     * stat
     */
    public void statState(POP3Context pop3Context, String[] args){
        if(args.length == 1) {
            String mailStatus = Manager.getMailStatus(this.user.getUsername());
            pop3Context.feedbackToClient("+OK "+mailStatus);
        }else{
            pop3Context.feedbackToClient("-ERR Syntax error");
        }
    }

    /**
     * list
     */
    public void listState(POP3Context pop3Context, String[] args){
        //非无参或有参
        if(args.length != 1 && args.length != 2){
            pop3Context.feedbackToClient("-ERR Syntax error");
        }

        //无参则返回每封邮件的编号和大小，最后以"."结束
        //有参则返回对应编号的邮件的大小
        if(args.length == 1){
            pop3Context.feedbackToClient("+OK "+Manager.getMailStatus(this.user.getUsername()));
            int mailNum = Manager.getMailNumber(this.user.getUsername());
            int i = 1;
            ArrayList<Integer> mailList = Manager.getMailList(this.user.getUsername());
            while(i <= mailNum){
                pop3Context.feedbackToClient(i+" "+mailList.get(i-1));
                i++;
            }
            pop3Context.feedbackToClient(".");
        }else{
            ArrayList<Integer> mailList = Manager.getMailList(this.user.getUsername());
            int mailNum = Manager.getMailNumber(this.user.getUsername());
            if(Integer.parseInt(args[1]) <= mailNum) {
                pop3Context.feedbackToClient("+OK " + args[1] + " " + mailList.get(Integer.parseInt(args[1])));
            }else{
                pop3Context.feedbackToClient("-ERR Unknown message");
            }
        }
    }

    /**
     * retr
     */
    public void retrState(POP3Context pop3Context, String[] args){
        if(args.length != 2) {
            pop3Context.feedbackToClient("-ERR Syntax error");
            return;
        }

        int mailNum = Manager.getMailNumber(this.user.getUsername());
        if(Integer.parseInt(args[1]) > mailNum){
            pop3Context.feedbackToClient("-ERR No Such Mail");
        }
        pop3Context.feedbackToClient("+OK retr "+Manager.getMailByte(this.user.getUsername(),Integer.parseInt(args[1]))+"\r\n"+Manager.getMailTotalContent(this.user.getUsername(),Integer.parseInt(args[1])));
    }

    /**
     * dele
     */
    public void deleState(POP3Context pop3Context, String[] args){

    }

    /**
     * rset
     */
    public void rsetState(POP3Context pop3Context, String[] args){

    }
}
