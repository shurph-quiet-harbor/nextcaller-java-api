package business;

import com.nextcaller.integration.client.NextCallerClient;
import java.util.HashMap;
import java.util.Map;

public class GetByNameAddressExample {
    String apiUsername = "{% api_username %}";
    String apiPassword = "{% api_password %}";

    NextCallerClient client = new NextCallerClient(apiUsername, apiPassword);

    public void example() throws Exception {
        Map<String, String> nameAddressData = new HashMap<String, String>(){{
            put("first_name", "Jerry");
            put("last_name", "Seinfeld");
            put("address", "129 West 81st Street");
            put("zip_code", "10024");
        }};

        Map<String, Object> response = client.getByNameAddress(nameAddressData);
    }
}
