package com.nextcaller.integration.client;

import com.nextcaller.integration.auth.BasicAuth;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.ValidateException;
import com.nextcaller.integration.response.ParseToObject;
import com.nextcaller.integration.util.PrepareUrlUtil;
import com.nextcaller.integration.util.ValidateUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    public static final String JSON_FORMAT = "json";
    public static final String XML_FORMAT = "xml";

    public static final String DEFAULT_USER_AGENT = "nextcaller/java/0.0.1";

    private final BasicAuth auth;
    private MakeHttpRequest makeHttpRequest;

    public RestClient(BasicAuth auth) {
        this.auth = auth;
        this.makeHttpRequest = new MakeHttpRequest();
    }

    /**
     * Get profile by a profile id
     *
     * @param profileId Profile identifier
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByProfileId(String profileId)
            throws AuthenticationException, HttpException, IOException {

        return getByProfileId(profileId, JSON_FORMAT, false);
    }

    /**
     * Get profile by a profile id
     *
     * @param profileId      Profile identifier
     * @param responseFormat response format [json|xml] (default json)
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByProfileId(String profileId, String responseFormat)
            throws AuthenticationException, HttpException, IOException {

        return getByProfileId(profileId, responseFormat, false);
    }

    /**
     * Get profile by a profile id
     *
     * @param profileId      Profile identifier
     * @param responseFormat response format [json|xml] (default json)
     * @param debug          boolean (default false)
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByProfileId(String profileId, String responseFormat, boolean debug)
            throws AuthenticationException, HttpException, IOException {

        if (responseFormat == null || responseFormat.isEmpty()) {
            responseFormat = JSON_FORMAT;
        }

        String url = PrepareUrlUtil.prepareUrlByProfileId(profileId, responseFormat);

        String response = makeHttpRequest.makeRequest(auth, url, null, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT, responseFormat, debug);

        return ParseToObject.responseToMap(response, responseFormat);
    }

    /**
     * Get profiles by a phone
     *
     * @param phone 10 digits phone, str ot int
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByPhone(String phone)
            throws AuthenticationException, HttpException, IOException, ValidateException {

        return getByPhone(phone, JSON_FORMAT, false);
    }

    /**
     * Get profiles by a phone
     *
     * @param phone          10 digits phone, str ot int
     * @param responseFormat response format [json|xml] (default json)
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByPhone(String phone, String responseFormat)
            throws AuthenticationException, HttpException, IOException, ValidateException {

        return getByPhone(phone, responseFormat, false);
    }

    /**
     * Get profiles by a phone
     *
     * @param phone          10 digits phone, str ot int
     * @param responseFormat response format [json|xml] (default json)
     * @param debug          boolean (default false)
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByPhone(String phone, String responseFormat, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {

        ValidateUtil.validatePhone(phone);

        if (responseFormat == null || responseFormat.isEmpty()) {
            responseFormat = JSON_FORMAT;
        }

        String url = PrepareUrlUtil.prepareUrlByPhone(phone, responseFormat);

        String response = makeHttpRequest.makeRequest(auth, url, null, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT, responseFormat, debug);

        return ParseToObject.responseToMap(response, responseFormat);
    }

    /**
     * Update profile by a profile id
     *
     * @param profileId  Profile identifier
     * @param newProfile dictionary with changed data
     * @return
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public boolean updateByProfileId(String profileId, Map<String, Object> newProfile)
            throws AuthenticationException, HttpException, IOException {

        return updateByProfileId(profileId, newProfile, false);
    }

    /**
     * Update profile by a profile id
     *
     * @param profileId  Profile identifier
     * @param newProfile dictionary with changed data
     * @param debug      boolean (default false)
     * @return
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public boolean updateByProfileId(String profileId, Map<String, Object> newProfile, boolean debug)
            throws AuthenticationException, HttpException, IOException {

        String url = PrepareUrlUtil.prepareUrlByProfileId(profileId, JSON_FORMAT);

        String userRequest = ParseToObject.userToString(newProfile, JSON_FORMAT);

        String response = makeHttpRequest.makeRequest(auth, url, userRequest, MakeHttpRequest.POST_METHOD, DEFAULT_USER_AGENT, JSON_FORMAT, debug);

        return Boolean.valueOf(response);
    }

}
