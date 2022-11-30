package com.domanski.backend.common.mail;

public interface EmailSender {
    void send(String to, String subject, String msg);
}
