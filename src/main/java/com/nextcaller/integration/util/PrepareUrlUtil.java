package com.nextcaller.integration.util;

public class PrepareUrlUtil {

    private static final String BASE_URL = "https://api.nextcaller.com/v2/";

    public static String prepareUrlByProfileId(String profileId, String responseFormat) {
        return BASE_URL + "users/" + profileId + "/?format=" + responseFormat;
    }

    public static String prepareUrlByPhone(String phone, String responseFormat) {
        return BASE_URL + "records/?phone=" + phone + "&format=" + responseFormat;
    }

}
