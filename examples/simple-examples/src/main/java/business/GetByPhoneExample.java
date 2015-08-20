package business;

import com.nextcaller.integration.client.NextCallerClient;
import java.util.Map;

public class GetByPhoneExample {
    String username = "{% api_username %}";
    String password = "{% api_password %}";

    NextCallerClient client = new NextCallerClient(username, password);

    public void example() throws Exception {
        String phone = "2125558383";

        Map<String, Object> response = client.getByPhone(phone);
    }
}
