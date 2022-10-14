package com.alkemy.ong.domain.emails;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailService {

    private final MailGateway mailGateway;

    public void sendMail(Email email){
        mailGateway.sendMail(email.getEmailRecipient(), email.getSubject(), email.getContent());
    }

}