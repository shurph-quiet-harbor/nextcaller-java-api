package com.nextcaller.integration.client;

import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.RateLimitException;
import com.nextcaller.integration.exceptions.ValidationException;
import com.nextcaller.integration.response.ParseToObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class AbstractClientTest {
    private final String PROFILE_JSON_REQUEST_EXAMPLE =
            "{\n" +
            "    \"first_name\": \"Clark\",\n" +
            "    \"last_name\": \"Kent\",\n" +
            "    \"shipping_address1\": {\n" +
            "        \"line1\": \"225 Kryptonite Ave.\",\n" +
            "        \"line2\": \"\",\n" +
            "        \"city\": \"Smallville\",\n" +
            "        \"state\": \"KS\",\n" +
            "        \"zip_code\": \"66002\"\n" +
            "    }\n" +
            "}" ;

    private final String PROFILE_JSON_RESULT_EXAMPLE =
            "{\n" +
            "    \"id\": \"97d949a413f4ea8b85e9586e1f2d9a\",\n" +
            "    \"first_name\": \"Jerry\",\n" +
            "    \"last_name\": \"Seinfeld\",\n" +
            "    \"name\": \"Jerry Seinfeld\",\n" +
            "    \"language\": \"English\",\n" +
            "    \"fraud_threat\": \"low\",\n" +
            "    \"spoof\": \"false\",\n" +
            "    \"phone\": [\n" +
            "        {\n" +
            "            \"number\": \"2125558383\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"carrier\": \"Verizon Wireless\",\n" +
            "    \"line_type\": \"LAN\",\n" +
            "    \"address\": [\n" +
            "        {\n" +
            "            \"city\": \"New York\",\n" +
            "            \"extended_zip\": \"\",\n" +
            "            \"country\": \"USA\",\n" +
            "            \"line2\": \"Apt 5a\",\n" +
            "            \"line1\": \"129 West 81st Street\",\n" +
            "            \"state\": \"NY\",\n" +
            "            \"zip_code\": \"10024\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"email\": \"jerry@example.org\",\n" +
            "    \"age\": \"45-54\",\n" +
            "    \"gender\": \"Male\",\n" +
            "    \"household_income\": \"50k-75k\",\n" +
            "    \"marital_status\": \"Single\",\n" +
            "    \"presence_of_children\": \"No\",\n" +
            "    \"home_owner_status\": \"Rent\",\n" +
            "    \"market_value\": \"350k-500k\",\n" +
            "    \"length_of_residence\": \"12 Years\",\n" +
            "    \"high_net_worth\": \"No\",\n" +
            "    \"occupation\": \"Entertainer\",\n" +
            "    \"education\": \"Completed College\",\n" +
            "    \"department\": \"not specified\"\n" +
            "}" ;

    private final String PHONE_JSON_RESULT_EXAMPLE =
            "{\n" +
            "    \"records\": [\n" +
            "        {\n" +
            "            \"id\": \"97d949a413f4ea8b85e9586e1f2d9a\",\n" +
            "            \"first_name\": \"Jerry\",\n" +
            "            \"last_name\": \"Seinfeld\",\n" +
            "            \"name\": \"Jerry Seinfeld\",\n" +
            "            \"language\": \"English\",\n" +
            "            \"fraud_threat\": \"low\",\n" +
            "            \"spoof\": \"false\",\n" +
            "            \"phone\": [\n" +
            "                {\n" +
            "                    \"number\": \"2125558383\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"carrier\": \"Verizon Wireless\",\n" +
            "            \"line_type\": \"LAN\",\n" +
            "            \"address\": [\n" +
            "                {\n" +
            "                    \"city\": \"New York\",\n" +
            "                    \"extended_zip\": \"\",\n" +
            "                    \"country\": \"USA\",\n" +
            "                    \"line2\": \"Apt 5a\",\n" +
            "                    \"line1\": \"129 West 81st Street\",\n" +
            "                    \"state\": \"NY\",\n" +
            "                    \"zip_code\": \"10024\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"email\": \"demo@nextcaller.com\",\n" +
            "            \"social_links\": [\n" +
            "                {\n" +
            "                    \"followers\": 1,\n" +
            "                    \"type\": \"twitter\",\n" +
            "                    \"url\": \"https://twitter.com/nextcaller\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"type\": \"facebook\",\n" +
            "                    \"url\": \"https://www.facebook.com/nextcaller\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"type\": \"linkedin\",\n" +
            "                    \"url\": \"https://www.linkedin.com/company/next-caller\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"age\": \"45-54\",\n" +
            "            \"gender\": \"Male\",\n" +
            "            \"household_income\": \"50k-75k\",\n" +
            "            \"marital_status\": \"Single\",\n" +
            "            \"presence_of_children\": \"No\",\n" +
            "            \"home_owner_status\": \"Rent\",\n" +
            "            \"market_value\": \"350k-500k\",\n" +
            "            \"length_of_residence\": \"12 Years\",\n" +
            "            \"high_net_worth\": \"No\",\n" +
            "            \"occupation\": \"Entertainer\",\n" +
            "            \"education\": \"Completed College\",\n" +
            "            \"department\": \"not specified\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    private final String PHONE_JSON_WRONG_RESULT =
            "{\n" +
            "    \"error\": {\n" +
            "        \"code\": \"555\",\n" +
            "        \"message\": \"The number you have entered is invalid. Please ensure your number contains 10 digits.\",\n" +
            "        \"type\": \"Bad Request\"\n" +
            "    }\n" +
            "}";

    private final String PROFILE_JSON_VALIDATION_ERROR_RESULT =
            "{\n" +
            "    \"error\": {\n" +
            "        \"code\": \"422\",\n" +
            "        \"description\": {\n" +
            "            \"address\": [\n" +
            "                \"zip_code or combination of city and state parameters must be provided.\"\n" +
            "            ]\n" +
            "        },\n" +
            "        \"message\": \"Validation Error\",\n" +
            "        \"type\": \"Unprocessable Entity\"\n" +
            "    }\n" +
            "}";

    private final String PROFILE_JSON_WRONG_REQUEST_EXAMPLE =
            "{\n" +
            "    \"first_name\": \"Clark\",\n" +
            "    \"last_name\": \"Kent\",\n" +
            "    \"email\": \"XXXXXXXXXXXX\",\n" +
            "    \"shipping_address1\": {\n" +
            "        \"line1\": \"225 Kryptonite Ave.\",\n" +
            "        \"line2\": \"\",\n" +
            "        \"city\": \"Smallville\",\n" +
            "        \"state\": \"KS\",\n" +
            "        \"zip_code\": \"66002\"\n" +
            "    }\n" +
            "}";

    private final String PROFILE_JSON_WRONG_RESULT =
            "{\n" +
            "    \"error\": {\n" +
            "        \"message\": \"There are validation errors\",\n" +
            "        \"code\": \"1054\",\n" +
            "        \"type\": \"Validation\",\n" +
            "        \"description\": {\n" +
            "            \"email\": [\n" +
            "                \"Invalid email address\"\n" +
            "            ]\n" +
            "        }\n" +
            "    }\n" +
            "}";

    private final String FRAUD_JSON_RESULT_EXAMPLE =
            "{\n" +
            "    \"spoofed\": \"unknown\",\n" +
            "    \"fraud_risk\": \"medium\"\n" +
            "}";

    private final String TOO_MANY_REQUESTS_EXAMPLE =
            "{\n" +
            "    \"error\": {\n" +
            "        \"message\": \"API calls per second limit is exceeded.\",\n" +
            "        \"code\": \"1061\",\n" +
            "        \"type\": \"Too Many Requests\"\n" +
            "    }\n" +
            "}";

    private HttpsURLConnection connection;

    private Map<String, Object> requestExample;
    private Map<String, Object> phoneJsonWrongRequestExample;

    public AbstractClientTest() {
        try {
            requestExample = ParseToObject.responseToMap(PROFILE_JSON_REQUEST_EXAMPLE);
            phoneJsonWrongRequestExample = ParseToObject.responseToMap(PROFILE_JSON_WRONG_REQUEST_EXAMPLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setMakeHttpRequest(AbstractClient client, MakeHttpRequest makeHttpRequest)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = AbstractClient.class.getDeclaredField("makeHttpRequest");
        Field modifiersField = Field.class.getDeclaredField("modifiers");

        field.setAccessible(true);
        modifiersField.setAccessible(true);

        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(client, makeHttpRequest);
    }

    protected void mockClient(AbstractClient client)
            throws IOException, NoSuchFieldException, IllegalAccessException {
        connection = mock(HttpsURLConnection.class);
        MakeHttpRequest makeHttpRequest = spy(client.makeHttpRequest);
        doReturn(connection).when(makeHttpRequest).getConnection((URL) any());
        setMakeHttpRequest(client, makeHttpRequest);
    }

    private void mockStream(Object methodCall, String input) {
        if (input != null) {
            InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
            when(methodCall).thenReturn(stream);
        }
    }

    private class NullOutputStream extends OutputStream {
        @Override
        public void write(byte[] bytes) throws IOException {
        }

        @Override
        public void write(int value) throws IOException {
        }
    }

    protected void mockResponse(int responseCode, String response, String error) throws IOException {
        when(connection.getResponseCode()).thenReturn(responseCode);
        when(connection.getOutputStream()).thenReturn(new NullOutputStream());
        mockStream(connection.getInputStream(), response);
        mockStream(connection.getErrorStream(), error);
    }

    protected void mockResponse(String response) throws IOException {
        mockResponse(HttpsURLConnection.HTTP_OK, response, null);
    }

    protected void mockErrorResponse(int responseCode, String error) throws IOException {
        mockResponse(responseCode, null, error);
    }

    protected void mockOkResponse() throws IOException {
        mockResponse(HttpsURLConnection.HTTP_OK, null, null);
    }

    public Map<String, Object> getByPhone(String phone)
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        return null;
    }

    public Map<String, Object> getByNameAddress(Map<String, String> nameAddressData)
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        return null;
    }

    public Map<String, Object> getByProfileId(String profileId)
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        return null;
    }

    public Boolean updateByProfileId(String profileId, Map<String, Object> newProfile)
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        return null;
    }

    public void testGetByPhone()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        String phone = "2125558383";

        mockResponse(PHONE_JSON_RESULT_EXAMPLE);

        Map<String, Object> response = getByPhone(phone);
        Map<String, Object> user = (Map<String, Object>)((List)response.get("records")).get(0);

        assertEquals(user.get("email"), "demo@nextcaller.com");
        assertEquals(user.get("first_name"), "Jerry");
        assertEquals(user.get("last_name"), "Seinfeld");
    }

    public void testByShortPhone()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        String phone = "212555838";

        mockErrorResponse(400, PHONE_JSON_WRONG_RESULT);

        getByPhone(phone);
    }

    public void testByNameAddressWithNotFullData()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        final Map<String, String> nameAddressData = new HashMap<String, String>(){{
            put("first_name", "Jerry");
            put("last_name", "Seinfeld");
            put("address", "129 West 81st Street");
            put("city", "New York");
        }};

        mockErrorResponse(422, PROFILE_JSON_VALIDATION_ERROR_RESULT);

        getByNameAddress(nameAddressData);
    }

    public void testGetByNameAddress()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        final Map<String, String> nameAddressData = new HashMap<String, String>(){{
            put("first_name", "Jerry");
            put("last_name", "Seinfeld");
            put("address", "129 West 81st Street");
            put("city", "New York");
            put("zip_code", "10024");
        }};

        mockResponse(PHONE_JSON_RESULT_EXAMPLE);

        Map<String, Object> response = getByNameAddress(nameAddressData);
        Map<String, Object> user = (Map<String, Object>)((List)response.get("records")).get(0);

        assertEquals(user.get("email"), "demo@nextcaller.com");
        assertEquals(user.get("first_name"), "Jerry");
        assertEquals(user.get("last_name"), "Seinfeld");
    }

    public void testGetByProfileId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";

        mockResponse(PROFILE_JSON_RESULT_EXAMPLE);

        Map<String, Object> response = getByProfileId(profileId);

        assertEquals(response.get("email"), "jerry@example.org");
        assertEquals(response.get("first_name"), "Jerry");
        assertEquals(response.get("last_name"), "Seinfeld");
    }

    public void testUpdateByProfileId()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";

        mockOkResponse();

        assertTrue(updateByProfileId(profileId, requestExample));
    }

    public void testProfileUpdateWrongRequest()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";
        int statusCode = 400;

        mockErrorResponse(statusCode, PROFILE_JSON_WRONG_RESULT);

        try {
            updateByProfileId(profileId, phoneJsonWrongRequestExample);
            fail( "method didn't throw when I expected it to" );
        } catch (HttpException e) {
            assertEquals(e.getHttpStatusCode(), statusCode);
            assertEquals(((List) e.getErrorMessage().getDescription().get("email")).get(0), "Invalid email address");
        }
    }

    public void testFraudLevel()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        String phone = "2125558383";

        mockResponse(FRAUD_JSON_RESULT_EXAMPLE);

        Map<String, Object> response = getByPhone(phone);

        assertEquals(response.get("spoofed"), "unknown");
    }

    public void testRateLimit()
            throws HttpException, IOException, AuthenticationException, ValidationException, RateLimitException {
        String phone = "2125558383";

        mockErrorResponse(MakeHttpRequest.HTTP_TOO_MANY_REQUESTS, TOO_MANY_REQUESTS_EXAMPLE);

        getByPhone(phone);
    }
}
