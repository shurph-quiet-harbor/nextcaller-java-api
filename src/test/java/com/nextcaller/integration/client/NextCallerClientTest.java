package com.nextcaller.integration.client;

import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.ValidateException;
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
            throws HttpException, IOException, AuthenticationException, ValidateException {
        return client.getByPhone(phone);
    }

    @Override
    public Map<String, Object> getByAddressName(Map<String, String> addressNameData)
            throws HttpException, IOException, AuthenticationException, ValidateException {
        return client.getByAddressName(addressNameData);
    }

    @Override
    public Map<String, Object> getByProfileId(String profileId)
            throws HttpException, IOException, AuthenticationException, ValidateException {
        return client.getByProfileId(profileId);
    }

    @Override
    public Boolean updateByProfileId(String profileId, Map<String, Object> newProfile)
            throws HttpException, IOException, AuthenticationException, ValidateException {
        return client.updateByProfileId(profileId, newProfile);
    }

    @Test
    public void testGetByPhone() throws HttpException, IOException, AuthenticationException, ValidateException {
        super.testGetByPhone();
    }

    @Test(expected = HttpException.class)
    public void testByShortPhone() throws HttpException, IOException, AuthenticationException, ValidateException {
        super.testByShortPhone();
    }

    @Test(expected = HttpException.class)
    public void testByAddressNameWithNotFullDataAddressName() throws HttpException, IOException, AuthenticationException, ValidateException {
        super.testByAddressNameWithNotFullDataAddressName();
    }

    @Test
    public void testGetByAddressName() throws HttpException, IOException, AuthenticationException, ValidateException {
        super.testGetByAddressName();
    }

    @Test
    public void testGetByProfileId() throws HttpException, IOException, AuthenticationException, ValidateException {
        super.testGetByProfileId();
    }

    @Test
    public void testUpdateByProfileId() throws HttpException, IOException, AuthenticationException, ValidateException {
        super.testUpdateByProfileId();
    }

    @Test
    public void testProfileUpdateWrongRequest() throws HttpException, IOException, AuthenticationException, ValidateException {
        super.testProfileUpdateWrongRequest();
    }

    @Test
    public void testFraudLevel() throws HttpException, IOException, AuthenticationException, ValidateException {
        super.testFraudLevel();
    }
}
