package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickStartGuide {
    String username = "{% api_username %}";
    String password = "{% api_password %}";
    boolean sandbox = true;

    PlatformNextCallerClient client = new PlatformNextCallerClient(username, password, sandbox);

    public void example() throws Exception {
        // please, see next two steps

        //region Step 2
        String phone = "2125558383";
        String accountId = "test";

        Map<String, Object> response = client.getByPhone(phone, accountId);
        //endregion

        //region Step 3
        Map<String, Object> profile = (Map<String, Object>) ((List) response.get("records")).get(0);
        Map<String, Object> address = (Map<String, Object>) ((List) profile.get("address")).get(0);

        String profileId = (String) profile.get("id");
        String firstName = (String) profile.get("first_name"); // "Jerry"
        String middleName = (String) profile.get("middle_name"); // "Allen"
        String lastName = (String) profile.get("last_name"); // "Seinfeld"
        String city = (String) address.get("city"); // "New York"
        String addressLine1 = (String) address.get("line1"); // "129 West 81st Street"
        //endregion
    }

    // please, see steps 4 and 5

    //region Step 4
    public void update() throws Exception {
        String profileId = "97d949a413f4ea8b85e9586e1f2d9a";
        Map<String, Object> profileData = new HashMap<String, Object>(){{
            put("first_name", "Clark");
            put("last_name", "Kent");
        }};
        String accountId = "test";

        boolean success = client.updateByProfileId(profileId, profileData, accountId);
    }
    //endregion

    //region Step 5
    public void getSummary() throws Exception {
        int page = 1;
        Map<String, Object> response = client.getPlatformStatistics(page);
    }

    public void singleAccountSummary() throws Exception {
        String accountId = "test";
        Map<String, Object> response = client.getPlatformAccount(accountId);
    }
    //endregion
}
