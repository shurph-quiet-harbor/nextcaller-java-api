package com.nextcaller.integration.util;

import com.nextcaller.integration.client.MakeHttpRequest;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static String prepareUrlByAddressName(Map<String, String> addressNameData, String platformUsername, boolean sandbox) {
        StringBuffer url = getBaseUrl(sandbox);
        if (platformUsername != null) {
            addressNameData.put("platform_username", platformUsername);
        }
        url.append("records/").append(mapToFormEncodedString(addressNameData));
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

    public static String prepareUrlByPlatformStatistics(int page, boolean sandbox) {
        StringBuffer url = getBaseUrl(sandbox);
        url.append("platform_users/?page=").
                append(page).
                append("&");
        appendFormat(url);

        return url.toString();
    }

    private static StringBuffer getBaseUrl(boolean sandbox) {
        return new StringBuffer(sandbox ? SANDBOX_URL : SERVER_URL);
    }

    private static void appendFormat(StringBuffer url) {
        url.append("format=").append(MakeHttpRequest.JSON_FORMAT);
    }

    public static String join(List<String> list, String separator) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (final  String str: list) {
            if (first) first = false;
            else sb.append(separator);
            sb.append(str);
        }
        return sb.toString();
    }

    private static String encodeURIComponent(String component)   {
        String result = null;
        try {
            result = URLEncoder.encode(component, "UTF-8")
                    .replaceAll("%28", "(")
                    .replaceAll("%29", ")")
                    .replaceAll("\\+", "%20")
                    .replaceAll("%27", "'")
                    .replaceAll("%21", "!")
                    .replaceAll("%7E", "~");
        }
        catch (java.io.UnsupportedEncodingException e) {
            result = component;
        }
        return result;
    }

    private static String mapToFormEncodedString(Map<String, String> data) {
        final List<String> acc = new ArrayList<String>();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            acc.add(encodeURIComponent(entry.getKey() + "=" + entry.getValue()));
        }
        return "?" + join(acc, "&");
    }

}
