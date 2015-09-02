package business;

import com.nextcaller.integration.client.NextCallerClient;
import java.util.HashMap;
import java.util.Map;

public class UpdateByProfileIdExample {
    String apiUsername = "{% api_username %}";
    String apiPassword = "{% api_password %}";

    NextCallerClient client = new NextCallerClient(apiUsername, apiPassword);

    public void example() throws Exception {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";
        Map<String, Object> profileData = new HashMap<String, Object>(){{
            put("first_name", "Clark");
            put("last_name", "Kent");
        }};
        String accountId = "test";

        boolean success = client.updateByProfileId(profileId, profileData);
    }
}
