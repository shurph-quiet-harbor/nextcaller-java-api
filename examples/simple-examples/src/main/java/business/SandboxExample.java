package business;

import com.nextcaller.integration.client.NextCallerClient;
import java.util.Map;

public class SandboxExample {
    String apiUsername = "{% api_username %}";
    String apiPassword = "{% api_password %}";
    boolean sandbox = true;

    NextCallerClient client = new NextCallerClient(apiUsername, apiPassword, sandbox);

    public void example() throws Exception {
        String phone = "2125558383";

        Map<String, Object> response = client.getByPhone(phone); // using Sandbox
    }
}
