package com.alkemy.ong.emails;

import com.alkemy.ong.domain.emails.MailGateway;
import com.alkemy.ong.domain.exceptions.MailException;
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
public class DefaultMailGateway implements MailGateway {

    private final SendGrid sendGrid;

    private static final String ERROR_MESSAGE = "failed to send email";

    @Value("${emailSender}")
    private String emailSender;
    @Override
    public void sendMail(String recipientEmail) {
        Email from = new Email(this.emailSender);
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email(recipientEmail);
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = this.sendGrid.api(request);
            if(response.getStatusCode()!=200 && response.getStatusCode()!=202){
                throw new MailException(ERROR_MESSAGE);
            }


        } catch (IOException ex) {
            ex.printStackTrace();
            throw new MailException(ERROR_MESSAGE);
        }
    }
}
