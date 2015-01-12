package com.nextcaller.integration.client;

import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
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
    public NextCallerClient(String username, String password, boolean sandbox) {
        super(username, password, sandbox);
    }

    /**
     * @param username The username identifies which application is making the request. Obtain this
     *                 value from checking the settings page for your application on dev.nextcaller.com/profile/api-usage.
     * @param password The password identifies which application is making the request.
     *                 Obtain this value from checking the settings page for your application on
     *                 dev.nextcaller.com/profile/api-usage.
     */
    public NextCallerClient(String username, String password) {
        super(username, password);
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
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return getByProfileId(profileId, DEFAULT_DEBUG);
    }

    /**
     * Get profile by a profile id
     *
     * @param profileId Profile identifier
     * @param debug     boolean (default false)
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByProfileId(String profileId, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return super.getByProfileId(profileId, null, debug);
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
        return getByPhone(phone, DEFAULT_DEBUG);
    }

    /**
     * Get profiles by a phone
     *
     * @param phone 10 digits phone, str ot int
     * @param debug boolean (default false)
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByPhone(String phone, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return super.getByPhone(phone, null, debug);
    }

    /**
     * Update profile by a profile id
     *
     * @param profileId  Profile identifier
     * @param newProfile dictionary with changed data
     * @return true if succeeded update, else false
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public boolean updateByProfileId(String profileId, Map<String, Object> newProfile)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return updateByProfileId(profileId, newProfile, DEFAULT_DEBUG);
    }

    /**
     * Update profile by a profile id
     *
     * @param profileId  Profile identifier
     * @param newProfile dictionary with changed data
     * @param debug      boolean (default false)
     * @return true if succeeded update, else false
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public boolean updateByProfileId(String profileId, Map<String, Object> newProfile, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return super.updateByProfileId(profileId, newProfile, null, debug);
    }

    /**
     * Get fraud level by a phone
     *
     * @param phone 10 digits phone, str ot int
     * @param debug boolean (default false)
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getFraudLevel(String phone, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return super.getFraudLevel(phone, null, debug);
    }

    /**
     * Get fraud level by a phone
     *
     * @param phone 10 digits phone, str ot int
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getFraudLevel(String phone)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return getFraudLevel(phone, DEFAULT_DEBUG);
    }

}
