package business;

import com.nextcaller.integration.client.NextCallerClient;
import java.util.Map;

public class SandboxExample {
    String username = "{% api_username %}";
    String password = "{% api_password %}";
    boolean sandbox = true;

    NextCallerClient client = new NextCallerClient(username, password, sandbox);

    public void example() throws Exception {
        String phone = "2125558383";

        Map<String, Object> response = client.getByPhone(phone); // using Sandbox
    }
}
