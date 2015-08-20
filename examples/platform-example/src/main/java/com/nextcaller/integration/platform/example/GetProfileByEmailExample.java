package com.nextcaller.integration.platform.example;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class GetProfileByEmailExample {

    private static final Logger logger = LoggerFactory.getLogger(GetProfileByEmailExample.class);

    private static final String username = "<api username>";
    private static final String password = "<api password>";
    private static final String email = "email@exmaple.com";
    private static final String accountId = "test";

    public static void main(String[] args) {
        logger.info("Run get by profile id");

        PlatformNextCallerClient client = new PlatformNextCallerClient(username, password);

        try {
            Map<String, Object> profile = client.getByEmail(email, accountId);

            System.out.println("first name: " + profile.get("first_name"));
            System.out.println("middle name: " + profile.get("middle_name"));
            System.out.println("last name: " + profile.get("last_name"));
            System.out.println("email: " + profile.get("email"));
        } catch (HttpException e) {
            logger.error("HttpException: http status code {}. response code {}. response message: {}.",
                    e.getHttpStatusCode(), e.getErrorMessage().getCode(), e.getErrorMessage().getMessage());
        } catch (AuthenticationException e) {
            logger.error("AuthenticationException({}): {}", e.getErrorMessage().getCode(), e.getErrorMessage().getMessage());
        } catch (IOException e) {
            logger.error("IOException: {}", e.getMessage());
        }
    }

}
