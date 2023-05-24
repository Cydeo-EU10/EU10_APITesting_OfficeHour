package week3;

import io.restassured.http.*;
import io.restassured.module.jsv.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import week2.*;

import static io.restassured.RestAssured.given;

public class SchemaValidation extends SpartanTestBase {

    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .when().get("/api/spartans/915")
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("singleSpatanSchema.json"));
    }



}
