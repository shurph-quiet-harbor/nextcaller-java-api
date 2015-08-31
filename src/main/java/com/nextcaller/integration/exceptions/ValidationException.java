package com.nextcaller.integration.exceptions;

import com.nextcaller.integration.response.ErrorMessage;

public class ValidationException extends NcException {

    public ValidationException(ErrorMessage error) {
        super(ValidationException.class.getSimpleName(), error.getMessage());
    }
}
