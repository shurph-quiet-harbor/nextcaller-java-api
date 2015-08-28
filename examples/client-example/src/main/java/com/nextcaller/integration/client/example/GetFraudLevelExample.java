package com.nextcaller.integration.client.example;

import com.nextcaller.integration.client.NextCallerClient;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.RateLimitException;
import com.nextcaller.integration.exceptions.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class GetFraudLevelExample {

    private static final Logger logger = LoggerFactory.getLogger(GetFraudLevelExample.class);

    private static final String username = "<api username>";
    private static final String password = "<api password>";
    private static final String phone = "2125558383";

    public static void main(String[] args) {
        logger.info("Run get fraud level");

        NextCallerClient client = new NextCallerClient(username, password);

        try {
            Map<String, Object> response = client.getFraudLevel(phone);

            System.out.println("spoofed: " + response.get("spoofed"));
            System.out.println("fraud_risk: " + response.get("fraud_risk"));
        } catch (HttpException e) {
            logger.error("HttpException: http status code {}. response code {}. response message: {}.",
                    e.getHttpStatusCode(), e.getErrorMessage().getCode(), e.getErrorMessage().getMessage());
        } catch (ValidateException e) {
            logger.error("ValidateException: {}", e.getMessage());
        } catch (AuthenticationException e) {
            logger.error("AuthenticationException({}): {}", e.getErrorMessage().getCode(), e.getErrorMessage().getMessage());
        } catch (RateLimitException e) {
            logger.error("RateLimitException: rate limit - {}, reset time - {}", e.getRateLimit(), e.getResetTime());
        } catch (IOException e) {
            logger.error("IOException: {}", e.getMessage());
        }
    }

}
