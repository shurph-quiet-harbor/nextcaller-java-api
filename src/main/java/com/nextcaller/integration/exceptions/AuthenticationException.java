package com.nextcaller.integration.exceptions;

import com.nextcaller.integration.response.ErrorMessage;

public class AuthenticationException extends NcException {

    private ErrorMessage errorMessage;

    public AuthenticationException(ErrorMessage err) {
        super(String.format("%s(%d)", AuthenticationException.class.getSimpleName(), err.getCode()), err.getMessage());

        this.errorMessage = err;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
