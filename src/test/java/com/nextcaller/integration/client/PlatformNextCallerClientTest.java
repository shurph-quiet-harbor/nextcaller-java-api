package com.nextcaller.integration.client;

import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.ValidateException;
import com.nextcaller.integration.response.ParseToObject;
import com.nextcaller.integration.response.RestError;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlatformNextCallerClientTest {

    private final String PROFILE_JSON_REQUEST_EXAMPLE = "{" +
            "    \"first_name\": \"Clark\"," +
            "    \"last_name\": \"Kent\"," +
            "    \"shipping_address1\": {" +
            "        \"line1\": \"225 Kryptonite Ave.\"," +
            "        \"line2\": \"\"," +
            "        \"city\": \"Smallville\"," +
            "        \"state\": \"KS\"," +
            "        \"zip_code\": \"66002\"" +
            "    }" +
            "}" ;

    private final String PROFILE_JSON_RESULT_EXAMPLE = "{" +
            "    \"id\": \"97d949a413f4ea8b85e9586e1f2d9a\"," +
            "    \"first_name\": \"Jerry\"," +
            "    \"last_name\": \"Seinfeld\"," +
            "    \"name\": \"Jerry Seinfeld\"," +
            "    \"language\": \"English\"," +
            "    \"fraud_threat\": \"low\"," +
            "    \"spoof\": \"false\"," +
            "    \"phone\": [" +
            "        {" +
            "            \"number\": \"2125558383\"" +
            "        }" +
            "    ]," +
            "    \"carrier\": \"Verizon Wireless\"," +
            "    \"line_type\": \"LAN\"," +
            "    \"address\": [" +
            "        {" +
            "            \"city\": \"New York\"," +
            "            \"extended_zip\": \"\"," +
            "            \"country\": \"USA\"," +
            "            \"line2\": \"Apt 5a\"," +
            "            \"line1\": \"129 West 81st Street\"," +
            "            \"state\": \"NY\"," +
            "            \"zip_code\": \"10024\"" +
            "        }" +
            "    ]," +
            "    \"email\": \"demo@nextcaller.com\"," +
            "    \"age\": \"45-54\"," +
            "    \"gender\": \"Male\"," +
            "    \"household_income\": \"50k-75k\"," +
            "    \"marital_status\": \"Single\"," +
            "    \"presence_of_children\": \"No\"," +
            "    \"home_owner_status\": \"Rent\"," +
            "    \"market_value\": \"350k-500k\"," +
            "    \"length_of_residence\": \"12 Years\"," +
            "    \"high_net_worth\": \"No\"," +
            "    \"occupation\": \"Entertainer\"," +
            "    \"education\": \"Completed College\"," +
            "    \"department\": \"not specified\"" +
            "}" ;

    private final String PHONE_JSON_RESULT_EXAMPLE =
            "{" +
            "    \"records\": [" +
            "        {" +
            "            \"id\": \"97d949a413f4ea8b85e9586e1f2d9a\"," +
            "            \"first_name\": \"Jerry\"," +
            "            \"last_name\": \"Seinfeld\"," +
            "            \"name\": \"Jerry Seinfeld\"," +
            "            \"language\": \"English\"," +
            "            \"fraud_threat\": \"low\"," +
            "            \"spoof\": \"false\"," +
            "            \"phone\": [" +
            "                {" +
            "                    \"number\": \"2125558383\"" +
            "                }" +
            "            ]," +
            "            \"carrier\": \"Verizon Wireless\"," +
            "            \"line_type\": \"LAN\"," +
            "            \"address\": [" +
            "                {" +
            "                    \"city\": \"New York\"," +
            "                    \"extended_zip\": \"\"," +
            "                    \"country\": \"USA\"," +
            "                    \"line2\": \"Apt 5a\"," +
            "                    \"line1\": \"129 West 81st Street\"," +
            "                    \"state\": \"NY\"," +
            "                    \"zip_code\": \"10024\"" +
            "                }" +
            "            ]," +
            "            \"email\": \"demo@nextcaller.com\"," +
            "            \"social_links\": [" +
            "                {" +
            "                    \"followers\": 1," +
            "                    \"type\": \"twitter\"," +
            "                    \"url\": \"https://twitter.com/nextcaller\"" +
            "                }," +
            "                {" +
            "                    \"type\": \"facebook\"," +
            "                    \"url\": \"https://www.facebook.com/nextcaller\"" +
            "                }," +
            "                {" +
            "                    \"type\": \"linkedin\"," +
            "                    \"url\": \"https://www.linkedin.com/company/next-caller\"" +
            "                }" +
            "            ]," +
            "            \"age\": \"45-54\"," +
            "            \"gender\": \"Male\"," +
            "            \"household_income\": \"50k-75k\"," +
            "            \"marital_status\": \"Single\"," +
            "            \"presence_of_children\": \"No\"," +
            "            \"home_owner_status\": \"Rent\"," +
            "            \"market_value\": \"350k-500k\"," +
            "            \"length_of_residence\": \"12 Years\"," +
            "            \"high_net_worth\": \"No\"," +
            "            \"occupation\": \"Entertainer\"," +
            "            \"education\": \"Completed College\"," +
            "            \"department\": \"not specified\"" +
            "        }" +
            "    ]" +
            "}";

    private final String PROFILE_JSON_WRONG_REQUEST_EXAMPLE =
            "{" +
            "    \"first_name\": \"Clark\"," +
            "    \"last_name\": \"Kent\"," +
            "    \"email\": \"XXXXXXXXXXXX\"," +
            "    \"shipping_address1\": {" +
            "        \"line1\": \"225 Kryptonite Ave.\"," +
            "        \"line2\": \"\"," +
            "        \"city\": \"Smallville\"," +
            "        \"state\": \"KS\"," +
            "        \"zip_code\": \"66002\"" +
            "    }" +
            "}";

    private final String PROFILE_JSON_WRONG_RESULT =
            "{" +
            "    \"error\": {" +
            "        \"message\": \"There are validation errors\"," +
            "        \"code\": \"1054\"," +
            "        \"type\": \"Validation\"," +
            "        \"description\": {" +
            "            \"email\": [" +
            "                \"Invalid email address\"" +
            "            ]" +
            "        }" +
            "    }" +
            "}";

    private final String FRAUD_JSON_RESULT_EXAMPLE =
            "{" +
            "    \"spoofed\": \"unknown\"," +
            "    \"fraud_risk\": \"medium\"" +
            "}";

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

    private PlatformNextCallerClient client;

    private Map<String, Object> requestExample;
    private Map<String, Object> jsonResultExampleExample;
    private Map<String, Object> phoneJsonResultExample;
    private Map<String, Object> phoneJsonWrongRequestExample;
    private RestError phoneJsonWrongResult;
    private Map<String, Object> fraudJsonResultExample;
    private Map<String, Object> platformStatisticsJsonResultExample;
    private Map<String, Object> platformStatisticsUserJsonResultExample;
    private Map<String, Object> accountIdJsonRequestExample;
    private Map<String, Object> accountIdWrongJsonRequestExample;
    private RestError accountIdWrongResult;

    public PlatformNextCallerClientTest() {
        client = mock(PlatformNextCallerClient.class);

        try {
            this.requestExample = ParseToObject.responseToMap(PROFILE_JSON_REQUEST_EXAMPLE);
            this.jsonResultExampleExample = ParseToObject.responseToMap(PROFILE_JSON_RESULT_EXAMPLE);
            this.phoneJsonResultExample = ParseToObject.responseToMap(PHONE_JSON_RESULT_EXAMPLE);
            this.phoneJsonWrongRequestExample = ParseToObject.responseToMap(PROFILE_JSON_WRONG_REQUEST_EXAMPLE);
            this.phoneJsonWrongResult = ParseToObject.getError(PROFILE_JSON_WRONG_RESULT);
            this.fraudJsonResultExample = ParseToObject.responseToMap(FRAUD_JSON_RESULT_EXAMPLE);
            this.platformStatisticsJsonResultExample = ParseToObject.responseToMap(PLATFORM_STATISTICS_JSON_RESULT_EXAMPLE);
            this.platformStatisticsUserJsonResultExample = ParseToObject.responseToMap(PLATFORM_STATISTICS_USER_JSON_RESULT_EXAMPLE);
            this.accountIdJsonRequestExample = ParseToObject.responseToMap(ACCOUNT_ID_JSON_REQUEST_EXAMPLE);
            this.accountIdWrongJsonRequestExample = ParseToObject.responseToMap(ACCOUNT_ID_WRONG_JSON_REQUEST_EXAMPLE);
            this.accountIdWrongResult = ParseToObject.getError(ACCOUNT_ID_WRONG_RESULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = ValidateException.class)
    public void testByShortPhone() throws HttpException, IOException, AuthenticationException, ValidateException {
        String phone = "212555838";
        String accountId = "user";

        when(client.getByPhone(phone, accountId)).thenThrow(new ValidateException("Invalid phone number: " + phone + ". Phone should has length 10."));

        Map<String, Object> response = client.getByPhone(phone, accountId);
    }

    @Test(expected = ValidateException.class)
    public void testByWrongPhone() throws HttpException, IOException, AuthenticationException, ValidateException {
        String phone = "XXXXXXXXXX";
        String accountId = "test";

        when(client.getByPhone(phone, accountId)).thenThrow(new ValidateException("Invalid phone number: " + phone + ". Phone should consists of only digits."));

        Map<String, Object> response = client.getByPhone(phone, accountId);
    }

    @Test(expected = ValidateException.class)
    public void testGetByPhoneWithoutAccountId() throws HttpException, IOException, AuthenticationException, ValidateException {
        String phone = "2125558383";
        String accountId = "";

        when(client.getByPhone(phone, accountId)).thenThrow(new ValidateException("Invalid Platform Account ID. Platform Account ID cannot be blank."));

        Map<String, Object> response = client.getByPhone(phone, accountId);
    }

    @Test
    public void testGetByPhoneWithAccountId() throws HttpException, IOException, AuthenticationException, ValidateException {
        String phone = "2125558383";
        String accountId = "test";

        when(client.getByPhone(phone, accountId)).thenReturn(phoneJsonResultExample);

        Map<String, Object> response = client.getByPhone(phone, accountId);
        Map<String, Object> user = (Map<String, Object>)((List)response.get("records")).get(0);

        assertEquals(user.get("email"), "demo@nextcaller.com");
        assertEquals(user.get("first_name"), "Jerry");
        assertEquals(user.get("last_name"), "Seinfeld");
    }

    @Test(expected = ValidateException.class)
    public void testByAddressNameWithNotFullDataAddressName() throws HttpException, IOException, AuthenticationException, ValidateException {
        final String accountId = "test";
        final Map<String, String> addressNameData = new HashMap<String, String>(){{
            put("first_name", "Jerry");
            put("last_name", "Seinfeld");
            put("address", "129 West 81st Street");
            put("city", "New York");
        }};

        when(client.getByAddressName(addressNameData, accountId)).thenThrow(new ValidateException("Either pair of city and state fields or zip_code field should be supplied"));

        Map<String, Object> response = client.getByAddressName(addressNameData, accountId);
    }

    @Test(expected = ValidateException.class)
    public void testByAddressNameWithWrongZipCode() throws HttpException, IOException, AuthenticationException, ValidateException {
        final String accountId = "test";
        final String zipCode = "1002";
        final Map<String, String> addressNameData = new HashMap<String, String>(){{
            put("first_name", "Jerry");
            put("last_name", "Seinfeld");
            put("address", "129 West 81st Street");
            put("city", "New York");
            put("zip_code", zipCode);
        }};

        when(client.getByAddressName(addressNameData, accountId)).thenThrow(new ValidateException(String.format("Invalid zip code: %s", zipCode)));

        Map<String, Object> response = client.getByAddressName(addressNameData, accountId);
    }

    @Test(expected = ValidateException.class)
    public void testByAddressNameWithoutAccountId() throws HttpException, IOException, AuthenticationException, ValidateException {
        final Map<String, String> addressNameData = new HashMap<String, String>(){{
            put("first_name", "Jerry");
            put("last_name", "Seinfeld");
            put("address", "129 West 81st Street");
            put("city", "New York");
            put("zip_code", "10024");
        }};
        String accountId = "";

        when(client.getByAddressName(addressNameData, accountId)).thenThrow(new ValidateException("Invalid Platform Account ID. Platform Account ID cannot be blank."));

        Map<String, Object> response = client.getByAddressName(addressNameData, accountId);
    }

    @Test
    public void testByAddressData() throws HttpException, IOException, AuthenticationException, ValidateException {
        final String accountId = "test";
        final Map<String, String> addressNameData = new HashMap<String, String>(){{
            put("first_name", "Jerry");
            put("last_name", "Seinfeld");
            put("address", "129 West 81st Street");
            put("city", "New York");
            put("zip_code", "10024");
        }};

        when(client.getByAddressName(addressNameData, accountId)).thenReturn(phoneJsonResultExample);

        Map<String, Object> response = client.getByAddressName(addressNameData, accountId);
        Map<String, Object> user = (Map<String, Object>)((List)response.get("records")).get(0);

        assertEquals(user.get("email"), "demo@nextcaller.com");
        assertEquals(user.get("first_name"), "Jerry");
        assertEquals(user.get("last_name"), "Seinfeld");
    }

    @Test(expected = ValidateException.class)
    public void testByWrongUsernameProfileGetRequest() throws HttpException, IOException, AuthenticationException, ValidateException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";
        String accountId = "";

        when(client.getByProfileId(profileId, accountId)).thenThrow(new ValidateException("Invalid Platform Account ID. Platform Account ID cannot be blank."));

        Map<String, Object> response = client.getByProfileId(profileId, accountId);
    }

    @Test
    public void testProfileGetRequest() throws HttpException, IOException, AuthenticationException, ValidateException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";
        String accountId = "test";

        when(client.getByProfileId(profileId, accountId)).thenReturn(jsonResultExampleExample);

        Map<String, Object> response = client.getByProfileId(profileId, accountId);

        assertEquals(response.get("email"), "demo@nextcaller.com");
        assertEquals(response.get("first_name"), "Jerry");
        assertEquals(response.get("last_name"), "Seinfeld");
    }

    @Test
    public void testProfileUpdateRequest() throws HttpException, IOException, AuthenticationException, ValidateException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";
        String accountId = "test";

        when(client.updateByProfileId(profileId, requestExample, accountId)).thenReturn(true);

        assertTrue(client.updateByProfileId(profileId, requestExample, accountId));
    }

    @Test
    public void testProfileUpdateWrongRequest() throws HttpException, IOException, AuthenticationException, ValidateException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";
        String accountId = "test";
        int statusCode = 400;

        when(client.updateByProfileId(profileId, phoneJsonWrongRequestExample, accountId)).thenThrow(new HttpException(phoneJsonWrongResult.getError(), statusCode));

        try {
            client.updateByProfileId(profileId, phoneJsonWrongRequestExample, accountId);
            fail( "method didn't throw when I expected it to" );
        } catch (HttpException e) {
            assertEquals(e.getHttpStatusCode(), statusCode);
            assertEquals(((List) e.getErrorMessage().getDescription().get("email")).get(0), "Invalid email address");
        }
    }

    @Test
    public void testFraudLevel() throws HttpException, IOException, AuthenticationException, ValidateException {
        String phone = "2125558383";
        String accountId = "test";

        when(client.getByPhone(phone, accountId)).thenReturn(fraudJsonResultExample);

        Map<String, Object> response = client.getByPhone(phone, accountId);

        assertEquals(response.get("spoofed"), "unknown");
    }

    @Test
    public void testGetAllStatistics() throws HttpException, IOException, AuthenticationException, ValidateException {
        when(client.getPlatformStatistics(1)).thenReturn(platformStatisticsJsonResultExample);

        Map<String, Object> response = client.getPlatformStatistics(1);

        assertNotNull(response.get("successful_platform_calls"));
        assertNotNull(response.get("total_platform_calls"));
        assertEquals(((Map<String, Object>)((List)response.get("object_list")).get(0)).get("username"), "test");
        assertEquals(((Map<String, Object>)((List)response.get("object_list")).get(0)).get("number_of_operations"), 3);
        assertEquals(((response.get("page"))), 1);
    }

    @Test
    public void testGetAllStatisticsWithoutPageParameter() throws HttpException, IOException, AuthenticationException, ValidateException {
        when(client.getPlatformStatistics()).thenReturn(platformStatisticsJsonResultExample);

        Map<String, Object> response = client.getPlatformStatistics();

        assertNotNull(response.get("successful_platform_calls"));
        assertNotNull(response.get("total_platform_calls"));
        assertEquals(((Map<String, Object>)((List)response.get("object_list")).get(0)).get("username"), "test");
        assertEquals(((Map<String, Object>)((List)response.get("object_list")).get(0)).get("number_of_operations"), 3);
        assertEquals(((response.get("page"))), 1);
    }

    @Test
    public void testGetUsersStatistics() throws HttpException, IOException, AuthenticationException, ValidateException {
        String accountId = "test";

        when(client.getPlatformAccount(accountId)).thenReturn(platformStatisticsUserJsonResultExample);

        Map<String, Object> response = client.getPlatformAccount(accountId);

        assertEquals(response.get("username"), "test");
        assertEquals(response.get("number_of_operations"), 3);
    }

    @Test
    public void testUpdatePlatformAccount() throws HttpException, IOException, AuthenticationException, ValidateException {
        String accountId = "test";

        when(client.updatePlatformAccount(accountId, accountIdJsonRequestExample)).thenReturn(true);

        assertTrue(client.updatePlatformAccount(accountId, accountIdJsonRequestExample));
    }

    @Test
    public void testUpdateWrongPlatformAccount() throws HttpException, IOException, AuthenticationException, ValidateException {
        String accountId = "test";
        int statusCode = 400;

        when(client.updatePlatformAccount(accountId, accountIdWrongJsonRequestExample)).thenThrow(new HttpException(accountIdWrongResult.getError(), statusCode));

        try {
            client.updatePlatformAccount(accountId, accountIdWrongJsonRequestExample);
            fail( "method didn't throw when I expected it to" );
        } catch (HttpException e) {
            assertEquals(e.getHttpStatusCode(), statusCode);
            assertEquals(((List) e.getErrorMessage().getDescription().get("email")).get(0), "Enter a valid email address.");
        }
    }
}
