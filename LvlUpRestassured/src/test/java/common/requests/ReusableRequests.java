package common.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import common.payloads.UserObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ReusableRequests {

    public static Response sendGetRequest(String endpoint){
        Response response = given()
                .header("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then().log().all()
                .extract().response();
        return response;
    }

    public static Response sendPostRequest(String endpoint, JSONObject requestBody){
        Response response = given()
                .header("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post(endpoint)
                .then().log().all()
                .extract().response();
        return response;
    }

    public static Response sendPostRequestWithUserObject(String endpoint, UserObject userPayload) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String jsonString = mapper.writeValueAsString(userPayload);
        System.out.println("Payload is: " + jsonString);
        Response response = given()
                .header("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonString)
                .when()
                .post(endpoint)
                .then().log().all()
                .extract().response();
        return response;
    }
}
