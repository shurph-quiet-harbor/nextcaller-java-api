package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import java.util.Map;

public class FraudExample {
    String username = "{% api_username %}";
    String password = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(username, password);

    public void example() throws Exception {
        String phone = "2125558383";
        String accountId = "test";

        Map<String, Object> response = client.getFraudLevel(phone, accountId);
    }
}
