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
     * @param version  Set API version
     * @param debug    Set debug output. Default false
     */
    private NextCallerClient(String username, String password, boolean sandbox, String version, boolean debug) {
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

        final public NextCallerClient build() {
            return new NextCallerClient(username, password, sandbox, version, debug);
        }

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
        return super.getByProfileId(profileId, null);
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
    public Map<String, Object> getByAddressName(Map<String, String> addressData)
            throws AuthenticationException, HttpException, IOException, ValidateException {
        return super.getByAddressName(addressData, null);
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
        return super.getByPhone(phone, null);
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
        return super.updateByProfileId(profileId, newProfile, null);
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
        return getFraudLevel(phone, null);
    }

}
