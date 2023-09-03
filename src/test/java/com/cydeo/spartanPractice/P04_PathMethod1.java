package com.cydeo.spartanPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*; // static import allows to avoid using RestAssured class every time when calling methods
import static org.junit.jupiter.api.Assertions.*;

public class P04_PathMethod1 {
    @BeforeAll
    public static void init() {
        baseURI = "http://18.212.176.61:8000";
    }

    @Test
    void pathMethod() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 3)
                .when().get("/api/spartans/{id}");
        response.prettyPrint();

        assertEquals(200, response.statusCode() );
        assertEquals(ContentType.JSON.toString(), response.contentType());

        int id = response.path("id");
        System.out.println("id = " + id);
        assertEquals(3, (Integer) response.path("id"));

        String name = response.path("name");
        System.out.println("name = " + name);
        assertEquals("Fidole", response.path("name"));

        String gender = response.path("gender");
        System.out.println("gender = " + gender);
        assertEquals("Male", response.path("gender"));

        long phone = response.path("phone");
        System.out.println("phone = " + phone);
        assertEquals(6105035231l, (Long)response.path("phone"));
    }

    @Test
    void getAllSpartansWithPath() {
        Response response = get("/api/spartans"); // it is an array that contains all information

        //get id of the first spartan
        int id = response.path("id[0]");
        System.out.println("id = " + id);

        //get name of the second spartan
        System.out.println("response.path(\"name[1]\") = " + response.path("name[1]"));

        //get gender of the last spartan
        System.out.println("response.path(\"gender[-1]\") = " + response.path("gender[-1]"));

        //get phone number of the second to the last spartan
        System.out.println("response.path(\"phone[-2]\") = " + response.path("phone[-2]"));
        System.out.println("response.path(\"phone[98]\") = " + response.path("phone[98]"));
        assertEquals((Long)response.path("phone[98]"), (Long)response.path("phone[-2]"));

        //get all names
        System.out.println("*****************************All names************************");
        System.out.println("response.path(\"name\") = " + response.path("name"));

        System.out.println("**************************** all id *****************************");
        List<Integer> allIds= response.path("id");
        System.out.println(allIds);

        System.out.println("----------------------------------------------"     );

    }
}
