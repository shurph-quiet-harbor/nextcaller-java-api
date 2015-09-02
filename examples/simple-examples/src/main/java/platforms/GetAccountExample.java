package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import java.util.Map;

public class GetAccountExample {
    String apiUsername = "{% api_username %}";
    String apiPassword = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(apiUsername, apiPassword);

    public void example() throws Exception {
        String accountId = "test";

        Map<String, Object> account = client.getPlatformAccount(accountId);
    }
}
