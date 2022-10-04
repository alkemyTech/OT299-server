package com.alkemy.ong.web;

import com.alkemy.ong.domain.emails.SendGridMailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/send-email")
public class EmailController {

    private final SendGridMailService sendGridMailService;

    @PostMapping("/{email}")
    public ResponseEntity<String> sendEmail(@PathVariable String email){
        Integer responseCodeStatus = sendGridMailService.sendMail(email);
        if(responseCodeStatus==200 || responseCodeStatus==202)
            return new ResponseEntity<>("Send succesfully", HttpStatus.OK);
        return new ResponseEntity<>("failed to sent", HttpStatus.NOT_FOUND);
    }

}
