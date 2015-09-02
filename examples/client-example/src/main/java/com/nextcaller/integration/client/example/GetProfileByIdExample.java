package com.nextcaller.integration.client.example;

import com.nextcaller.integration.client.NextCallerClient;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.RateLimitException;
import com.nextcaller.integration.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class GetProfileByIdExample {

    private static final Logger logger = LoggerFactory.getLogger(GetProfileByIdExample.class);

    private static final String apiUsername = "<api username>";
    private static final String apiPassword = "<api password>";
    private static final String profileId = "97d949a413f4ea8b85e9586e1f2d9a";

    public static void main(String[] args) {
        logger.info("Run get by profile id");

        NextCallerClient client = new NextCallerClient(apiUsername, apiPassword);

        try {
            Map<String, Object> profile = client.getByProfileId(profileId);

            System.out.println("first name: " + profile.get("first_name"));
            System.out.println("middle name: " + profile.get("middle_name"));
            System.out.println("last name: " + profile.get("last_name"));
            System.out.println("email: " + profile.get("email"));
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
