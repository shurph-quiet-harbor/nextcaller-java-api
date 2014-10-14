nextcaller-java-api
=====================

Java wrapper for the Nextcaller API.
Supports java v6 and newer


Installation
------------

**Dependencies**: see pom.xml file "dependencies" section

**Installation**:

*cloning from the GitHub repo*:

    $ git clone git://github.com/nextcaller/Nextcaller-java-api.git
    $ cd nextcaller-java-api
    $ mvn clean install

*in order to add lib, maven dependency should be added*:

  <dependency>
      <groupId>com.nextcaller.integration-java</groupId>
      <artifactId>integration-java</artifactId>
      <version>1.0</version>
  </dependency>


How to use example
-------
    import com.nextcaller.integration.client.RestClient; //importing classes
    import com.nextcaller.integration.auth.BasicAuth;

    String apiKey = "XXXXX"; // api key
    String apiSecret = "XXXXX"; // api secret
    String phoneNumber = "121212...";
    BasicAuth auth = new BasicAuth(apiKey, apiSecret);
    RestClient client = new RestClient(auth);
    Map<String,Object> resp = client.getByPhone(phoneNumber);
    System.out.println(resp);


Client
-------------

    import com.nextcaller.integration.client.RestClient;
    import com.nextcaller.integration.auth.BasicAuth;

    String apiKey = "XXXXX";
    String apiSecret = "XXXXX";
    RestClient client = new RestClient(new BasicAuth(apiKey, apiSecret))

**Parameters**:

    apiKey - api key
    apiSecret - api secret


API Items
-------------

### Get profile by phone ###

    res = client.getByPhone(number, responseFormat)

**Parameters**:

    number - phone number
    responseFormat - [json|xml] - required response format

### Get profile by id ###

    res = client.getByProfileId(profileId, responseFormat)

**Parameters**:

    profileId - id of a profile
    responseFormat - [json|xml] - required response format


### Update profile by id ###

    res = client.updateByProfileId(profileId, newProfile)

**Parameters**:

    profileId - id of a profile
    newProfile - data to update

**Example**:

    profileId = "XXXXXXXXX"
    data = new HashMap<String,Object>();
    data.put("email", "test@test.com");

    client.updateByProfileId(profileId, data)

**Response**:

*Returns 204 response in case of successful request.*
