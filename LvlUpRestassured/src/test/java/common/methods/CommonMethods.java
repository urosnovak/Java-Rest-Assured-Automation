package common.methods;

import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommonMethods {
    protected List<Throwable> assertionErrors = new ArrayList<>();
    public void validateStatusCode(Response response, int expectedStatusCode){
        try{
            assertThat("Validate status code is: " + expectedStatusCode, response.getStatusCode(), is(expectedStatusCode));
        } catch (Throwable errors){
            assertionErrors.add(errors);
        }
    }

    public void validatePropertyIsInteger(int id){
        try {
            assertThat(id , instanceOf(Integer.class));
        } catch (Throwable error){
            assertionErrors.add(error );
        }
    }

    public void validatePropertyInsideArrayIsInteger(Response response, String jsonPath){
        List<Integer> allIDs = response.jsonPath().getList(jsonPath);
        for(Integer id : allIDs){
            validatePropertyIsInteger(id);
        }
    }

    public void validatePropertyIsString(Response response, String jsonPath){
        try {
            assertThat(response.path(jsonPath), instanceOf(String.class));
        } catch (Throwable error){
            assertionErrors.add(error);
        }
    }

    public void logAssertionErrorsList(){
        if (assertionErrors.size() > 0){
            System.out.printf("%s errors found in %s%n", assertionErrors.size(), this.getClass().getSimpleName());
            for(int i = 0; i < assertionErrors.size(); i++){
                System.out.println(assertionErrors.get(i).getMessage());
            }
            Assert.fail(Arrays.toString(assertionErrors.stream().map(x -> "\r\n" + x.getMessage()).toArray()));
        } else{
            System.out.printf("There were no assertion errors for %s", this.getClass().getSimpleName());
        }
    }

    public void validateResponseContainsProperties (Response response, String jsonPath, String... property){
        try{
            for(String mandatoryProperty : property){
                assertThat(response.path(jsonPath), hasKey(mandatoryProperty));
            }
        } catch (Throwable error){
            assertionErrors.add(error);
        }
    }

    public void validateResponseDoesNotContainProperties (Response response, String jsonPath, String... property){
        try{
            for(String mandatoryProperty : property){
                assertThat(response.path(jsonPath), not(hasKey(mandatoryProperty)));
            }
        } catch (Throwable error){
            assertionErrors.add(error);
        }
    }
    public void validatePropertyIsBoolean (Response response, String jsonPath){
        try {
        assertThat(response.path(jsonPath), instanceOf(Boolean.class));
    } catch (Throwable error){
        assertionErrors.add(error);
    }
    }
}
