package com.nextcaller.integration.client.example;

import com.nextcaller.integration.client.NextCallerClient;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.String;
import java.util.List;
import java.util.Map;

public class GetProfileByAddressDataExample {

    private static final Logger logger = LoggerFactory.getLogger(GetProfileByAddressDataExample.class);

    private static final String username = "<api username>";
    private static final String password = "<api password>";
    private static final Map<String, String> addressNameData = new HashMap<String, String>(){{
        put("first_name", "Jerry");
        put("last_name", "Seinfeld");
        put("address", "129 West 81st Street");
        put("zip_code", "10024");
    }};

    public static void main(String[] args) {
        logger.info("Run get by address and name");

        NextCallerClient client = new NextCallerClient(username, password);

        try {
            Map<String, Object> response = client.getByAddressName(addressNameData);
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
