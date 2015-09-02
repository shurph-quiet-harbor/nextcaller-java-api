package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountExample {
    String apiUsername = "{% api_username %}";
    String apiPassword = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(apiUsername, apiPassword);

    public void example() throws Exception {
        Map<String, Object> accountData = new HashMap<String, Object>(){{
            put("id", "test");
            put("first_name", "Clark");
            put("last_name", "Kent");
        }};

        boolean success = client.createPlatformAccount(accountData);
    }
}
