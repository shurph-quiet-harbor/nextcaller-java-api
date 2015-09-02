package com.nextcaller.integration.exceptions;

public class RateLimitException extends NcException {

    private int rateLimit;
    private long resetTime;

    public RateLimitException(String message, int rateLimit, long resetTime) {
        super(RateLimitException.class.getSimpleName(), message);

        this.rateLimit = rateLimit;
        this.resetTime = resetTime;
    }

    public int getRateLimit() {
        return rateLimit;
    }

    public long getResetTime() {
        return resetTime;
    }
}
