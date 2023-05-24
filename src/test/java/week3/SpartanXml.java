package week3;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.path.xml.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;
import week2.*;

public class SpartanXml extends SpartanTestBase {

    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.XML)
                .when().get("/api/spartans");

//        response.prettyPrint();

        XmlPath xmlPath = response.xmlPath();
        String name = xmlPath.getString("List.item[0].name");
        System.out.println(name);
    }
}
