package com.nextcaller.integration.client;

import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.exceptions.ValidateException;
import com.nextcaller.integration.response.ParseToObject;
import com.nextcaller.integration.util.PrepareUrlUtil;
import com.nextcaller.integration.util.ValidateUtil;

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
    public PlatformNextCallerClient(String username, String password, boolean sandbox) {
        super(username, password, sandbox);
    }

    /**
     * @param username The username identifies which application is making the request. Obtain this
     *                 value from checking the settings page for your application on dev.nextcaller.com/profile/api-usage.
     * @param password The password identifies which application is making the request.
     *                 Obtain this value from checking the settings page for your application on
     *                 dev.nextcaller.com/profile/api-usage.
     */
    public PlatformNextCallerClient(String username, String password) {
        super(username, password);
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
    @Override
    public Map<String, Object> getByProfileId(String profileId, String platformUsername, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        ValidateUtil.validatePlatformUsername(platformUsername);

        return super.getByProfileId(profileId, platformUsername, debug);
    }

    /**
     * Get profile by a profile id
     *
     * @param profileId        Profile identifier
     * @param platformUsername Platform username
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByProfileId(String profileId, String platformUsername)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return getByProfileId(profileId, platformUsername, DEFAULT_DEBUG);
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
    @Override
    public Map<String, Object> getByPhone(String phone, String platformUsername, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        ValidateUtil.validatePlatformUsername(platformUsername);

        return super.getByPhone(phone, platformUsername, debug);
    }

    /**
     * Get profiles by a phone
     *
     * @param phone            10 digits phone, str ot int
     * @param platformUsername Platform username
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByPhone(String phone, String platformUsername)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return getByPhone(phone, platformUsername, DEFAULT_DEBUG);
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
    @Override
    public Map<String, Object> getFraudLevel(String phone, String platformUsername, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        ValidateUtil.validatePlatformUsername(platformUsername);

        return super.getFraudLevel(phone, platformUsername, debug);
    }

    /**
     * Get fraud level by a phone
     *
     * @param phone            10 digits phone, str ot int
     * @param platformUsername Platform username
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getFraudLevel(String phone, String platformUsername)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return getFraudLevel(phone, platformUsername, DEFAULT_DEBUG);
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
    @Override
    public boolean updateByProfileId(String profileId, Map<String, Object> newProfile, String platformUsername, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        ValidateUtil.validatePlatformUsername(platformUsername);

        return super.updateByProfileId(profileId, newProfile, platformUsername, debug);
    }

    /**
     * Update profile by a profile id
     *
     * @param profileId        Profile identifier
     * @param newProfile       dictionary with changed data
     * @param platformUsername Platform username
     * @return true if succeeded update, else false
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public boolean updateByProfileId(String profileId, Map<String, Object> newProfile, String platformUsername)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return updateByProfileId(profileId, newProfile, platformUsername, DEFAULT_DEBUG);
    }

    /**
     * Get platform statistics
     *
     * @param debug boolean (default false)
     * @param page int
     * @return map statistics
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     * @throws ValidateException
     */
    public Map<String, Object> getPlatformStatistics(int page, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        String url = PrepareUrlUtil.prepareUrlByPlatformStatistics(page, sandbox);

        String response = makeHttpRequest.makeRequest(auth, url, null, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT, debug);

        return ParseToObject.responseToMap(response);
    }

    /**
     * Get platform statistics
     *
     * @param page int
     * @return map statistics
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     * @throws ValidateException
     */
    public Map<String, Object> getPlatformStatistics(int page)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return getPlatformStatistics(page, DEFAULT_DEBUG);
    }

    /**
     * Get platform statistics
     *
     * @return map statistics
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     * @throws ValidateException
     */
    public Map<String, Object> getPlatformStatistics()
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return getPlatformStatistics(1);
    }

    /**
     * Get platform user
     *
     * @param platformUsername platform username
     * @param debug    boolean (default false)
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getPlatformUser(String platformUsername, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        ValidateUtil.validatePlatformUsername(platformUsername);

        String url = PrepareUrlUtil.prepareUrlByPlatformUser(platformUsername, sandbox);

        String response = makeHttpRequest.makeRequest(auth, url, null, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT, debug);

        return ParseToObject.responseToMap(response);
    }

    /**
     * Get platform user
     *
     * @param platformUsername platform username
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getPlatformUser(String platformUsername)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return getPlatformUser(platformUsername, DEFAULT_DEBUG);
    }

    /**
     * @param newProfile dictionary with changed data
     * @param debug      boolean (default false)
     * @return true if succeeded update, else false
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public boolean updatePlatformUser(String platformUsername, Map<String, Object> newProfile, boolean debug)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        ValidateUtil.validatePlatformUsername(platformUsername);

        String url = PrepareUrlUtil.prepareUrlByPlatformUser(platformUsername, sandbox);

        String userRequest = ParseToObject.userToString(newProfile);

        String response = makeHttpRequest.makeRequest(auth, url, userRequest, MakeHttpRequest.POST_METHOD, DEFAULT_USER_AGENT, debug);

        return Boolean.valueOf(response);
    }

    /**
     * @param newProfile dictionary with changed data
     * @return true if succeeded update, else false
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public boolean updatePlatformUser(String platformUsername, Map<String, Object> newProfile)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return updatePlatformUser(platformUsername, newProfile, DEFAULT_DEBUG);
    }
}
