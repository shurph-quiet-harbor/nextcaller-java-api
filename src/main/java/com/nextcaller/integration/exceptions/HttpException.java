package com.nextcaller.integration.exceptions;

import com.nextcaller.integration.response.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(HttpException.class);
    private ErrorMessage errorMessage;

    public HttpException(String message) {
        super("HttpException: " + message);

        logger.error("HttpException: " + message);
    }

    public HttpException(ErrorMessage err) {
        super("HttpException(" + err.getCode() + ") : " + err.getMessage());

        this.errorMessage = err;

        logger.error("HttpException(" + err.getCode() + ") : " + err.getMessage());
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
