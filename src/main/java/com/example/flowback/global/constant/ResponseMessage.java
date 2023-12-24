package com.example.flowback.global.constant;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    SUCCESS("success"),
    FAIL("fail"),
    ;

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }
}
