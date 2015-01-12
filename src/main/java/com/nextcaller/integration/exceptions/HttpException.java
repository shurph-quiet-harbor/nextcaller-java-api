package com.nextcaller.integration.exceptions;

import com.nextcaller.integration.response.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpException extends Exception {

    private ErrorMessage errorMessage;
    private int httpStatusCode;

    public HttpException(String message, int httpStatusCode) {
        super("HttpException(" + httpStatusCode + ") : " + message);
    }

    public HttpException(ErrorMessage err, int httpStatusCode) {
        super("HttpException(" + httpStatusCode + ") : http status code - " + err.getCode() + ", message - "
                + err.getMessage());

        this.errorMessage = err;
        this.httpStatusCode = httpStatusCode;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
