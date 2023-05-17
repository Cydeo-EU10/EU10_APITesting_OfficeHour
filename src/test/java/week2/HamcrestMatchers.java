package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class HamcrestMatchers extends SpartanTestBase {

    @Test
    public void test1() {
        RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans/7")
                .then().statusCode(200)
                .and().header("Content-Type", is("application/json"))
                .and().body("name", is("Hershel"),
                        "id", equalTo(7),
                        "gender", is(notNullValue()));
    }

    @Test
    public void test2(){
        JsonPath jsonPath = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans/7")
                .then().statusCode(200)
                .and().header("Content-Type", is("application/json"))
                .and().body("name", is("Hershel"))
                .extract().jsonPath();

        assertThat(jsonPath.getString("name"),is("Hershel"));



    }
}
