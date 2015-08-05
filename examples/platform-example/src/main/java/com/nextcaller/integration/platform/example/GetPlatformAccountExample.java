package com.nextcaller.integration.platform.example;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class GetPlatformAccountExample {

    private static final Logger logger = LoggerFactory.getLogger(GetPlatformAccountExample.class);

    private static final String username = "XXXXX";
    private static final String password = "XXXXX";
    private static final String accountId = "XXXXX";

    public static void main(String[] args) {
        logger.info("run get platform account");

        PlatformNextCallerClient.Builder builder = new PlatformNextCallerClient.Builder(username, password);
        PlatformNextCallerClient client = builder.setDebugMode().setSandboxMode().build();

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
        } catch (ValidateException e) {
            logger.error("ValidateException: {}", e.getMessage());
        } catch (AuthenticationException e) {
            logger.error("AuthenticationException({}): {}", e.getErrorMessage().getCode(), e.getErrorMessage().getMessage());
        } catch (IOException e) {
            logger.error("IOException: {}", e.getMessage());
        }
    }

}
