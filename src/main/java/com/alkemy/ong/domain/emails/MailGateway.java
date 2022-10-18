package com.alkemy.ong.domain.emails;

import org.springframework.stereotype.Component;

@Component
public interface MailGateway {

    void sendMail(String recipientEmail, String subject, String content);
}