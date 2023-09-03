package com.cydeo.spartanPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P02_PathParameter {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://18.212.176.61:8000";
    }
    @Test
    public void pathParamTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",3).when().get("/api/spartans/{id}");
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertTrue(response.asString().contains("Fidole"));

        response.prettyPrint();
    }
}
