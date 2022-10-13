package com.alkemy.ong.web;

import com.alkemy.ong.domain.comments.Comment;
import com.alkemy.ong.domain.emails.Email;
import com.alkemy.ong.domain.emails.MailService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@RestController
@AllArgsConstructor
@RequestMapping("/send-email")
public class EmailController {

    private final MailService mailService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody EmailDto emailDto){
        mailService.sendMail(toModel(emailDto));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Email toModel(EmailDto emailDto){

        return Email.builder()
                .emailRecipient(emailDto.getEmailRecipient())
                .subject(emailDto.getSubject())
                .build();
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class EmailDto{

        public EmailDto() {
        }

        private String emailRecipient;
        private String subject;
    }

}