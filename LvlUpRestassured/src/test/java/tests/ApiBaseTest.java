package tests;

import common.methods.CommonMethods;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static org.hamcrest.Matchers.lessThan;

public class ApiBaseTest extends CommonMethods {
    @BeforeSuite
    public void setup(){
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectResponseTime(lessThan(7000L)).build();
        RestAssured.responseSpecification = responseSpecification;
    }

    @BeforeMethod
    public void setupTest(){
        assertionErrors.clear();
    }
}
