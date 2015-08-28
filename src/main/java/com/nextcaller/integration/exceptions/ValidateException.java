package com.nextcaller.integration.exceptions;

public class ValidateException extends NcException {

    public ValidateException(String message) {
        super(ValidateException.class.getSimpleName(), message);
    }
}
