package com.nextcaller.integration.response;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {

    /**
     * server error message
     */
    private String message;

    /**
     * server error code. Description look at https://nextcaller.com/platform/documentation/error-responses/
      */
    private int code;

    /**
     * error type
     */
    private String type;

    /**
     * description error. For example: descriptions of fields validation error
     */
    private Map<String, Object> description;

    public ErrorMessage() {
        this.message = "";
        this.code = 0;
        this.type = "";
        this.description = new HashMap<String, Object>();
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

    public Map<String, Object> getDescription() {
        return description;
    }

    public void setDescription(Map<String, Object> description) {
        this.description = description;
    }
}
