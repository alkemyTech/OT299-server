package com.alkemy.ong.domain.exceptions;

public class MailException extends RuntimeException {

    public MailException(String errorMessage) {
        super(errorMessage);
    }
}
