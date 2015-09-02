package com.nextcaller.integration.platform.example;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.RateLimitException;
import com.nextcaller.integration.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreatePlatformAccountExample {

    private static final Logger logger = LoggerFactory.getLogger(CreatePlatformAccountExample.class);

    private static final String apiUsername = "<api username>";
    private static final String apiPassword = "<api password>";

    public static void main(String[] args) {
        logger.info("run update platform account");

        PlatformNextCallerClient client = new PlatformNextCallerClient(apiUsername, apiPassword);

        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("id", "test");
            data.put("first_name", "test");
            data.put("last_name", "test");
            data.put("email", "test@test.com");

            client.createPlatformAccount(data);

            logger.info("Update account success");
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
