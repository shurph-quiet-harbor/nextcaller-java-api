package com.nextcaller.integration.client;

import com.nextcaller.integration.auth.BasicAuth;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.ValidateException;
import com.nextcaller.integration.response.ParseToObject;
import com.nextcaller.integration.util.PrepareUrlUtil;
import com.nextcaller.integration.util.ValidateUtil;
import com.nextcaller.integration.util.VersionProvider;

import java.io.IOException;
import java.util.Map;

abstract class AbstractClient {

    public static final String DEFAULT_USER_AGENT = "nextcaller/java/" + VersionProvider.getVersion();

    protected static final boolean DEFAULT_DEBUG = false;
    protected static final boolean DEFAULT_SANDBOX = false;

    protected final BasicAuth auth;
    protected MakeHttpRequest makeHttpRequest;
    protected boolean sandbox;

    /**
     * @param username The username identifies which application is making the request. Obtain this
     *                 value from checking the settings page for your application on dev.nextcaller.com/profile/api-usage.
     * @param password The password identifies which application is making the request.
     *                 Obtain this value from checking the settings page for your application on
     *                 dev.nextcaller.com/profile/api-usage.
     * @param sandbox  Set to true if you want to use the sandbox
     */
    protected AbstractClient(String username, String password, boolean sandbox) {
        this.auth = new BasicAuth(username, password);
        this.makeHttpRequest = new MakeHttpRequest();
        this.sandbox = sandbox;
    }

    /**
     * @param username The username identifies which application is making the request. Obtain this
     *                 value from checking the settings page for your application on dev.nextcaller.com/profile/api-usage.
     * @param password The password identifies which application is making the request.
     *                 Obtain this value from checking the settings page for your application on
     *                 dev.nextcaller.com/profile/api-usage.
     */
    protected AbstractClient(String username, String password) {
        this(username, password, DEFAULT_SANDBOX);
    }

    /**
     * Get profile by a profile id
     *
     * @param profileId        Profile identifier
     * @param platformUsername Platform username
     * @param debug            boolean (default false)
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    protected Map<String, Object> getByProfileId(String profileId, String platformUsername, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        ValidateUtil.validateProfileId(profileId);
        
        String url = PrepareUrlUtil.prepareUrlByProfileId(profileId, platformUsername, sandbox);

        String response = makeHttpRequest.makeRequest(auth, url, null, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT, debug);

        return ParseToObject.responseToMap(response);
    }

    /**
     * Get profiles by a phone
     *
     * @param phone            10 digits phone, str ot int
     * @param platformUsername Platform username
     * @param debug            boolean (default false)
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    protected Map<String, Object> getByPhone(String phone, String platformUsername, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {

        ValidateUtil.validatePhone(phone);

        String url = PrepareUrlUtil.prepareUrlByPhone(phone, platformUsername, sandbox);

        String response = makeHttpRequest.makeRequest(auth, url, null, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT, debug);

        return ParseToObject.responseToMap(response);
    }

    /**
     * Get profiles by a phone
     *
     * @param addressNameData  dictionary with address data
     * @param platformUsername Platform username
     * @param debug            boolean (default false)
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    protected Map<String, Object> getByAddressName(Map<String, String> addressNameData, String platformUsername, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {

        ValidateUtil.validateAddressName(addressNameData);

        String url = PrepareUrlUtil.prepareUrlByAddressName(addressNameData, platformUsername, sandbox);

        String response = makeHttpRequest.makeRequest(auth, url, null, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT, debug);

        return ParseToObject.responseToMap(response);
    }

    /**
     * Get fraud level by a phone
     *
     * @param phone            10 digits phone, str ot int
     * @param platformUsername Platform username
     * @param debug            boolean (default false)
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    protected Map<String, Object> getFraudLevel(String phone, String platformUsername, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {

        ValidateUtil.validatePhone(phone);

        String url = PrepareUrlUtil.prepareUrlByFraudLevel(phone, platformUsername, sandbox);

        String response = makeHttpRequest.makeRequest(auth, url, null, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT, debug);

        return ParseToObject.responseToMap(response);
    }

    /**
     * Update profile by a profile id
     *
     * @param profileId        Profile identifier
     * @param newProfile       dictionary with changed data
     * @param platformUsername Platform username
     * @param debug            boolean (default false)
     * @return true if succeeded update, else false
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    protected boolean updateByProfileId(String profileId, Map<String, Object> newProfile, String platformUsername, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        ValidateUtil.validateProfileId(profileId);
        
        String url = PrepareUrlUtil.prepareUrlByProfileId(profileId, platformUsername, sandbox);

        String userRequest = ParseToObject.userToString(newProfile);

        String response = makeHttpRequest.makeRequest(auth, url, userRequest, MakeHttpRequest.POST_METHOD, DEFAULT_USER_AGENT, debug);

        return Boolean.valueOf(response);
    }

}
