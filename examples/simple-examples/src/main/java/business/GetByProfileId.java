package business;

import com.nextcaller.integration.client.NextCallerClient;
import java.util.Map;

public class GetByProfileId {
    String username = "{% api_username %}";
    String password = "{% api_password %}";

    NextCallerClient client = new NextCallerClient(username, password);

    public void example() throws Exception {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";

        Map<String, Object> profile = client.getByProfileId(profileId);
    }
}
