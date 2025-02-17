package com.phamtienanh.identify_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    INVALID_KEY(123,"Invalid request body",HttpStatus.BAD_REQUEST),
    USER_EXISTED(1001,"User exitsted",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002,"User not found",HttpStatus.NOT_FOUND),
    WRONG_PASSWORD(1003,"Wrong password",HttpStatus.UNAUTHORIZED),
    UNCATEGORIZED_EXEPTION(999,"Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(1006,"You do not have permission", HttpStatus.UNAUTHORIZED),
    USERNAME_INVALID(1004,"username must be at least 3 characters",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1005,"password must be at least 8 characters",HttpStatus.BAD_REQUEST)
    ;

    private int code;
    private HttpStatusCode statusCode;
    private String message;


    ErrorCode (int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

}
