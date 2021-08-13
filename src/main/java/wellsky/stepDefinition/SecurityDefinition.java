package wellsky.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import wellsky.apiRestTest.utils.ConfigurationConstants;
import wellsky.apiRestTest.utils.HttpHeaderContants;
import wellsky.apiRestTest.utils.HttpStatusCodeConstants;

public class SecurityDefinition {

    private static Response response;
    private static RequestSpecification request;

    private static String jsonString;

    @Given("An authentication Services")
    public void an_authentication_services() {
        RestAssured.baseURI = ConfigurationConstants.BASE_URL;
        request = RestAssured.given();
        request = request.header(HttpHeaderContants.CONTENT_TYPE, HttpHeaderContants.APLICATION_JSON);
    }

    @When("I send username {string} and password {string}")
    public void i_send_username_and_password(String string, String string2) {
        response = request.body("{ \"userName\":\"" + string + "\", \"password\":\"" + string2 + "\"}")
                .post("/Account/v1/GenerateToken");
        jsonString = response.asString();
    }

    @Then("The services response success")
    public void the_services_response_success() {
        Assert.assertEquals(HttpStatusCodeConstants.CODE_SUCCESSFULL, response.getStatusCode());
    }

    @Then("Generate a new Token")
    public void generate_a_new_token() {
        String token = JsonPath.from(jsonString).get("token");
        Assert.assertNotNull(token);
    }


    @Then("The services response with error")
    public void the_services_response_with_error() {
        String statusRes = JsonPath.from(jsonString).get("status");
        Assert.assertEquals("Failed",statusRes);
    }

    @Then("Dont generate any token")
    public void dont_generate_any_token() {
        String token = JsonPath.from(jsonString).get("token");
        Assert.assertNull(token);
    }

}
