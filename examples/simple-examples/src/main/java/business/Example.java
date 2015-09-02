package business;

import com.nextcaller.integration.client.NextCallerClient;

public class Example {
    String apiUsername = "{% api_username %}";
    String apiPassword = "{% api_password %}";

    NextCallerClient client = new NextCallerClient(apiUsername, apiPassword);
}
