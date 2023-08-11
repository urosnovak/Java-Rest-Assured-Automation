package tests;

import common.requests.ReusableRequests;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GetSpecificUserTest extends ApiBaseTest{
    @Parameters({"baseUrl", "usersEndpoint"})
    @Test
    public void getSpecificUser(String baseUrl, String usersEndpoint){
        Response response = ReusableRequests.sendGetRequest(baseUrl + usersEndpoint + "5");
        validateStatusCode(response, 200);
        validatePropertyIsInteger(response.path("id"));
        validatePropertyIsString(response, "userName");
        validatePropertyIsString(response, "password");
        validateResponseContainsProperties(response, "", "id", "userName", "password");
        validateResponseDoesNotContainProperties(response, "", "neki property", "jos jedan");

        logAssertionErrorsList();
    }
}
