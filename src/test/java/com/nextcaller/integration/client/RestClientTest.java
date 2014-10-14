package com.nextcaller.integration.client;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.ValidateException;
import com.nextcaller.integration.response.ParseToObject;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RestClientTest {

    private RestClient client;

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

    private final String PROFILE_XML_RESULT_EXAMPLE = "<object>" +
            "    <id>97d949a413f4ea8b85e9586e1f2d9a</id>" +
            "    <first_name>Jerry</first_name>" +
            "    <last_name>Seinfeld</last_name>" +
            "    <name>Jerry Seinfeld</name>" +
            "    <language>English</language>" +
            "    <fraud_threat>low</fraud_threat>" +
            "    <spoof>false</spoof>" +
            "    <phone>" +
            "        <object>" +
            "            <number>2125558383</number>" +
            "        </object>" +
            "    </phone>" +
            "    <carrier>Verizon Wireless</carrier>" +
            "    <line_type>LAN</line_type>" +
            "    <address>" +
            "        <object>" +
            "            <line1>129 West 81st Street</line1>" +
            "            <line2>Apt 5a</line2>" +
            "            <city>New York</city>" +
            "            <state>NY</state>" +
            "            <zip_code>10024</zip_code>" +
            "            <extended_zip/>" +
            "            <country>USA</country>" +
            "        </object>" +
            "    </address>" +
            "    <email>demo@nextcaller.com</email>" +
            "    <age>45-54</age>" +
            "    <gender>Male</gender>" +
            "    <household_income>50k-75k</household_income>" +
            "    <marital_status>Single</marital_status>" +
            "    <presence_of_children>No</presence_of_children>" +
            "    <home_owner_status>Rent</home_owner_status>" +
            "    <market_value>350k-500k</market_value>" +
            "    <length_of_residence>12 Years</length_of_residence>" +
            "    <high_net_worth>No</high_net_worth>" +
            "    <occupation>Entertainer</occupation>" +
            "    <education>Completed College</education>" +
            "    <department>not specified</department>" +
            "</object>";

    private final String PHONE_JSON_RESULT_EXAMPLE = "{" +
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

    private final String PHONE_XML_RESULT_EXAMPLE = "<response>" +
            "    <records>" +
            "        <object>" +
            "            <id>97d949a413f4ea8b85e9586e1f2d9a</id>" +
            "            <first_name>Jerry</first_name>" +
            "            <last_name>Seinfeld</last_name>" +
            "            <name>Jerry Seinfeld</name>" +
            "            <language>English</language>" +
            "            <fraud_threat>low</fraud_threat>" +
            "            <spoof>false</spoof>" +
            "            <phone>" +
            "                <object>" +
            "                    <number>2125558383</number>" +
            "                </object>" +
            "            </phone>" +
            "            <carrier>Verizon Wireless</carrier>" +
            "            <line_type>LAN</line_type>" +
            "            <address>" +
            "                <object>" +
            "                    <line1>129 West 81st Street</line1>" +
            "                    <line2>Apt 5a</line2>" +
            "                    <city>New York</city>" +
            "                    <state>NY</state>" +
            "                    <zip_code>10024</zip_code>" +
            "                    <extended_zip/>" +
            "                    <country>USA</country>" +
            "                </object>" +
            "            </address>" +
            "            <email>demo@nextcaller.com</email>" +
            "            <age>45-54</age>" +
            "            <gender>Male</gender>" +
            "            <household_income>50k-75k</household_income>" +
            "            <marital_status>Single</marital_status>" +
            "            <presence_of_children>No</presence_of_children>" +
            "            <home_owner_status>Rent</home_owner_status>" +
            "            <market_value>350k-500k</market_value>" +
            "            <length_of_residence>12 Years</length_of_residence>" +
            "            <high_net_worth>No</high_net_worth>" +
            "            <occupation>Entertainer</occupation>" +
            "            <education>Completed College</education>" +
            "            <department>not specified</department>" +
            "        </object>" +
            "    </records>" +
            "</response>";

    private Map<String, Object> requestExample;
    private Map<String, Object> jsonResultExampleExample;
    private Map<String, Object> xmlResultExample;
    private Map<String, Object> phoneJsonResultExample;
    private Map<String, Object> phoneXmlResultExample;

    public RestClientTest() {
        client = mock(RestClient.class);

        try {
            requestExample = ParseToObject.responseToMap(PROFILE_JSON_REQUEST_EXAMPLE, RestClient.JSON_FORMAT);
            jsonResultExampleExample = ParseToObject.responseToMap(PROFILE_JSON_RESULT_EXAMPLE, RestClient.JSON_FORMAT);
            xmlResultExample = ParseToObject.responseToMap(PROFILE_XML_RESULT_EXAMPLE, RestClient.XML_FORMAT);
            phoneJsonResultExample = ParseToObject.responseToMap(PHONE_JSON_RESULT_EXAMPLE, RestClient.JSON_FORMAT);
            phoneXmlResultExample = ParseToObject.responseToMap(PHONE_XML_RESULT_EXAMPLE, RestClient.XML_FORMAT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProfileGetJsonRequest() throws HttpException, IOException, AuthenticationException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";

        when(client.getByProfileId(profileId, RestClient.JSON_FORMAT)).thenReturn(jsonResultExampleExample);

        Map<String, Object> response = client.getByProfileId(profileId, RestClient.JSON_FORMAT);

        assertEquals(response.get("email"), "demo@nextcaller.com");
        assertEquals(response.get("first_name"), "Jerry");
        assertEquals(response.get("last_name"), "Seinfeld");
    }

    @Test
    public void testProfileGetXmlRequest() throws HttpException, IOException, AuthenticationException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";

        when(client.getByProfileId(profileId, RestClient.XML_FORMAT)).thenReturn(xmlResultExample);

        Map<String, Object> response = client.getByProfileId(profileId, RestClient.XML_FORMAT);

        assertEquals(response.get("email"), "demo@nextcaller.com");
        assertEquals(response.get("first_name"), "Jerry");
        assertEquals(response.get("last_name"), "Seinfeld");
    }

    @Test
    public void testProfileUpdateJsonRequest() throws HttpException, IOException, AuthenticationException {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";

        when(client.updateByProfileId(profileId, requestExample)).thenReturn(true);

        assertTrue(client.updateByProfileId(profileId, requestExample));
    }

    @Test
    public void testByPhoneJsonRequest() throws HttpException, IOException, AuthenticationException, ValidateException {
        String phone = "2125558383";

        when(client.getByPhone(phone, RestClient.JSON_FORMAT)).thenReturn(phoneJsonResultExample);

        Map<String, Object> response = client.getByPhone(phone, RestClient.JSON_FORMAT);
        Map<String, Object> user = (Map<String, Object>)((List)response.get("records")).get(0);

        assertEquals(user.get("email"), "demo@nextcaller.com");
        assertEquals(user.get("first_name"), "Jerry");
        assertEquals(user.get("last_name"), "Seinfeld");
    }

    @Test
    public void testByPhoneXmlRequest() throws HttpException, IOException, AuthenticationException, ValidateException {
        String phone = "2125558383";

        when(client.getByPhone(phone, RestClient.XML_FORMAT)).thenReturn(phoneXmlResultExample);

        Map<String, Object> response = client.getByPhone(phone, RestClient.XML_FORMAT);
        Map<String, Object> user = (Map<String, Object>)((Map<String, Object>)response.get("records")).get("object");

        assertEquals(user.get("email"), "demo@nextcaller.com");
        assertEquals(user.get("first_name"), "Jerry");
        assertEquals(user.get("last_name"), "Seinfeld");
    }
}
