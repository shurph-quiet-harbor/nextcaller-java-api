package com.nextcaller.integration.client;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.ValidateException;
import com.nextcaller.integration.response.ParseToObject;
import com.nextcaller.integration.response.RestError;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class NextCallerClientTest {

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

    private NextCallerClient client;

    private Map<String, Object> requestExample;
    private Map<String, Object> jsonResultExampleExample;
    private Map<String, Object> phoneJsonResultExample;
    private Map<String, Object> phoneJsonWrongRequestExample;
    RestError phoneJsonWrongResult;
    private Map<String, Object> fraudJsonResultExample;

    public NextCallerClientTest() {
        client = mock(NextCallerClient.class);

        try {
            requestExample = ParseToObject.responseToMap(PROFILE_JSON_REQUEST_EXAMPLE);
            jsonResultExampleExample = ParseToObject.responseToMap(PROFILE_JSON_RESULT_EXAMPLE);
            phoneJsonResultExample = ParseToObject.responseToMap(PHONE_JSON_RESULT_EXAMPLE);
            phoneJsonWrongRequestExample = ParseToObject.responseToMap(PROFILE_JSON_WRONG_REQUEST_EXAMPLE);
            phoneJsonWrongResult = ParseToObject.getError(PROFILE_JSON_WRONG_RESULT);
            fraudJsonResultExample = ParseToObject.responseToMap(FRAUD_JSON_RESULT_EXAMPLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = ValidateException.class)
    public void testByShortPhone() throws HttpException, IOException, AuthenticationException, ValidateException {
        String phone = "212555838";

        when(client.getByPhone(phone)).thenThrow(new ValidateException("Invalid phone number: " + phone + ". Phone should has length 10."));

        Map<String, Object> response = client.getByPhone(phone);
    }

    @Test(expected = ValidateException.class)
    public void testByWrongPhone() throws HttpException, IOException, AuthenticationException, ValidateException {
        String phone = "XXXXXXXXXX";

        when(client.getByPhone(phone)).thenThrow(new ValidateException("Invalid phone number: " + phone + ". Phone should consists of only digits."));

        Map<String, Object> response = client.getByPhone(phone);
    }

    @Test
    public void testByPhone() throws HttpException, IOException, AuthenticationException, ValidateException {
        String phone = "2125558383";

        when(client.getByPhone(phone)).thenReturn(phoneJsonResultExample);

        Map<String, Object> response = client.getByPhone(phone);
        Map<String, Object> user = (Map<String, Object>)((List)response.get("records")).get(0);

        assertEquals(user.get("email"), "demo@nextcaller.com");
        assertEquals(user.get("first_name"), "Jerry");
        assertEquals(user.get("last_name"), "Seinfeld");
    }

    @Test
    public void testProfileGetRequest() throws HttpException, IOException, AuthenticationException, ValidateException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";

        when(client.getByProfileId(profileId)).thenReturn(jsonResultExampleExample);

        Map<String, Object> response = client.getByProfileId(profileId);

        assertEquals(response.get("email"), "demo@nextcaller.com");
        assertEquals(response.get("first_name"), "Jerry");
        assertEquals(response.get("last_name"), "Seinfeld");
    }

    @Test
    public void testProfileUpdateRequest() throws HttpException, IOException, AuthenticationException, ValidateException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";

        when(client.updateByProfileId(profileId, requestExample)).thenReturn(true);

        assertTrue(client.updateByProfileId(profileId, requestExample));
    }

    @Test
    public void testProfileUpdateWrongRequest() throws HttpException, IOException, AuthenticationException, ValidateException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";
        int statusCode = 400;

        when(client.updateByProfileId(profileId, phoneJsonWrongRequestExample)).thenThrow(new HttpException(phoneJsonWrongResult.getError(), statusCode));

        try {
            client.updateByProfileId(profileId, phoneJsonWrongRequestExample);
            fail( "method didn't throw when I expected it to" );
        } catch (HttpException e) {
            assertEquals(e.getHttpStatusCode(), statusCode);
            assertEquals(((List) e.getErrorMessage().getDescription().get("email")).get(0), "Invalid email address");
        }
    }

    @Test
    public void testFraudLevel() throws HttpException, IOException, AuthenticationException, ValidateException {
        String phone = "2125558383";

        when(client.getByPhone(phone)).thenReturn(fraudJsonResultExample);

        Map<String, Object> response = client.getByPhone(phone);

        assertEquals(response.get("spoofed"), "unknown");
    }
}
