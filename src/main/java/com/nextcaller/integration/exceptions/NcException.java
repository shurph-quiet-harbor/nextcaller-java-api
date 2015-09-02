package com.nextcaller.integration.exceptions;

public class NcException extends Exception {

    public NcException(String type, String message) {
        super(String.format("%s : %s", type, message));
    }
}
