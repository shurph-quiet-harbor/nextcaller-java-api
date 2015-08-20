package business;

import com.nextcaller.integration.client.NextCallerClient;
import java.util.Map;

public class GetByEmailExample {
    String username = "{% api_username %}";
    String password = "{% api_password %}";

    NextCallerClient client = new NextCallerClient(username, password);

    public void example() throws Exception {
        String email = "email@exmaple.com";

        Map<String, Object> response = client.getByEmail(email);
    }
}
