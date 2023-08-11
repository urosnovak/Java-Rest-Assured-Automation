package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.payloads.UserObject;
import common.requests.ReusableRequests;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PostNewUserTest extends ApiBaseTest{
    @Parameters({"baseUrl", "usersEndpoint"})
    @Test
    public void createNewUSer(String baseUrl, String usersEndpoint){
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 11);
        requestBody.put("userName", "randomUser");
        requestBody.put("password", "12345");

        Response response = ReusableRequests.sendPostRequest(baseUrl + usersEndpoint, requestBody);

        validateStatusCode(response, 200);

        logAssertionErrorsList();
    }

    @Parameters({"baseUrl", "usersEndpoint"})
    @Test
    public void createNewUSerWithPojoClass(String baseUrl, String usersEndpoint) throws JsonProcessingException {
        UserObject userObject = new UserObject();
        userObject.setId(12);
        userObject.setUserName("Pojo User");
        userObject.setPassword("sifra");

        Response response = ReusableRequests.sendPostRequestWithUserObject(baseUrl + usersEndpoint, userObject);

        validateStatusCode(response, 200);

        logAssertionErrorsList();
    }
}
