package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import java.util.Map;

public class GetAccountExample {
    String username = "{% api_username %}";
    String password = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(username, password);

    public void example() throws Exception {
        String accountId = "test";

        Map<String, Object> account = client.getPlatformAccount(accountId);
    }
}
