package com.nextcaller.integration.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateException extends Exception {

    public ValidateException(String message) {
        super("ValidateException : " + message);
    }

}
