/*
Basic Tests for any API
validate Json and XML response
Validate status codes and status lines
Fetch API responses
 */
package starting_pack;

import io.restassured.response.Response;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TrialGetRequest {

    @Test @Ignore
    public void simple_get_request() {
        given()
                .baseUri("https://gorest.co.in/public/v2").
        when()
                .get("/users").
        then()
                .statusCode(200);
    }

    @Test @Ignore
    public void validate_json_response() {
        given()
            .baseUri("https://gorest.co.in/public/v2").
        when()
            .get("/users").
        then()
            .statusCode(200)
            .body("[0].id", equalTo(3826988));
    }

    @Test @Ignore
    public void extract_response_data() {
        Response res = given()
            .baseUri("https://gorest.co.in/public/v2").
        when()
            .get("/users").
        then()
            .extract().response();
        System.out.println(res.asString());
    }

    @Test @Ignore
    public void extract_single_response_data() {
        int res = given()
                .baseUri("https://gorest.co.in/public/v2").
                when()
                .get("/users").
                then()
                .extract().path("[0].id");
        System.out.println(res);
    }

    @Test
    public void verify_status_line() {
        given()
            .baseUri("https://api.openweathermap.org/data/2.5")
            .queryParam("q", "London,uk")
            .queryParam("APPID", "3f7393c569d314987f3b072d260d7142")
            .queryParam("mode", "xml").
        when()
            .get("/weather")
        .then()
            .statusLine("HTTP/1.1 401 Unauthorized");
    }

}
