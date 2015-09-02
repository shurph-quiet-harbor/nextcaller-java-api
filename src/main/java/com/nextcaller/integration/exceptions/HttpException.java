package com.nextcaller.integration.exceptions;

import com.nextcaller.integration.response.ErrorMessage;

public class HttpException extends NcException {

    private ErrorMessage errorMessage;
    private int httpStatusCode;

    private static String getExceptionType(int httpStatusCode) {
        return String.format("%s(%d)", HttpException.class.getSimpleName(), httpStatusCode);
    }

    public HttpException(String message, int httpStatusCode) {
        super(getExceptionType(httpStatusCode), message);

        this.httpStatusCode = httpStatusCode;
    }

    public HttpException(ErrorMessage err, int httpStatusCode) {
        super(getExceptionType(httpStatusCode),
                String.format("error code - %d, message - %s", err.getCode(), err.getMessage()));

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
