package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import java.util.Map;

public class GetByProfileId {
    String apiUsername = "{% api_username %}";
    String apiPassword = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(apiUsername, apiPassword);

    public void example() throws Exception {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";
        String accountId = "test";

        Map<String, Object> response = client.getByProfileId(profileId, accountId);
    }
}
