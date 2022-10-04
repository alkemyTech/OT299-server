package com.alkemy.ong.emails;

import com.alkemy.ong.domain.emails.SendGridMailGateway;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class DefaultSendGridMailGateway implements SendGridMailGateway {

    private final SendGrid sendGrid;

    @Value("${emailSender}")
    private String emailSender;
    @Override
    public Integer sendMail(String recipientEmail) {
        Email from = new Email(this.emailSender);
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email(recipientEmail);
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        Integer statusCode = null;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = this.sendGrid.api(request);
            statusCode= response.getStatusCode();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return statusCode;
    }
}
