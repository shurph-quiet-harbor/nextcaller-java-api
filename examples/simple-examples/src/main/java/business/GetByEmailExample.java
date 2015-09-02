package business;

import com.nextcaller.integration.client.NextCallerClient;
import java.util.Map;

public class GetByEmailExample {
    String apiUsername = "{% api_username %}";
    String apiPassword = "{% api_password %}";

    NextCallerClient client = new NextCallerClient(apiUsername, apiPassword);

    public void example() throws Exception {
        String email = "email@exmaple.com";

        Map<String, Object> response = client.getByEmail(email);
    }
}
