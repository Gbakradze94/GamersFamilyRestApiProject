package com.gamersfamily.gamersfamily.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{

    private final HttpStatus status;
    private final String messageError;

    public BlogAPIException(String messageError, HttpStatus status) {
        super(messageError);
        this.status = status;
        this.messageError = messageError;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessageError() {
        return messageError;
    }
}
