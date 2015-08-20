package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import java.util.HashMap;
import java.util.Map;

public class GetByAddressNameExample {
    String username = "{% api_username %}";
    String password = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(username, password);

    public void example() throws Exception {
        Map<String, String> addressNameData = new HashMap<String, String>(){{
            put("first_name", "Jerry");
            put("last_name", "Seinfeld");
            put("address", "129 West 81st Street");
            put("zip_code", "10024");
        }};
        String accountId = "test";

        Map<String, Object> response = client.getByAddressName(addressNameData, accountId);
    }
}
