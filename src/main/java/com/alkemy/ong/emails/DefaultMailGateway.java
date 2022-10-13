package com.alkemy.ong.emails;

import com.alkemy.ong.domain.emails.MailGateway;
import com.alkemy.ong.domain.exceptions.MailException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class DefaultMailGateway implements MailGateway {

    private final SendGrid sendGrid;

    private static final String ERROR_MESSAGE = "failed to send email";

    private final EmailTemplate emailTemplate;

    @Value("${emailSender}")
    private String emailSender;
    @Override
    public void sendMail(String recipientEmail, String subject) {
        Email from = new Email(this.emailSender);
        Email to = new Email(recipientEmail);
        Mail mail = new Mail();
        DynamicTemplatePersonalization personalization = new DynamicTemplatePersonalization();
        personalization.addTo(to);
        mail.setFrom(from);
        mail.setSubject(subject);
        mail.addPersonalization(personalization);
        mail.addContent(emailTemplate.getContent());
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

    private static class DynamicTemplatePersonalization extends Personalization {

        @JsonProperty(value = "dynamic_template_data")
        private Map<String, String> dynamic_template_data;

        @JsonProperty("dynamic_template_data")
        public Map<String, String> getDynamicTemplateEmailData() {
            if (dynamic_template_data == null) {
                return Collections.<String, String>emptyMap();
            }
            return dynamic_template_data;
        }
    }

}
