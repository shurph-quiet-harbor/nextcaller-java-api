package com.nextcaller.integration.exceptions;

import com.nextcaller.integration.response.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationException extends Exception {

    private ErrorMessage errorMessage;

    public AuthenticationException(ErrorMessage err) {
        super("AuthenticationException(" + err.getCode() + ") : " + err.getMessage());

        this.errorMessage = err;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

}
