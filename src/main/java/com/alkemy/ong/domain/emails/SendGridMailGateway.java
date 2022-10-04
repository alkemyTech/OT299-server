package com.alkemy.ong.domain.emails;

import org.springframework.stereotype.Component;

@Component
public interface SendGridMailGateway {

    Integer sendMail(String recipientEmail);
}
