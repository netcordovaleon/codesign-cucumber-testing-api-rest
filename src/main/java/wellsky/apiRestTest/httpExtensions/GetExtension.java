package wellsky.apiRestTest.httpExtensions;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class GetExtension extends  HttpAbstractExtension {

    public void ValidatePropertyValue(String uri, String propertyString, int spectValue) {
        get(uri).then().body(propertyString, equalTo(spectValue));
    }

    public void ValidatePropertyValue(String uri, String propertyString, String spectValue) {
        get(uri).then().body(propertyString, equalTo(spectValue));
    }

    public void ValidatePropertyValue(String uri, String propertyString, boolean spectValue) {
        get(uri).then().body(propertyString, equalTo(spectValue));
    }
}
