package com.skpl.mailapp.protocol.smtp.states;

/**
 * @author maple
 * @Date 2020/5/10 9:13
 */
public interface SMTPState {
    void handle(String data);
}
