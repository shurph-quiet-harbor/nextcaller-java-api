package com.nextcaller.integration.util;

import com.nextcaller.integration.client.MakeHttpRequest;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrepareUrlUtil {

    public static final String SERVER_URL = "https://api.nextcaller.com/v%s/";
    public static final String SANDBOX_URL = "https://api.sandbox.nextcaller.com/v%s/";

    public static String prepareUrlByProfileId(String profileId, String platformUsername, boolean sandbox, String version) {
        StringBuffer url = getBaseUrl(sandbox, version);
        Map<String, String> params = new HashMap<String, String>() {{
            put("format", MakeHttpRequest.JSON_FORMAT);
        }};
        if (platformUsername != null) {
            params.put("platform_username", platformUsername);
        }
        url.append("users/").append(profileId).append("/").append(mapToFormEncodedString(params));
        return url.toString();
    }

    public static String prepareUrlByPhone(final String phone, String platformUsername, boolean sandbox, String version) {
        StringBuffer url = getBaseUrl(sandbox, version);
        Map<String, String> params = new HashMap<String, String>() {{
            put("format", MakeHttpRequest.JSON_FORMAT);
            put("phone", phone);
        }};
        if (platformUsername != null) {
            params.put("platform_username", platformUsername);
        }
        url.append("records/").append(mapToFormEncodedString(params));
        return url.toString();
    }

    public static String prepareUrlByAddressName(Map<String, String> addressNameData, String platformUsername, boolean sandbox, String version) {
        StringBuffer url = getBaseUrl(sandbox, version);
        addressNameData.put("format", MakeHttpRequest.JSON_FORMAT);
        if (platformUsername != null) {
            addressNameData.put("platform_username", platformUsername);
        }
        url.append("records/").append(mapToFormEncodedString(addressNameData));
        return url.toString();
    }

    public static String prepareUrlByFraudLevel(final String phone, String platformUsername, boolean sandbox, String version) {
        StringBuffer url = getBaseUrl(sandbox, version);
        Map<String, String> params = new HashMap<String, String>() {{
            put("format", MakeHttpRequest.JSON_FORMAT);
            put("phone", phone);
        }};
        if (platformUsername != null) {
            params.put("platform_username", platformUsername);
        }
        url.append("fraud/").append(mapToFormEncodedString(params));
        return url.toString();
    }

    public static String prepareUrlByPlatformUser(String username, boolean sandbox, String version) {
        StringBuffer url = getBaseUrl(sandbox, version);
        Map<String, String> params = new HashMap<String, String>() {{
            put("format", MakeHttpRequest.JSON_FORMAT);
        }};
        url.append("platform_users/").append(username).append("/").append(mapToFormEncodedString(params));
        return url.toString();
    }

    public static String prepareUrlByPlatformStatistics(final int page, boolean sandbox, String version) {
        StringBuffer url = getBaseUrl(sandbox, version);
        Map<String, String> params = new HashMap<String, String>() {{
            put("format", MakeHttpRequest.JSON_FORMAT);
            put("page", Integer.toString(page));
        }};
        url.append("platform_users/").append(mapToFormEncodedString(params));
        return url.toString();
    }

    private static StringBuffer getBaseUrl(boolean sandbox, String version) {
        return new StringBuffer(String.format(sandbox ? SANDBOX_URL : SERVER_URL, version));
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
        String result;
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
            acc.add(encodeURIComponent(entry.getKey()) +
                    "=" +
                    encodeURIComponent(entry.getValue()));
        }
        return "?" + join(acc, "&");
    }

}
