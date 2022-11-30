package com.domanski.backend.common.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FakeEmailService implements EmailSender{

    @Override
    public void send(String to, String subject, String msg) {
        log.info("Email send");
        log.info("To" + to);
        log.info("subject" + subject);
        log.info("msg" + msg);
    }
}
