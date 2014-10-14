package com.nextcaller.integration.response;

public class ErrorMessage {

    private String message;
    private int code;
    private String type;

    public ErrorMessage() {
        this.message = "";
        this.code = 0;
        this.type = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
