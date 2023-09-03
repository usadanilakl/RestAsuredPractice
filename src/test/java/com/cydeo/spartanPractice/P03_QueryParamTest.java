package com.cydeo.spartanPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P03_QueryParamTest {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://18.212.176.61:8000";
    }
    @Test
    public void queryParamTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParam("nameContains", "J")
                .queryParam("gender", "Female")
                .get("/api/spartans/search");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType());
        response.prettyPrint();
        Assertions.assertTrue(response.asString().contains("Female") && response.asString().contains("J"));

    }
}
