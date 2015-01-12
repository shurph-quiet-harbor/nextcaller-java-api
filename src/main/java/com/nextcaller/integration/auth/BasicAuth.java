package com.nextcaller.integration.auth;

import javax.xml.bind.DatatypeConverter;

public class BasicAuth {

    private String username;
    private String password;

    /**
     * @param username The username identifies which application is making the request. Obtain this
     *                 value from checking the settings page for your application on dev.nextcaller.com/profile/api-usage.
     * @param password The password identifies which application is making the request.
     *                 Obtain this value from checking the settings page for your application on
     *                 dev.nextcaller.com/profile/api-usage.
     */
    public BasicAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return http header for Basic Authentication
     */
    public String getHeaders() {
        String value = DatatypeConverter.printBase64Binary((username + ":" + password).getBytes());

        return "Basic " + value;
    }

}
