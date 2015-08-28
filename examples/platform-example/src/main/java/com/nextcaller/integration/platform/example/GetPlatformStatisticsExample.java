package com.nextcaller.integration.platform.example;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.RateLimitException;
import com.nextcaller.integration.exceptions.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GetPlatformStatisticsExample {

    private static final Logger logger = LoggerFactory.getLogger(GetPlatformStatisticsExample.class);

    private static final String username = "<api username>";
    private static final String password = "<api password>";
    private static final int page = 1;

    public static void main(String[] args) {
        logger.info("Run get platform statistics");

        PlatformNextCallerClient client = new PlatformNextCallerClient(username, password);

        try {
            Map<String, Object> response = client.getPlatformStatistics(page);
            List<Map<String, Object>> records = (List<Map<String, Object>>)response.get("object_list");
            
            for (Map<String, Object> user : records) {
                System.out.println();
                System.out.println("first name: " + user.get("first_name"));
                System.out.println("last name: " + user.get("last_name"));
                System.out.println("username: " + user.get("username"));
                System.out.println("email: " + user.get("email"));
                System.out.println("number of operations: " + user.get("number_of_operations"));
            }
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
