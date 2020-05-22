package com.skpl.mailapp.protocol.pop3.server.state;


import com.skpl.mailapp.protocol.pop3.server.Manager;
import com.skpl.mailapp.protocol.pop3.server.context.POP3Context;
import com.skpl.mailapp.protocol.pop3.server.domain.User;

/**
 * login in.
 */
public class LoginState implements POP3State{

    private String content = null;
    private User user = null;

    public LoginState(){
        this.content = "user";
        this.user = new User();
    }

    @Override
    public void handle(POP3Context pop3Context, String[] args) {
        String cmd = args[0];

        if(!cmd.equalsIgnoreCase("user") && !cmd.equalsIgnoreCase("pass")){
            pop3Context.feedbackToClient("-ERR Auth first");
            return;
        }

        if(!content.equalsIgnoreCase(cmd)){
            pop3Context.feedbackToClient("-ERR "+ content+" need");
            return;
        }

        if(args.length != 2){
            pop3Context.feedbackToClient("-ERR Syntax error");
            return;
        }

        if(content.equals("user")) {
            if (!Manager.isUserExist(args[1])) {
                pop3Context.feedbackToClient("-ERR User Not Exist");
                pop3Context.closeConnection();
                return;
            }
            pop3Context.feedbackToClient("+OK 1703 mail");
            this.user.setUsername(args[1]);
            this.content = "pass";
        }else{
            this.user.setPassword(args[1]);
            if(!Manager.authUser(this.user)){
                pop3Context.feedbackToClient("-ERR Incorrect Password");
                System.out.println("User: "+this.user.getUsername()+" login failed");
                pop3Context.closeConnection();
                return;
            }
            pop3Context.feedbackToClient("+OK "+Manager.getMailNumber(this.user.getUsername())+" message(s) ["+Manager.getMailBytes(this.user.getUsername())+" byte(s)]");
            System.out.println("User: "+this.user.getUsername()+" login successfully");
            //登录后的操作
            pop3Context.getPop3ReceiveService().setPop3State(new ApplyState(this.user));
            this.content = "quit";
        }
    }

    @Override
    public void updateDB() {

    }
}
