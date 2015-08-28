package com.nextcaller.integration.client;

import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.RateLimitException;
import com.nextcaller.integration.exceptions.ValidateException;

import java.io.IOException;
import java.util.Map;

/**
 * The NextCaller API client
 */
public class NextCallerClient extends AbstractClient {

    /**
     * @param username The username identifies which application is making the request. Obtain this
     *                 value from checking the settings page for your application on dev.nextcaller.com/profile/api-usage.
     * @param password The password identifies which application is making the request.
     *                 Obtain this value from checking the settings page for your application on
     *                 dev.nextcaller.com/profile/api-usage.
     * @param sandbox  Set to true if you want to use the sandbox
     */
    public NextCallerClient(final String username, final String password, final boolean sandbox) {
        super(username, password, sandbox);
    }

    /**
     * @param username The username identifies which application is making the request. Obtain this
     *                 value from checking the settings page for your application on dev.nextcaller.com/profile/api-usage.
     * @param password The password identifies which application is making the request.
     *                 Obtain this value from checking the settings page for your application on
     *                 dev.nextcaller.com/profile/api-usage.
     */
    public NextCallerClient(final String username, final String password) {
        super(username, password, DEFAULT_SANDBOX);
    }

    /**
     * Get profile by a profile id
     *
     * @param profileId profile identifier
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByProfileId(String profileId)
            throws AuthenticationException, HttpException, IOException, ValidateException, RateLimitException {
        return super.getByProfileId(profileId, null);
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
    public Map<String, Object> getByAddressName(Map<String, String> addressNameData)
            throws AuthenticationException, HttpException, IOException, ValidateException, RateLimitException {
        return super.getByAddressName(addressNameData, null);
    }

    /**
     * Get profiles by a phone
     *
     * @param phone 10 digits phone
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByPhone(String phone)
            throws AuthenticationException, HttpException, IOException, ValidateException, RateLimitException {
        return super.getByPhone(phone, null);
    }

    /**
     * Get profiles by a email
     *
     * @param email email
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByEmail(String email)
            throws AuthenticationException, HttpException, IOException, ValidateException, RateLimitException {
        return super.getByEmail(email, null);
    }

    /**
     * Update profile by a profile id
     *
     * @param profileId  Profile identifier
     * @param profileData dictionary with changed data
     * @return true if succeeded update, else false
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public boolean updateByProfileId(String profileId, Map<String, Object> profileData)
            throws AuthenticationException, HttpException, IOException, ValidateException, RateLimitException {
        return super.updateByProfileId(profileId, profileData, null);
    }

    /**
     * Get fraud level by a phone
     *
     * @param phone 10 digits phone
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getFraudLevel(String phone)
            throws AuthenticationException, HttpException, IOException, ValidateException, RateLimitException {
        return getFraudLevel(phone, null);
    }

}
