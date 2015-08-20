package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import java.util.Map;

public class GetByEmailExample {
    String username = "{% api_username %}";
    String password = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(username, password);

    public void example() throws Exception {
        String email = "email@exmaple.com";
        String accountId = "test";

        Map<String, Object> response = client.getByEmail(email, accountId);
    }
}
