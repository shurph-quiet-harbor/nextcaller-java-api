package com.nextcaller.integration.platform.example;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GetProfileByPhoneExample {

    private static final Logger logger = LoggerFactory.getLogger(GetProfileByPhoneExample.class);

    private static final String username = "XXXXX";
    private static final String password = "XXXXX";
    private static final String phoneNumber = "1211211212";
    private static final String accountId = "XXXXX";

    public static void main(String[] args) {
        logger.info("Run get by phone");

        PlatformNextCallerClient client = new PlatformNextCallerClient(username, password);

        try {
            Map<String, Object> response = client.getByPhone(phoneNumber, accountId);
            List<Map<String, Object>> records = (List<Map<String, Object>>)response.get("records");
            Map<String, Object> profile = records.get(0);
            
            System.out.println("first name: " + profile.get("first_name"));
            System.out.println("middle name: " + profile.get("middle_name"));
            System.out.println("last name: " + profile.get("last_name"));
            System.out.println("email: " + profile.get("email"));
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
