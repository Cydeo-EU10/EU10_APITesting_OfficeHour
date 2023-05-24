package week3;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import week2.*;

import java.util.*;

public class Junit5Features extends SpartanTestBase {

    /*
    using junit5, we can easily past data to methods
    we have 4 ways to do this
    value source
    method source
    csv source
    csv file source
     */

    @ParameterizedTest
    @ValueSource(ints = {7,9,11,12,13})
    public void test1(int id){
        Response response =  RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",id)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();
    }

    public static List<Integer> generateNumber(){
        return Arrays.asList(31,32,33);
    }




    @ParameterizedTest
    @MethodSource("generateNumber")
    public void test2(int id){
        Response response =  RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",id)
                .when().get("/api/spartans/{id}");

        response.prettyPrint();
    }


    @ParameterizedTest
    @CsvSource({"20,Lothario",
                "21, Mark",
                "22, Koenraad"})
    public void nameId(String id, String name){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id",id)
                .when().get("/api/spartans/{id}");

        Assertions.assertEquals(name, response.path("name"));
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/SpartanDataPOST.csv",numLinesToSkip = 1)
    public void addSpartanTest(String name, String gender, Long phone){

        Map<String,Object> spartanMap = new HashMap<>();
        spartanMap.put("name", name);
        spartanMap.put("gender", gender);
        spartanMap.put("phone", phone);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartanMap)
                .when().post("/api/spartans")
                .then().statusCode(201)
                .extract().response();

        Assertions.assertEquals("A Spartan is Born!",response.path("success"));


    }





}
