package com.nextcaller.integration.response;

public class RestError {

    private ErrorMessage error;

    public ErrorMessage getError() {
        return error;
    }

    public RestError() {
        this.error = new ErrorMessage();
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

}
