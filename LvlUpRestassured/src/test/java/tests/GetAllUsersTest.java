package tests;

import common.requests.ReusableRequests;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GetAllUsersTest extends ApiBaseTest {
    @Parameters({"baseUrl", "usersEndpoint"})
    @Test
    public void getUsersTest(String baseUrl, String usersEndpoint) {
        Response response = ReusableRequests.sendGetRequest(baseUrl + usersEndpoint);

        validateStatusCode(response, 200);
        validatePropertyInsideArrayIsInteger(response, "id");

        logAssertionErrorsList();
    }
}
