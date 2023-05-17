package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

public class ResponseBodyValidation {


    // using path() method to get value from body
    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("http://3.216.30.92:8000/api/spartans/7");

        response.prettyPrint();
        Assertions.assertEquals("Hershel", response.path("name"));
        Assertions.assertEquals("5278678322", response.path("phone").toString());
    }


    // using jsonPath() object to get value from body

    @Test
    public void test2() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "da")
                .when().get("http://3.216.30.92:8000/api/spartans/search");

        System.out.println(response.statusCode());
        response.prettyPrint();

        String name1 = response.path("content[0].name");
        List<String> nameList = response.path("content.name");
        System.out.println(name1);
        System.out.println(nameList);


        JsonPath jsonPath = response.jsonPath();
        String name2 = jsonPath.getString("content[1].name");
        List<String> genders = jsonPath.getList("content.gender");
        System.out.println(name2);
        System.out.println(genders);
    }

    // queryParams method
    @Test
    public void test3() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("nameContains","da")
                .and().queryParam("gender","Female")
                .when().get("http://3.216.30.92:8000/api/spartans/search");

        response.prettyPrint();

        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("nameContains","da");
        queryParams.put("gender","Female");

        Response response2 = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(queryParams)
                .when().get("http://3.216.30.92:8000/api/spartans/search");

        response.prettyPrint();
    }
}
