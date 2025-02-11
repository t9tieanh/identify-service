package com.phamtienanh.identify_service.exception;

public enum ErrorCode {
    INVALID_KEY(123,"Invalid request body"),
    USER_EXISTED(1001,"User exitsted"),
    USER_NOT_FOUND(1002,"User not found"),
    WRONG_PASSWORD(1003,"Wrong password"),
    UNCATEGORIZED_EXEPTION(999,"Uncategorized error"),
    USERNAME_INVALID(1004,"username must be at least 3 characters"),
    PASSWORD_INVALID(1005,"password must be at least 8 characters")
    ;

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

}
