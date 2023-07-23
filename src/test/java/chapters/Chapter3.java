//put post and delete request examples
package chapters;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Chapter3 {

    @Test
    public void post_request() {
        File filename = new File("resources/create_employee.json");
        String age =
        given()
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .contentType(ContentType.JSON)
                .body(filename).
        when()
                .post("/create").
        then()
                .statusCode(200)
                .body("data.name", equalTo("Mike"))
                .extract().path("data.age");

        System.out.println(age);

    }

    @Test
    public void post_request_with_json() {
        JSONObject body = new JSONObject();
        body.put("name", "Tim");
        body.put("salary", "5843");
        body.put("age", "20");
        String age =
                given()
                    .baseUri("https://dummy.restapiexample.com/api/v1")
                    .contentType(ContentType.JSON)
                    .body(body.toString()).
                when()
                    .post("/create").
                then()
                    .statusCode(200)
                    .body("data.name", equalTo("Tim"))
                    .extract().path("data.age");

        System.out.println(age);
    }

    @Test
    public void put_request_with_json() {
        JSONObject body = new JSONObject();
        body.put("name", "Tim");
        body.put("salary", "5843");
        body.put("age", "20");
        given()
            .baseUri("https://dummy.restapiexample.com/api/v1")
            .contentType(ContentType.JSON)
            .body(body.toString()).
        when()
            .put("/update/10").
        then()
            .statusCode(200);
    }

    @Test
    public void delete_record() {
        String msg =
            given().baseUri("https://dummy.restapiexample.com/api/v1")
                    .contentType(ContentType.JSON).
            when()
                    .delete("/delete/10").
            then()
                    .statusCode(200)
                    .extract().path("message");
        System.out.println(msg);
    }
}
