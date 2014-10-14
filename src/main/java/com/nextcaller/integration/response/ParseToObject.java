package com.nextcaller.integration.response;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nextcaller.integration.client.RestClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class ParseToObject {


    public static RestError getError(String textResponse, String contentType) throws IOException {
        RestError error = new RestError();

        if (contentType.equals(RestClient.XML_FORMAT)) {
            error = new XmlMapper().readValue(textResponse, RestError.class);
        } else if (contentType.equals(RestClient.JSON_FORMAT)) {
            error = new ObjectMapper().readValue(textResponse, RestError.class);
        }

        return error;
    }


    public static Map<String, Object> responseToMap(String textResponse, String contentType) throws IOException {
        Map<String,Object> map = new HashMap<String,Object>();

        if (textResponse != null && !textResponse.isEmpty()) {
            if (contentType.equals(RestClient.XML_FORMAT)) {
                XmlMapper mapper = new XmlMapper();

                map = mapper.readValue(textResponse, map.getClass());
            } else if (contentType.equals(RestClient.JSON_FORMAT)) {
                ObjectMapper mapper = new ObjectMapper();

                map = mapper.readValue(textResponse, new TypeReference<HashMap<String, Object>>() {
                });
            }
        }

        return map;
    }


    public static String userToString(Map<String,Object> user, String contentType) throws IOException {
        String result = null;

        if (contentType.equals(RestClient.XML_FORMAT)) {
            StringWriter userWriter = new StringWriter();
            new XmlMapper().writeValue(userWriter, user);
            result = userWriter.toString();
            userWriter.close();
        } else if (contentType.equals(RestClient.JSON_FORMAT)) {
            StringWriter userWriter = new StringWriter();
            new ObjectMapper().writeValue(userWriter, user);
            result = userWriter.toString();
            userWriter.close();
        }

        return result;
    }

}
