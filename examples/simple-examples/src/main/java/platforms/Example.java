package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;

public class Example {
    String username = "{% api_username %}";
    String password = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(username, password);
}
