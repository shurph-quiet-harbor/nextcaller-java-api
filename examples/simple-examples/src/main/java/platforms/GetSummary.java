package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import java.util.Map;

public class GetSummary {
    String username = "{% api_username %}";
    String password = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(username, password);

    public void example() throws Exception {
        int page = 1;

        Map<String, Object> accounts = client.getPlatformStatistics(page);
    }
}
