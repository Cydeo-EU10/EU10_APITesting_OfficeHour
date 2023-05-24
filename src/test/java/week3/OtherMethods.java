package week3;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import week2.*;
import week2.POJO.*;

import java.util.*;

import static io.restassured.RestAssured.given;

public class OtherMethods extends SpartanTestBase {


    /*
    postman collection:
    https://api.postman.com/collections/14823187-c18d5c5e-0b8a-4d16-9725-98cc9aaf7588?access_key=PMAT-01H0NGA414Y4M9QDE5BJMXT9DP

     */

    // post method
    @Test
    public void test1(){
        /*
        3 ways to provide body to api
        1. as String
        2. as Map
        3. as POJO
         */

        // string
        String bodyString = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Hanks\",\n" +
                "  \"phone\": 1236547895\n" +
                "}";

        // map
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name","Jeniffer");
        bodyMap.put("gender","Female");
        bodyMap.put("phone",1236547895L);

        // pojo or java object
        SingleSpartan bodyPojo = new SingleSpartan();
        bodyPojo.setName("Jack");
        bodyPojo.setGender("Male");
        bodyPojo.setPhone(5468792135L);

        Response response = given().accept(ContentType.JSON) // telling api that we only receive json format as response
                .and().contentType(ContentType.JSON)// telling api that the data we sent is json format
                .and().body(bodyPojo)
                .when().post("/api/spartans");

        Assertions.assertEquals(201,response.statusCode());

    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().delete("/api/spartans/8");

        Assertions.assertEquals(204,response.statusCode());
    }






}
