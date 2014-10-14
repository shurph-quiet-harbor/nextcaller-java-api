package com.nextcaller.integration.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(ValidateException.class);

    public ValidateException(String message) {
        super("ValidateException: " + message);

        logger.error("ValidateException: " + message);
    }

}
