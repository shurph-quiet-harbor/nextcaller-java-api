package com.nextcaller.integration.platform.example;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.RateLimitException;
import com.nextcaller.integration.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class GetPlatformAccountExample {

    private static final Logger logger = LoggerFactory.getLogger(GetPlatformAccountExample.class);

    private static final String apiUsername = "<api username>";
    private static final String apiPassword = "<api password>";
    private static final String accountId = "test";

    public static void main(String[] args) {
        logger.info("run get platform account");

        PlatformNextCallerClient client = new PlatformNextCallerClient(apiUsername, apiPassword);

        try {
            Map<String, Object> account = client.getPlatformAccount(accountId);

            System.out.println("first name: " + account.get("first_name"));
            System.out.println("last name: " + account.get("last_name"));
            System.out.println("account id: " + account.get("account_id"));
            System.out.println("email: " + account.get("email"));
            System.out.println("number of operations: " + account.get("number_of_operations"));
        } catch (HttpException e) {
            logger.error("HttpException: http status code {}. response code {}. response message: {}.",
                    e.getHttpStatusCode(), e.getErrorMessage().getCode(), e.getErrorMessage().getMessage());
        } catch (ValidationException e) {
            logger.error("ValidationException: {}", e.getMessage());
        } catch (AuthenticationException e) {
            logger.error("AuthenticationException({}): {}", e.getErrorMessage().getCode(), e.getErrorMessage().getMessage());
        } catch (RateLimitException e) {
            logger.error("RateLimitException: rate limit - {}, reset time - {}", e.getRateLimit(), e.getResetTime());
        } catch (IOException e) {
            logger.error("IOException: {}", e.getMessage());
        }
    }

}
