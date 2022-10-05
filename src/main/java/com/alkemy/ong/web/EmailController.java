package com.alkemy.ong.web;

import com.alkemy.ong.domain.emails.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/send-email")
public class EmailController {

    private final MailService mailService;

    @PostMapping("/{email}")
    public ResponseEntity<Void> sendEmail(@PathVariable String email){
        mailService.sendMail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
