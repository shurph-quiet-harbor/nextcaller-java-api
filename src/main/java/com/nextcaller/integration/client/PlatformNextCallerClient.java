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
     * @param version  Set API version
     * @param debug    Set debug output. Default false
     */
    private PlatformNextCallerClient(
            final String username, final String password, final boolean sandbox,
            final String version, final boolean debug) {
        super(username, password, sandbox, version, debug);
    }

    public static class Builder {

        private String username;
        private String password;
        private boolean sandbox;
        private boolean debug;
        private String version;

        public Builder(String username, String password) {
            this.username = username;
            this.password = password;
            this.version = DEFAULT_API_VERSION;
            this.sandbox = DEFAULT_SANDBOX;
            this.debug = DEFAULT_DEBUG;
        }

        final public Builder setDebugMode(final boolean debug) {
            this.debug = debug;
            return this;
        }

        final public Builder setDebugMode() {
            this.debug = true;
            return this;
        }

        final public Builder setSandboxMode(final boolean sandbox) {
            this.sandbox = sandbox;
            return this;
        }

        final public Builder setSandboxMode() {
            this.sandbox = true;
            return this;
        }

        final public Builder setVersion(final String version) {
            this.version = version;
            return this;
        }

        final public Builder setVersion(final int version) {
            this.version = Integer.toString(version);
            return this;
        }

        final public Builder setVersion(final double version) {
            this.version = Double.toString(version);
            return this;
        }

        final public PlatformNextCallerClient build() {
            return new PlatformNextCallerClient(username, password, sandbox, version, debug);
        }

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
        return super.getByProfileId(profileId, platformUsername);
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
        return super.getByPhone(phone, platformUsername);
    }

    /**
     * Get profiles by a address and name
     *
     * @param addressData dictionary of address, name data
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getByAddressName(Map<String, String> addressData, String platformUsername)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return super.getByAddressName(addressData, platformUsername);
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
        return super.getFraudLevel(phone, platformUsername);
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
        return super.updateByProfileId(profileId, newProfile, platformUsername);
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
        String url = PrepareUrlUtil.prepareUrlByPlatformStatistics(page, sandbox, version);

        String response = makeHttpRequest.makeRequest(auth, url, null, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT, debug);

        return ParseToObject.responseToMap(response);
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
     * @return map user
     * @throws AuthenticationException
     * @throws HttpException
     * @throws IOException
     */
    public Map<String, Object> getPlatformUser(String platformUsername)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        ValidateUtil.validatePlatformUsername(platformUsername);

        String url = PrepareUrlUtil.prepareUrlByPlatformUser(platformUsername, sandbox, version);

        String response = makeHttpRequest.makeRequest(auth, url, null, MakeHttpRequest.GET_METHOD, DEFAULT_USER_AGENT, debug);

        return ParseToObject.responseToMap(response);
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
        ValidateUtil.validatePlatformUsername(platformUsername);

        String url = PrepareUrlUtil.prepareUrlByPlatformUser(platformUsername, sandbox, version);

        String userRequest = ParseToObject.userToString(newProfile);

        String response = makeHttpRequest.makeRequest(auth, url, userRequest, MakeHttpRequest.POST_METHOD, DEFAULT_USER_AGENT, debug);

        return Boolean.valueOf(response);
    }

}
