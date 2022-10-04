package com.alkemy.ong.domain.emails;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SendGridMailService {

    private final SendGridMailGateway sendGridMailGateway;

    public Integer sendMail(String recipientEmail){
        return sendGridMailGateway.sendMail(recipientEmail);
    }

}
