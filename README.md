nextcaller-java-api
=====================

Java wrapper for the Nextcaller API.
Supports java v6 and newer


Installation
------------

**Dependencies**:

* jackson-jaxrs

**Installation**:

*cloning from the GitHub repo*:

    $ git clone git://github.com/nextcaller/Nextcaller-java-api.git
    $ cd nextcaller-java-api
    $ mvn clean install

*in order to add lib, maven dependency should be added*:


    <dependency>
        <groupId>com.nextcaller.integration-java</groupId>
        <artifactId>integration-java</artifactId>
        <version>1.1</version>
    </dependency>

    
    
Example
-------

    import com.nextcaller.integration.client.NextCallerClient;
    import com.nextcaller.integration.exceptions.AuthenticationException;
    import com.nextcaller.integration.exceptions.HttpException;
    import com.nextcaller.integration.exceptions.ValidateException;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    import java.io.IOException;
    import java.util.Map;

    public class ClientExample {

        private static final Logger logger = LoggerFactory.getLogger(ClientExample.class);

        private static final String username = "XXXXX";
        private static final String password = "XXXXX";
        private static final boolean sandbox = true;
        private static final boolean debug = true;
        private static final String phone = "1211211212";

        public static void main(String[] args) {
            NextCallerClient client = new NextCallerClient(username, password, sandbox);

            try {
                Map<String, Object> response = client.getByPhone(phone, debug);
                logger.info(response.toString());
            } catch (HttpException e) {
                logger.error("HttpException. Http status code {}. Response code {}. Response message {}.",
                        e.getHttpStatusCode(), e.getErrorMessage().getCode(), e.getErrorMessage().getMessage());
            } catch (ValidateException e) {
                logger.error("ValidateException: {}", e.getMessage());
            } catch (AuthenticationException e) {
                logger.error("AuthenticationException({}): {}", e.getErrorMessage().getCode(), e.getErrorMessage().getMessage());
            } catch (IOException e) {
                logger.error("IOException: {}", e.getMessage());
            }
        }
    }


NextCallerClient
----------------

    String username = "XXXXX";
    String password = "XXXXX";
    boolean sandbox = true;
    import com.nextcaller.integration.client.NextCallerClient;
    NextCallerClient client = new NextCallerClient(username, password, sandbox);

**Parameters**:

    username            -- username
    password            -- password
    sandbox             -- boolean (default false)
    

API Items
-------------

### Get profile by phone ###

    boolean debug = true;
    Map<String, Object> res = client.getByPhone(phone, debug);

**Parameters**:

    phone               -- 10 digits phone, str ot int
    debug               -- boolean (default false)

### Get profile by id ###

    boolean debug = true;
    Map<String, Object> res = client.getByProfileId(profileId, debug);

**Parameters**:

    profileId           -- Profile identifier, str, length is 30
    debug               -- boolean (default false)

### Update profile by id ###

    boolean debug = true;
    client.updateByProfileId(profileId, data, debug);

**Parameters**:

    profileId           -- Profile identifier, str, length is 30
    data                -- dictionary with changed data
    debug               -- boolean (default false)

**Example**:

    boolean debug = true;
    String profileId = "XXXXXXXXX";
    Map<String,Object> data = new HashMap<String,Object>();
    data.put("email", "test@test.com");
    boolean success = client.updateByProfileId(profileId, data, debug);

**Response**:

*Returns **204 No Content** response in the case of the successful request.*


### Get fraud level ###

    boolean debug = true;
    Map<String, Object> res = client.getFraudLevel(phone, debug);
    
**Parameters**:

    phone               -- 10 digits phone, str ot int
    debug               -- boolean (default false)



Exceptions
-------------

In case of wrong phone number a ValidateException exception will be thrown:
    
    ValidateException: Invalid phone number: 121121122. Phone should has length 10.

In case of wrong profile id a ValidateException exception will be thrown:

    ValidateException: Invalid profile id: XXXXXXXXXXXXXXXXXXXX. Profile id should has length 30.

In case of wrong username or password a AuthenticationException exception will be thrown:

    AuthenticationException(1046): Your consumer key is invalid. Please contact support@nextcaller.com to troubleshoot this issue.
    
In case of the library cannot parse response,
the [java.io.IOException](http://docs.oracle.com/javase/6/docs/api/java/io/IOException.html) exception is raised.

In case of the library gets a response with the http code more or equal 400,
the HttpException exception is raised.
    
    HttpException: http status code 404, response code 404, response message: Resource hasn't been found.

If a request times out,
the [java.io.IOException](http://docs.oracle.com/javase/6/docs/api/java/io/IOException.html) exception is raised.


