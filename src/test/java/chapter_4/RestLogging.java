package chapter_4;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class RestLogging {

    @Test
    public void log_all_usage(){
        given().
                baseUri("https://gorest.co.in/public/v2").
        when().
                get("/users").
        then().
                statusCode(200).
                log().all();
    }

    @Test
    public void log_body_header(){
        given().
                baseUri("https://gorest.co.in/public/v2").
        when().
                get("/users").
        then().
                statusCode(200).
                log().cookies();
//                log().status();
//                log().headers();
//                log().body();

    }

    @Test
    public void log_if_error() {
        given()
                .baseUri("https://api.openweathermap.org/data/2.5")
                .queryParam("q", "London,uk")
                .queryParam("APPID", "3f7393c569d314987f3b072d260d7142")
                .queryParam("mode", "xml").
        when()
                .get("/weather").
        then().
                log().ifError(); //logs error if response code is between 400 to 599 i.e client/server error
    }

    @Test
    public void log_if_validation_fails() {
        given()
            .baseUri("https://api.openweathermap.org/data/2.5")
            .queryParam("q", "London,uk")
            .queryParam("APPID", "3f7393c569d314987f3b072d260d7142")
            .queryParam("mode", "xml").
        when()
            .get("/weather").
        then().
            log().ifValidationFails()//logs only if the following validations fail
            .statusCode(401);
    }
}
