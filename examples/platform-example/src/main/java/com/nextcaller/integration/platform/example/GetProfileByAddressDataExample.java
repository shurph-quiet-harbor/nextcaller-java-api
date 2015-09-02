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
import java.util.List;
import java.util.Map;

public class GetProfileByAddressDataExample {

    private static final Logger logger = LoggerFactory.getLogger(GetProfileByAddressDataExample.class);

    private static final String apiUsername = "<api username>";
    private static final String apiPassword = "<api password>";
    private static final Map<String, String> nameAddressData = new HashMap<String, String>(){{
        put("first_name", "Jerry");
        put("last_name", "Seinfeld");
        put("address", "129 West 81st Street");
        put("zip_code", "10024");
    }};
    private static final String accountId = "test";

    public static void main(String[] args) {
        logger.info("Run get by address and name");

        PlatformNextCallerClient client = new PlatformNextCallerClient(apiUsername, apiPassword);

        try {
            Map<String, Object> response = client.getByNameAddress(nameAddressData, accountId);
            List<Map<String, Object>> records = (List<Map<String, Object>>)response.get("records");
            Map<String, Object> profile = records.get(0);
            
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
