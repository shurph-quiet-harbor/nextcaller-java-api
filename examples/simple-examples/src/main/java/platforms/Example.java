package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;

public class Example {
    String apiUsername = "{% api_username %}";
    String apiPassword = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(apiUsername, apiPassword);
}
