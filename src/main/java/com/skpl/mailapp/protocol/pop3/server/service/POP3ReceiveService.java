package com.skpl.mailapp.protocol.pop3.server.service;


import com.skpl.mailapp.protocol.pop3.server.context.POP3Context;
import com.skpl.mailapp.protocol.pop3.server.state.POP3State;

/**
 * the data state of pop3 service
 */
public class POP3ReceiveService {

    private String[] commands = {"user", "pass", "stat", "list", "retr", "dele", "rset", "quit"};
    private POP3State pop3State = null;

    public POP3ReceiveService(POP3State pop3State) {
        this.pop3State = pop3State;
    }

    public void setPop3State(POP3State pop3State) {
        this.pop3State = pop3State;
    }

    /**
     * handle the input command from client
     */
    public void handleInputCommand(POP3Context pop3Context, String inputString) {
        System.out.println("C:" + inputString);
        String[] args = inputString.split("\\s+");
        String command = args[0].toLowerCase();

        if (!checkCommand(command)) {
            pop3Context.feedbackToClient("-ERR Unknown Command");
            return;
        }

        if (command.equals("quit")) {
            pop3Context.feedbackToClient("+OK 1703 mail quit");
            pop3Context.closeConnection();
            return;
        }

        pop3State.handle(pop3Context, args);
    }

    /**
     * check the command is valid
     */
    public boolean checkCommand(String cmd) {
        boolean flag = false;
        for (String s : commands) {
            if (cmd.equals(s)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
