package com.nextcaller.integration.client;

import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.RateLimitException;
import com.nextcaller.integration.exceptions.ValidationException;
import com.nextcaller.integration.response.ParseToObject;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PlatformNextCallerClientTest extends AbstractClientTest {

    private final String PLATFORM_STATISTICS_JSON_RESULT_EXAMPLE =
            "{" +
            "    \"object_list\": [" +
            "        {" +
            "            \"username\": \"test\"," +
            "            \"first_name\": \"\"," +
            "            \"last_name\": \"\"," +
            "            \"company_name\": \"\"," +
            "            \"email\": \"\"," +
            "            \"number_of_operations\": 3," +
            "            \"successful_calls\": {" +
            "                \"201411\": 3" +
            "            }," +
            "            \"total_calls\": {" +
            "                \"201411\": 3" +
            "            }," +
            "            \"created_time\": \"2014-11-13 06:07:19.836404\"," +
            "            \"resource_uri\": \"/v2/accounts/test/\"" +
            "        }" +
            "    ]," +
            "   \"page\": 1," +
            "    \"has_next\": false," +
            "    \"total_pages\": 1," +
            "    \"total_platform_calls\": {" +
            "        \"2014-11\": 3" +
            "    }," +
            "    \"successful_platform_calls\": {" +
            "        \"2014-11\": 3" +
            "    }" +
            "}";

    private final String PLATFORM_STATISTICS_USER_JSON_RESULT_EXAMPLE =
            "{" +
            "    \"username\": \"test\"," +
            "    \"first_name\": \"\"," +
            "    \"last_name\": \"\"," +
            "    \"company_name\": \"\"," +
            "    \"email\": \"\"," +
            "    \"number_of_operations\": 3," +
            "    \"successful_calls\": {" +
            "        \"201411\": 3" +
            "    }," +
            "    \"total_calls\": {" +
            "        \"201411\": 3" +
            "    }," +
            "    \"resource_uri\": \"/v2/accounts/test/\"" +
            "}";

    private final String ACCOUNT_ID_JSON_REQUEST_EXAMPLE =
            "{" +
            "    \"id\": \"test\"," +
            "    \"first_name\": \"Clark\"," +
            "    \"last_name\": \"Kent\"," +
            "    \"email\": \"test@test.com\"" +
            "}";

    private final String ACCOUNT_ID_WRONG_JSON_REQUEST_EXAMPLE =
            "{" +
            "    \"first_name\": \"Clark\"," +
            "    \"last_name\": \"Kent\"," +
            "    \"email\": \"XXXX\"" +
            "}";

    private final String ACCOUNT_ID_WRONG_RESULT =
            "{" +
            "    \"error\": {" +
            "        \"message\": \"Validation Error\"," +
            "        \"code\": \"422\"," +
            "        \"type\": \"Unprocessable Entity\"," +
            "        \"description\": {" +
            "            \"email\": [" +
            "                \"Enter a valid email address.\"" +
            "            ]" +
            "        }" +
            "    }" +
            "}";

    private static final String username = "XXXXX";
    private static final String password = "XXXXX";
    private String accountId;

    private PlatformNextCallerClient client;

    private Map<String, Object> accountIdJsonRequestExample;
    private Map<String, Object> accountIdWrongJsonRequestExample;

    public PlatformNextCallerClientTest() throws IOException, NoSuchFieldException, IllegalAccessException {

        super();

        client = new PlatformNextCallerClient(username, password);
        mockClient(client);

        try {
            this.accountIdJsonRequestExample = ParseToObject.responseToMap(ACCOUNT_ID_JSON_REQUEST_EXAMPLE);
            this.accountIdWrongJsonRequestExample = ParseToObject.responseToMap(ACCOUNT_ID_WRONG_JSON_REQUEST_EXAMPLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Object> getByPhone(String phone)
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        return client.getByPhone(phone, accountId);
    }

    @Override
    public Map<String, Object> getByNameAddress(Map<String, String> nameAddressData)
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        return client.getByNameAddress(nameAddressData, accountId);
    }

    @Override
    public Map<String, Object> getByProfileId(String profileId)
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        return client.getByProfileId(profileId, accountId);
    }

    @Override
    public Boolean updateByProfileId(String profileId, Map<String, Object> newProfile)
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        return client.updateByProfileId(profileId, newProfile, accountId);
    }

    @Test
    public void testGetByPhoneWithAccountId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        accountId = "test";
        super.testGetByPhone();
    }

    @Test
    public void testGetByPhoneWithoutAccountId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        accountId = "";
        super.testGetByPhone();
    }

    @Test(expected = HttpException.class)
    public void testByShortPhoneWithAccountId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        accountId = "test";
        super.testByShortPhone();
    }

    @Test(expected = ValidationException.class)
    public void testByNameAddressWithNotFullDataWithAccountId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        accountId = "test";
        super.testByNameAddressWithNotFullData();
    }

    @Test(expected = RateLimitException.class)
    public void testRateLimitWithAccountId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        accountId = "test";
        super.testRateLimit();
    }

    @Test
    public void testGetByNameAddressWithAccountId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        accountId = "test";
        super.testGetByNameAddress();
    }

    @Test
    public void testGetByNameAddressWithoutAccountId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        accountId = "";
        super.testGetByNameAddress();
    }

    @Test
    public void testGetByProfileIdWithAccountId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        accountId = "test";
        super.testGetByProfileId();
    }

    @Test
    public void testGetByProfileIdWithoutAccountId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        accountId = "";
        super.testGetByProfileId();
    }

    @Test
    public void testUpdateByProfileIdWithAccountId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        accountId = "test";
        super.testUpdateByProfileId();
    }

    @Test
    public void testProfileUpdateWrongRequestWithAccountId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        accountId = "test";
        super.testProfileUpdateWrongRequest();
    }

    @Test
    public void testGetAllStatistics()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        mockResponse(PLATFORM_STATISTICS_JSON_RESULT_EXAMPLE);

        Map<String, Object> response = client.getPlatformStatistics(1);

        assertNotNull(response.get("successful_platform_calls"));
        assertNotNull(response.get("total_platform_calls"));
        assertEquals(((Map<String, Object>)((List)response.get("object_list")).get(0)).get("username"), "test");
        assertEquals(((Map<String, Object>)((List)response.get("object_list")).get(0)).get("number_of_operations"), 3);
        assertEquals(((response.get("page"))), 1);
    }

    @Test
    public void testGetAllStatisticsWithoutPageParameter()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        mockResponse(PLATFORM_STATISTICS_JSON_RESULT_EXAMPLE);

        Map<String, Object> response = client.getPlatformStatistics();

        assertNotNull(response.get("successful_platform_calls"));
        assertNotNull(response.get("total_platform_calls"));
        assertEquals(((Map<String, Object>)((List)response.get("object_list")).get(0)).get("username"), "test");
        assertEquals(((Map<String, Object>)((List)response.get("object_list")).get(0)).get("number_of_operations"), 3);
        assertEquals(((response.get("page"))), 1);
    }

    @Test
    public void testGetUsersStatistics()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        String accountId = "test";

        mockResponse(PLATFORM_STATISTICS_USER_JSON_RESULT_EXAMPLE);

        Map<String, Object> response = client.getPlatformAccount(accountId);

        assertEquals(response.get("username"), "test");
        assertEquals(response.get("number_of_operations"), 3);
    }

    @Test
    public void testCreatePlatformAccount()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        mockOkResponse();

        assertTrue(client.createPlatformAccount(accountIdJsonRequestExample));
    }

    @Test
    public void testUpdatePlatformAccount()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        String accountId = "test";

        mockOkResponse();

        assertTrue(client.updatePlatformAccount(accountIdJsonRequestExample, accountId));
    }

    @Test
    public void testUpdateWrongPlatformAccount()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        String accountId = "test";
        int statusCode = 400;

        mockErrorResponse(statusCode, ACCOUNT_ID_WRONG_RESULT);

        try {
            client.updatePlatformAccount(accountIdWrongJsonRequestExample, accountId);
            fail( "method didn't throw when I expected it to" );
        } catch (HttpException e) {
            assertEquals(e.getHttpStatusCode(), statusCode);
            assertEquals(((List) e.getErrorMessage().getDescription().get("email")).get(0), "Enter a valid email address.");
        }
    }
}
