package com.nextcaller.integration.exceptions;

import com.nextcaller.integration.response.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationException.class);
    private ErrorMessage errorMessage;

    public AuthenticationException(ErrorMessage err) {
        super("AuthenticationException(" + err.getCode() + ") : " + err.getMessage());

        this.errorMessage = err;

        logger.error("AuthenticationException(" + err.getCode() + ") : " + err.getMessage());
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
