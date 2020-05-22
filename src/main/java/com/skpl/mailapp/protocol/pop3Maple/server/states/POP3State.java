package com.skpl.mailapp.protocol.pop3Maple.server.states;

/**
 * @author maple
 * @Date 2020/5/10 9:13
 */
public interface POP3State {
    void handle(String data);
}
