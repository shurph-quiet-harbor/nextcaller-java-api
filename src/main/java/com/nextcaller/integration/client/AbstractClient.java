package com.nextcaller.integration.client;

import com.nextcaller.integration.auth.BasicAuth;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.RateLimitException;
import com.nextcaller.integration.exceptions.ValidationException;
import com.nextcaller.integration.response.ParseToObject;
import com.nextcaller.integration.util.PrepareUrlUtil;
import com.nextcaller.integration.util.VersionProvider;

import java.io.IOException;
import java.util.Map;


abstract class AbstractClient {

    public static final String DEFAULT_USER_AGENT = "nextcaller/java/" + VersionProvider.getVersion();

    protected static final boolean DEFAULT_SANDBOX = false;
    public static final String API_VERSION = "2.1";

    protected final BasicAuth auth;
    protected final MakeHttpRequest makeHttpRequest;
    protected final boolean sandbox;

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
     * Get profile by a profile id
     *
     * @param profileId profile identifier
     * @param accountId identifier of platform account
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    protected Map<String, Object> getByProfileId(String profileId, String accountId)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {
        String url = PrepareUrlUtil.prepareUrlByProfileId(profileId, sandbox, API_VERSION);

        String response = makeHttpRequest.makeRequest(auth, url, null, accountId, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT);

        return ParseToObject.responseToMap(response);
    }

    /**
     * Get profiles by a phone
     *
     * @param phone     10 digits phone
     * @param accountId identifier of platform account
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    protected Map<String, Object> getByPhone(String phone, String accountId)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {

        String url = PrepareUrlUtil.prepareUrlByPhone(phone, sandbox, API_VERSION);

        String response = makeHttpRequest.makeRequest(auth, url, null, accountId, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT);

        return ParseToObject.responseToMap(response);
    }

    /**
     * Get profiles by a email
     *
     * @param email     email
     * @param accountId identifier of platform account
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    protected Map<String, Object> getByEmail(String email, String accountId)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {

        String url = PrepareUrlUtil.prepareUrlByEmail(email, sandbox, API_VERSION);

        String response = makeHttpRequest.makeRequest(auth, url, null, accountId, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT);

        return ParseToObject.responseToMap(response);
    }

    /**
     * Get profiles by a phone
     *
     * @param nameAddressData  dictionary with address data
     * @param accountId identifier of platform account
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    protected Map<String, Object> getByNameAddress(Map<String, String> nameAddressData, String accountId)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {

        String url = PrepareUrlUtil.prepareUrlByNameAddress(nameAddressData, sandbox, API_VERSION);

        String response = makeHttpRequest.makeRequest(auth, url, null, accountId, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT);

        return ParseToObject.responseToMap(response);
    }

    /**
     * Update profile by a profile id
     *
     * @param profileId  profile identifier
     * @param profileData dictionary with changed data
     * @param accountId  identifier of platform account
     * @return true if succeeded update, else false
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    protected boolean updateByProfileId(String profileId, Map<String, Object> profileData, String accountId)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {
        String url = PrepareUrlUtil.prepareUrlByProfileId(profileId, sandbox, API_VERSION);

        String profileDataString = ParseToObject.mapToString(profileData);

        String response = makeHttpRequest.makeRequest(auth, url, profileDataString, accountId, MakeHttpRequest.POST_METHOD, DEFAULT_USER_AGENT);

        return Boolean.valueOf(response);
    }

}
