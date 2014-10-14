package com.nextcaller.integration.auth;

import javax.xml.bind.DatatypeConverter;

public class BasicAuth {

    private String apiKey;
    private String apiSecret;

    /**
     *
     * @param apiKey (consumer key) The consumer_key identifies which application is making the request. Obtain this
     *               value from checking the settings page for your application on dev.nextcaller.com/profile/api-usage.
     * @param apiSecret (consumer secret) The consumer_secret identifies which application is making the request.
     *                  Obtain this value from checking the settings page for your application on
     *                  dev.nextcaller.com/profile/api-usage.
     */
    public BasicAuth(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    /**
     *
     * @return http header for Basic Authentication
     */
    public String getHeaders() {
        String value = DatatypeConverter.printBase64Binary((apiKey + ":" + apiSecret).getBytes());

        return "Basic " + value;
    }

}
