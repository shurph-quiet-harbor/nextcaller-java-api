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

    private static final int HTTP_STATUS_SERVER_ERROR = 500;
    private static final int HTTP_STATUS_BAD_REQUEST = 400;
    private static final int HTTP_STATUS_UNAUTHORIZED = 401;
    private static final int HTTP_STATUS_NO_CONTENT = 204;

    /**
     * @param auth        http header for Basic authentication
     * @param url         to send the request
     * @param data        the data sended by url
     * @param method      the HTTP method
     * @param userAgent   the name of the source of the request
     * @param contentType - internet media type. Can be set to "GET" and "POST"
     * @param debug
     * @return response
     * @throws AuthenticationException
     * @throws HttpException
     */
    public String makeRequest(BasicAuth auth, String url, String data, String method, String userAgent,
                              String contentType, boolean debug) throws AuthenticationException, HttpException {

        URL connectionUrl;
        HttpsURLConnection connection = null;

        String response = null;

        try {

            connectionUrl = new URL(url);
            connection = (HttpsURLConnection) connectionUrl.openConnection();

            if (debug) {
                logger.debug("Request url: " + connectionUrl);
            }

            addConnectionParams(connection, auth, method, userAgent, contentType, data);

            if (method.equals(POST_METHOD) && debug) {
                logger.debug("Request body: " + data);
            }

            if (connection.getResponseCode() >= HTTP_STATUS_SERVER_ERROR) {
                response = getStringRequest(connection, true);
                String message = ParseToObject.responseToMap(response, contentType).get("error_message").toString();

                throw new HttpException(message);
            } else if (connection.getResponseCode() >= HTTP_STATUS_BAD_REQUEST) {

                response = getStringRequest(connection, true);

                RestError err = new RestError();
                if (response != null && !response.isEmpty()) {
                    err = ParseToObject.getError(response, contentType);
                }

                if (connection.getResponseCode() == HTTP_STATUS_UNAUTHORIZED) {
                    throw new AuthenticationException(err.getError());
                } else {
                    throw new HttpException(err.getError());
                }

            } else {
                response = getStringRequest(connection, false);
            }

            if (method.equals(POST_METHOD) && connection.getResponseCode() == HTTP_STATUS_NO_CONTENT) {
                response = "true";
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

        if (debug) {
            logger.debug("Response: " + response);
        }

        return response;
    }

    private void addConnectionParams(HttpsURLConnection connection, BasicAuth auth, String method, String userAgent,
                                     String contentType, String data) throws IOException {

        if (method != null && !method.isEmpty()) {
            connection.setRequestMethod(method);
        } else {
            connection.setRequestMethod(GET_METHOD);
        }

        connection.setRequestProperty("Authorization", auth.getHeaders());
        connection.setRequestProperty("Connection", "Keep-Alive");

        if (userAgent != null && !userAgent.isEmpty()) {
            connection.setRequestProperty("User-Agent", userAgent);
        } else {
            connection.setRequestProperty("User-Agent", RestClient.DEFAULT_USER_AGENT);
        }

        if (data != null) {

            if (contentType != null && contentType.equals(RestClient.JSON_FORMAT)) {
                connection.setRequestProperty("Content-Type", "application/json");
            } else {
                connection.setRequestProperty("Content-Type", "application/xml");
            }

            connection.setDoOutput(true);

            byte[] postDataBytes = data.getBytes("UTF-8");
            connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            connection.getOutputStream().write(postDataBytes);
            connection.getOutputStream().flush();
            connection.getOutputStream().close();
        }

    }

    private String getStringRequest(HttpsURLConnection con, boolean isError) {

        String input = "";

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
                        input += str;
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

        return input;
    }

}
