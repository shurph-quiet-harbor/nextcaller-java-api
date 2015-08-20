package business;

import com.nextcaller.integration.client.NextCallerClient;

public class Example {
    String username = "{% api_username %}";
    String password = "{% api_password %}";

    NextCallerClient client = new NextCallerClient(username, password);
}
