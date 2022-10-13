package com.alkemy.ong.domain.emails;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Email {

    private String emailRecipient;
    private String subject;
    private String content;
}