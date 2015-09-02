package platforms;

import com.nextcaller.integration.client.PlatformNextCallerClient;
import java.util.Map;

public class GetSummary {
    String apiUsername = "{% api_username %}";
    String apiPassword = "{% api_password %}";

    PlatformNextCallerClient client = new PlatformNextCallerClient(apiUsername, apiPassword);

    public void example() throws Exception {
        int page = 1;

        Map<String, Object> accounts = client.getPlatformStatistics(page);
    }
}
