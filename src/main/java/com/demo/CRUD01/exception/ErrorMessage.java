package com.demo.CRUD01.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


public enum ErrorMessage {


    //400
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "이름을 적어주세요");


    private final HttpStatus status;
    private final String message;

    ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
