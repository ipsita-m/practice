/*
Create HashMAp to add multiple Query Parameters
Multi value parameters can be passed as semicolon or comma separated values based on how th eAPI is designed
 */
package chapters;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RequestParametersChapter5 {

    @Test
    public void single_query_param() {
        given()
                .baseUri("https://gorest.co.in/public/v2")
                .queryParam("gender","female").
        when()
                .get("/users").
        then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void multiple_query_params() {
        HashMap<String, Object> params  = new HashMap<String, Object>();
        params.put("gender","female");
        params.put("status", "active");

        given()
                .baseUri("https://gorest.co.in/public/v2")
                .queryParams(params).
        when()
                .get("/users").
        then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void multi_value_query_params() {
        given()
                .baseUri("https://gorest.co.in/public/v2")
                .queryParam("gender","male")
                .queryParam("status","active;inactive").
        when()
                .get("/users").
        then()
                .log().all()
                .statusCode(200);
    }

    @Test //Test may not run its just an example
    public void path_params() {
        given()
                .baseUri("https://gorest.co.in/public/v2")
                .pathParam("gender", "female").
        when()
                .get("/users/{gender}").
        then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void handling_form_data() {
        // Can use HashMap for multiple data
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/x-www-form-urlencoded;charset=UFT-8")
                .formParam("First Name", "abc")
                .formParam("Last Name", "xyz").
        when()
                .post("/post").
        then()
                .log().all()
                .statusCode(200);

    }
}
