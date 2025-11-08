package com.fastgo.authentication.fatsgo_authentication.dto;

public class RegistrationResultDTO {

    public final static int OK = 0;
    public final static int EMAIL_ALREADY_EXISTS = 1;
    public final static int EMAIL_SYNTAX_ERROR = 2;
    public final static int USERNAME_ALREADY_EXISTS = 3;
    public final static int PHONE_ALREADY_EXISTS = 4;
    public final static int PHONE_SYNTAX_ERROR = 5;
    public final static int GENERAL_ERROR = 99;

    private int result;
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
