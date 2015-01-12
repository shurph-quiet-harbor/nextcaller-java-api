package com.nextcaller.integration.response;

public class RestError {

    private ErrorMessage error;

    public RestError() {
        this.error = new ErrorMessage();
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

}
