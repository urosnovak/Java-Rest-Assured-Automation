package tests;

import common.requests.ReusableRequests;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GetActivity extends ApiBaseTest{
    @Parameters({"baseUrl", "activityEndpoint"})
    @Test
    public void GetUserActivity(String baseUrl, String activityEndpoint){
        Response response = ReusableRequests.sendGetRequest(baseUrl + activityEndpoint+"3");

        validateStatusCode(response,200);
        validatePropertyIsInteger(response.path("id"));
        validatePropertyIsString(response,"title");
        validatePropertyIsString(response, "dueDate");
        validatePropertyIsBoolean(response, "completed");
        logAssertionErrorsList();
    }
}
