package com.skpl.mailapp.protocol.pop3.server.state;


import com.skpl.mailapp.protocol.pop3.server.context.POP3Context;

/**
 * POP3State.
 */
public interface POP3State {

    /**
     * handle the command from clients
     */
    void handle(POP3Context pop3Context, String[] args);

    /**
     * update operations into databases
     */
    void updateDB();
}
