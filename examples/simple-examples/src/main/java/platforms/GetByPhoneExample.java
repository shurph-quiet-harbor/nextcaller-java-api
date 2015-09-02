package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import java.util.Map;

public class GetByPhoneExample {
    String apiUsername = "{% api_username %}";
    String apiPassword = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(apiUsername, apiPassword);

    public void example() throws Exception {
        String phone = "2125558383";
        String accountId = "test";

        Map<String, Object> response = client.getByPhone(phone, accountId);
    }
}
