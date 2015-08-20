package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import java.util.HashMap;
import java.util.Map;

public class UpdateByProfileIdExample {
    String username = "{% api_username %}";
    String password = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(username, password);

    public void example() throws Exception {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";
        Map<String, Object> profileData = new HashMap<String, Object>(){{
            put("first_name", "Clark");
            put("last_name", "Kent");
        }};
        String accountId = "test";

        boolean success = client.updateByProfileId(profileId, profileData, accountId);
    }
}
