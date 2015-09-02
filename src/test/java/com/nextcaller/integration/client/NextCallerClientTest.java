package com.nextcaller.integration.client;

import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.RateLimitException;
import com.nextcaller.integration.exceptions.ValidationException;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class NextCallerClientTest extends AbstractClientTest {

    private static final String username = "XXXXX";
    private static final String password = "XXXXX";

    private NextCallerClient client;

    public NextCallerClientTest() throws IOException, NoSuchFieldException, IllegalAccessException {

        super();

        client = new NextCallerClient(username, password);
        mockClient(client);
    }

    @Override
    public Map<String, Object> getByPhone(String phone)
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        return client.getByPhone(phone);
    }

    @Override
    public Map<String, Object> getByNameAddress(Map<String, String> nameAddressData)
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        return client.getByNameAddress(nameAddressData);
    }

    @Override
    public Map<String, Object> getByProfileId(String profileId)
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        return client.getByProfileId(profileId);
    }

    @Override
    public Boolean updateByProfileId(String profileId, Map<String, Object> newProfile)
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        return client.updateByProfileId(profileId, newProfile);
    }

    @Test
    public void testGetByPhone()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        super.testGetByPhone();
    }

    @Test(expected = HttpException.class)
    public void testByShortPhone()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        super.testByShortPhone();
    }

    @Test(expected = ValidationException.class)
    public void testByNameAddressWithNotFullData()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        super.testByNameAddressWithNotFullData();
    }

    @Test(expected = RateLimitException.class)
    public void testRateLimit()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        super.testRateLimit();
    }

    @Test
    public void testGetByNameAddress()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        super.testGetByNameAddress();
    }

    @Test
    public void testGetByProfileId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        super.testGetByProfileId();
    }

    @Test
    public void testUpdateByProfileId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        super.testUpdateByProfileId();
    }

    @Test
    public void testProfileUpdateWrongRequest()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        super.testProfileUpdateWrongRequest();
    }

    @Test
    public void testFraudLevel()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        super.testFraudLevel();
    }
}
