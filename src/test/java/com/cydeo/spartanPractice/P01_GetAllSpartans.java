package com.cydeo.spartanPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_GetAllSpartans {
    @Test
    public void getAllSparans(){
        Response response = RestAssured.get("http://18.212.176.61:8000/api/spartans");

        //Status code
        System.out.println(response.statusCode());
        Assertions.assertEquals(200, response.statusCode());

        //Content type
        System.out.println(response.contentType());
        Assertions.assertEquals("application/json", response.contentType());

        //Print body
        System.out.println(response.prettyPrint());

        // As string

        System.out.println(response.asString());
    }

}
