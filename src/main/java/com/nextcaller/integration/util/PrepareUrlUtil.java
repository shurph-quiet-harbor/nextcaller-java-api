package com.nextcaller.integration.util;

import com.nextcaller.integration.client.MakeHttpRequest;

public class PrepareUrlUtil {

    public static final String SERVER_URL = "https://api.nextcaller.com/v2/";
    public static final String SANDBOX_URL = "https://api.sandbox.nextcaller.com/v2/";

    public static String prepareUrlByProfileId(String profileId, String platformUsername, boolean sandbox) {
        StringBuffer url = getBaseUrl(sandbox);
        url.append("users/").append(profileId).append("/");
        appendFormat(url);

        if (platformUsername != null) {
            url.append("&platform_username=").append(platformUsername);
        }

        return url.toString();
    }

    public static String prepareUrlByPhone(String phone, String platformUsername, boolean sandbox) {
        StringBuffer url = getBaseUrl(sandbox);
        url.append("records/?phone=").append(phone).append("&");
        appendFormat(url);

        if (platformUsername != null) {
            url.append("&platform_username=").append(platformUsername);
        }

        return url.toString();
    }

    public static String prepareUrlByFraudLevel(String phone, String platformUsername, boolean sandbox) {
        StringBuffer url = getBaseUrl(sandbox);
        url.append("fraud/?phone=").append(phone).append("&");
        appendFormat(url);

        if (platformUsername != null) {
            url.append("&platform_username=").append(platformUsername);
        }

        return url.toString();
    }

    public static String prepareUrlByPlatformUser(String username, boolean sandbox) {
        StringBuffer url = getBaseUrl(sandbox);
        url.append("platform_users/")
                .append(username)
                .append("/?");
        appendFormat(url);

        return url.toString();
    }

    public static String prepareUrlByPlatformStatistics(boolean sandbox) {
        StringBuffer url = getBaseUrl(sandbox);
        url.append("platform_users/?");
        appendFormat(url);

        return url.toString();
    }

    private static StringBuffer getBaseUrl(boolean sandbox) {
        return new StringBuffer(sandbox ? SANDBOX_URL : SERVER_URL);
    }

    private static void appendFormat(StringBuffer url) {
        url.append("format=").append(MakeHttpRequest.JSON_FORMAT);
    }

}
