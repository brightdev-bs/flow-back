package com.example.flowback.global.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    EXTENSION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 확장자입니다."),
    EXTENSION_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 확장자입니다."),
    EXTENSION_INVALID_EXCEPTION(HttpStatus.BAD_REQUEST, "확장자는 1~20자 사이로 입력해주세요."),
    ;


    String message;
    HttpStatus status;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
