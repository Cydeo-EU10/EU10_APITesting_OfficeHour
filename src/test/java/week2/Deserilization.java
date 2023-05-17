package week2;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import week2.POJO.*;

import java.util.*;

public class Deserilization extends SpartanTestBase{


    /*
    Json --> Java  :  Deserialization
    Java --> Json  :  Serialization
     */


    // json --> map
    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans/7");

        Map<String, Object> spartan7 = response.as(Map.class);
        System.out.println(spartan7);
    }

    // json --> list
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        List<Map<String,Object>> allSpartans = response.as(List.class);
        System.out.println(allSpartans);

        Assertions.assertEquals("Rodolfo",allSpartans.get(2).get("name"));
    }


    // json --> POJO (CUSTOM CLASS OBJECT)
    @Test
    public void test3(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans/7");

        SingleSpartan spartan7 = response.as(SingleSpartan.class);
        System.out.println(spartan7);
    }

    @Test
    public void test4(){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("nameContains","da");
        queryParams.put("gender","Female");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(queryParams)
                .when().get("http://3.216.30.92:8000/api/spartans/search");

        SearchSpartan spartan = response.as(SearchSpartan.class);
        System.out.println(spartan);


    }
}
