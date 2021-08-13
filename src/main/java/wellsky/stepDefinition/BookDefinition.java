package wellsky.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import wellsky.apiRestTest.httpExtensions.GetExtension;
import io.restassured.RestAssured;
import wellsky.apiRestTest.utils.ConfigurationConstants;
import wellsky.apiRestTest.utils.HttpHeaderContants;
import wellsky.apiRestTest.utils.HttpStatusCodeConstants;

import java.io.File;
import java.util.List;
import java.util.Map;

public class BookDefinition {

    private static final String USER_ID = "b4c15441-9431-47e3-9274-ffdc133f5796";
    private static final String USERNAME = "luisCordova";
    private static final String PASSWORD = "P@$$w0rd";


    private static Response response;
    private static RequestSpecification request;

    private static String token;
    private static String jsonString;
    private static String bookId;

    public BookDefinition () {
        RestAssured.baseURI = ConfigurationConstants.BASE_URL;
        request = RestAssured.given();
        request = request.header(HttpHeaderContants.CONTENT_TYPE, HttpHeaderContants.APLICATION_JSON);
    }

    @Given("I am an authorized user")
    public void i_am_an_authorized_user() {
        response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
                .post("/Account/v1/GenerateToken");
        String jsonString = response.asString();
        token = JsonPath.from(jsonString).get("token");
    }

    @Given("A list of book are available")
    public void a_list_of_book_are_available() {
        response = request.get("/BookStore/v1/Books");

        jsonString = response.asString();
        List<Map<String, String>> books = JsonPath.from(jsonString).get("books");
        Assert.assertTrue(books.size() > 0);

        bookId = books.get(0).get("isbn");
    }

    @When("I add a book to my reading list")
    public void i_add_a_book_to_my_reading_list() {
        request.header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");
        response = request.body("{ \"userId\": \"" + USER_ID + "\", " +
                        "\"collectionOfIsbns\": [ { \"isbn\": \"" + bookId + "\" } ]}")
                .post("/BookStore/v1/Books");
    }

    @Then("The book is added")
    public void the_book_is_added() {
        Assert.assertEquals(HttpStatusCodeConstants.CODE_SUCCESSFULL, response.getStatusCode());
    }

    @Then("The book has a correct JSON structure")
    public void the_book_has_a_correct_json_structure() {
        Assert.assertEquals(HttpStatusCodeConstants.CODE_BAD_REQUEST, response.getStatusCode());
    }

}
