package com.nextcaller.integration.client;

import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.RateLimitException;
import com.nextcaller.integration.exceptions.ValidationException;
import com.nextcaller.integration.response.ParseToObject;
import com.nextcaller.integration.util.PrepareUrlUtil;

import java.io.IOException;
import java.util.Map;

/**
 * The NextCaller API platform client
 */
public class PlatformNextCallerClient extends AbstractClient {

    /**
     * @param username The username identifies which application is making the request. Obtain this
     *                 value from checking the settings page for your application on dev.nextcaller.com/profile/api-usage.
     * @param password The password identifies which application is making the request.
     *                 Obtain this value from checking the settings page for your application on
     *                 dev.nextcaller.com/profile/api-usage.
     * @param sandbox  Set to true if you want to use the sandbox
     */
    public PlatformNextCallerClient(final String username, final String password, final boolean sandbox) {
        super(username, password, sandbox);
    }

    /**
     * @param username The username identifies which application is making the request. Obtain this
     *                 value from checking the settings page for your application on dev.nextcaller.com/profile/api-usage.
     * @param password The password identifies which application is making the request.
     *                 Obtain this value from checking the settings page for your application on
     *                 dev.nextcaller.com/profile/api-usage.
     */
    public PlatformNextCallerClient(final String username, final String password) {
        super(username, password, DEFAULT_SANDBOX);
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
    public Map<String, Object> getByProfileId(String profileId, String accountId)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {
        return super.getByProfileId(profileId, accountId);
    }

    /**
     * Get profiles by a phone
     *
     * @param phone            10 digits phone
     * @param accountId identifier of platform account
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByPhone(String phone, String accountId)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {
        return super.getByPhone(phone, accountId);
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
    public Map<String, Object> getByEmail(String email, String accountId)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {
        return super.getByEmail(email, accountId);
    }

    /**
     * Get profiles by a address and name
     *
     * @param addressNameData dictionary of address, name data
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByAddressName(Map<String, String> addressNameData, String accountId)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {
        return super.getByAddressName(addressNameData, accountId);
    }

    /**
     * Get fraud level by a phone
     *
     * @param phone            10 digits phone
     * @param accountId identifier of platform account
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getFraudLevel(String phone, String accountId)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {
        return super.getFraudLevel(phone, accountId);
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
    public boolean updateByProfileId(String profileId, Map<String, Object> profileData, String accountId)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {
        return super.updateByProfileId(profileId, profileData, accountId);
    }

    /**
     * Get platform statistics
     *
     * @param page int
     * @return map statistics
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     * @throws ValidationException
     */
    public Map<String, Object> getPlatformStatistics(int page)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {
        String url = PrepareUrlUtil.prepareUrlByPlatformStatistics(page, sandbox, API_VERSION);

        String response = makeHttpRequest.makeRequest(auth, url, null, null, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT);

        return ParseToObject.responseToMap(response);
    }

    /**
     * Get platform statistics
     *
     * @return map statistics
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     * @throws ValidationException
     */
    public Map<String, Object> getPlatformStatistics()
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {
        return getPlatformStatistics(1);
    }

    /**
     * Get platform account
     *
     * @param accountId identifier of platform account
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getPlatformAccount(String accountId)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {
        String url = PrepareUrlUtil.prepareUrlByPlatformAccountId(accountId, sandbox, API_VERSION);

        String response = makeHttpRequest.makeRequest(auth, url, null, null, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT);

        return ParseToObject.responseToMap(response);
    }

    /**
     * @param accountData dictionary with changed data
     * @return true if succeeded update, else false
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public boolean updatePlatformAccount(String accountId, Map<String, Object> accountData)
            throws AuthenticationException, HttpException, IOException, ValidationException, RateLimitException {
        String url = PrepareUrlUtil.prepareUrlByPlatformAccountId(accountId, sandbox, API_VERSION);

        String accountDataString = ParseToObject.mapToString(accountData);

        String response = makeHttpRequest.makeRequest(auth, url, accountDataString, null, MakeHttpRequest.POST_METHOD, DEFAULT_USER_AGENT);

        return Boolean.valueOf(response);
    }

}
