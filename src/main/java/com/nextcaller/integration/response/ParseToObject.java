package com.nextcaller.integration.response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class ParseToObject {

    public static RestError getError(String textResponse) throws IOException {
        RestError error = new ObjectMapper().readValue(textResponse, RestError.class);

        return error;
    }

    public static Map<String, Object> responseToMap(String textResponse) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();

        if (textResponse != null && !textResponse.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();

            map = mapper.readValue(textResponse, new TypeReference<HashMap<String, Object>>() {});
        }

        return map;
    }

    public static String userToString(Map<String, Object> user) throws IOException {
        StringWriter userWriter = new StringWriter();
        new ObjectMapper().writeValue(userWriter, user);
        String result = userWriter.toString();
        userWriter.close();

        return result;
    }

}
