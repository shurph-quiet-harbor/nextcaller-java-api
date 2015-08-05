package com.nextcaller.integration.platform.example;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.String;
import java.util.Map;

public class GetFraudLevelExample {

    private static final Logger logger = LoggerFactory.getLogger(GetFraudLevelExample.class);

    private static final String username = "XXXXX";
    private static final String password = "XXXXX";
    private static final String phoneNumber = "1211211212";
    private static final String accountId = "XXXXX";

    public static void main(String[] args) {
        logger.info("Run get fraud level");

        PlatformNextCallerClient.Builder builder = new PlatformNextCallerClient.Builder(username, password);
        PlatformNextCallerClient client = builder.setDebugMode().setSandboxMode().build();

        try {
            Map<String, Object> response = client.getFraudLevel(phoneNumber, accountId);

            System.out.println("spoofed: " + response.get("spoofed"));
            System.out.println("fraud_risk: " + response.get("fraud_risk"));
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
