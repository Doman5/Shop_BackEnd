package com.domanski.backend.common.mail;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailConfig {

    @Bean
    @ConditionalOnProperty(name="app.email.sender", matchIfMissing = true, havingValue = "emailSimpleSender")
    public EmailSender emailSimpleSender(JavaMailSender javaMailSender) {
        return new EmailSimpleService(javaMailSender);
    }

    @Bean
    @ConditionalOnProperty(name="app.email.sender", havingValue = "emailFakeSender")
    public EmailSender emailFakeSender() {
        return new FakeEmailService();
    }
}
