package com.nextcaller.integration.client;

import com.nextcaller.integration.auth.BasicAuth;
import com.nextcaller.integration.exceptions.AuthenticationException;
import com.nextcaller.integration.exceptions.HttpException;
import com.nextcaller.integration.response.ParseToObject;
import com.nextcaller.integration.response.RestError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MakeHttpRequest {

    private static final Logger logger = LoggerFactory.getLogger(MakeHttpRequest.class);

    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";

    private static final String ERROR_MESSAGE_RESPONSE_OBJECT = "error_message";

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String USER_AGENT_HEADER_NAME = "User-Agent";
    private static final String CONNECTION_HEADER_NAME = "Connection";
    private static final String CONNECTION_HEADER_VALUE = "Keep-Alive";
    private static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";
    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    private static final String CONTENT_LENGTH_HEADER_NAME = "Content-Length";
    private static final String PLATFORM_ACCOUNT_HEADER = "Nc-Account-Id";
    
    private static final int DEFAULT_REQUEST_TIMEOUT = 60000; // 60 second

    private static final String MESSAGE_ENCODING = "UTF-8";

    private static final String UPDATE_RESPONSE_VALUE = "true";

    public static final String JSON_FORMAT = "json";

    /**
     * @param auth      http header for Basic authentication
     * @param url       to send the request
     * @param data      the data sended by url
     * @param accountId identifier of platform account
     * @param method    the HTTP method
     * @param userAgent the name of the source of the request
     * @return response
     * @throws AuthenticationException
     * @throws HttpException
     */
    public String makeRequest(BasicAuth auth, String url, String data, String accountId,
                              String method, String userAgent) throws AuthenticationException, HttpException {

        URL connectionUrl;
        HttpsURLConnection connection = null;

        String response = null;

        try {

            connectionUrl = new URL(url);
            connection = getConnection(connectionUrl);
            connection.setConnectTimeout(DEFAULT_REQUEST_TIMEOUT);

            addConnectionParams(connection, auth, method, userAgent, data, accountId);

            int responseCode = connection.getResponseCode();

            if (responseCode >= HttpsURLConnection.HTTP_INTERNAL_ERROR) {
                response = getStringRequest(connection, true);
                Object error = ParseToObject.responseToMap(response).get(ERROR_MESSAGE_RESPONSE_OBJECT);
                String message = null;
                if (error != null) {
                    message = error.toString();
                }

                throw new HttpException(message, responseCode);
            } else if (responseCode >= HttpsURLConnection.HTTP_BAD_REQUEST) {

                response = getStringRequest(connection, true);

                RestError err = null;
                if (response != null && !response.isEmpty()) {
                    err = ParseToObject.getError(response);
                }

                if (responseCode == HttpsURLConnection.HTTP_UNAUTHORIZED) {
                    throw new AuthenticationException(err.getError());
                } else if (err != null) {
                    throw new HttpException(err.getError(), responseCode);
                } else {
                    Object error = ParseToObject.responseToMap(response).get(ERROR_MESSAGE_RESPONSE_OBJECT);
                    String message = null;
                    if (error != null) {
                        message = error.toString();
                    }
                    throw new HttpException(message, responseCode);
                }

            } else {
                response = getStringRequest(connection, false);
            }

            if (method.equals(POST_METHOD) && (responseCode == HttpsURLConnection.HTTP_NO_CONTENT
                    || responseCode == HttpsURLConnection.HTTP_OK)) {
                response = UPDATE_RESPONSE_VALUE;
            }

        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response;
    }

    protected HttpsURLConnection getConnection(URL connectionUrl) throws IOException {
        return (HttpsURLConnection) connectionUrl.openConnection();
    }

    private void addConnectionParams(HttpsURLConnection connection, BasicAuth auth, String method, String userAgent,
                                     String data, String accountId) throws IOException {

        if (method != null && !method.isEmpty()) {
            connection.setRequestMethod(method);
        } else {
            connection.setRequestMethod(GET_METHOD);
        }

        connection.setRequestProperty(AUTHORIZATION_HEADER_NAME, auth.getHeaders());
        connection.setRequestProperty(CONNECTION_HEADER_NAME, CONNECTION_HEADER_VALUE);
        if (accountId != null)
            connection.setRequestProperty(PLATFORM_ACCOUNT_HEADER, accountId);

        if (userAgent != null && !userAgent.isEmpty()) {
            connection.setRequestProperty(USER_AGENT_HEADER_NAME, userAgent);
        } else {
            connection.setRequestProperty(USER_AGENT_HEADER_NAME, NextCallerClient.DEFAULT_USER_AGENT);
        }

        if (data != null) {

            connection.setRequestProperty(CONTENT_TYPE_HEADER_NAME, CONTENT_TYPE_APPLICATION_JSON);
            connection.setDoOutput(true);

            byte[] postDataBytes = data.getBytes(MESSAGE_ENCODING);
            connection.setRequestProperty(CONTENT_LENGTH_HEADER_NAME, String.valueOf(postDataBytes.length));
            connection.getOutputStream().write(postDataBytes);
            connection.getOutputStream().flush();
            connection.getOutputStream().close();
        }

    }

    private String getStringRequest(HttpsURLConnection con, boolean isError) {

        StringBuffer input = new StringBuffer();

        if (con != null) {

            BufferedReader br = null;

            try {

                if (isError) {
                    if (con.getErrorStream() != null) {
                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }
                } else {
                    if (con.getInputStream() != null) {
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    }
                }

                if (br != null) {
                    String str;

                    while ((str = br.readLine()) != null) {
                        input.append(str);
                    }
                }

            } catch (IOException e) {
                logger.error(e.getMessage());
            } finally {

                if (br != null) {

                    try {
                        br.close();
                    } catch (IOException e) {
                        logger.error(e.getMessage());
                    }
                }

            }

        }

        return input.toString();
    }

}
