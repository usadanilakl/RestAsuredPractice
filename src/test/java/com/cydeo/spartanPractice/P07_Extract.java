package com.cydeo.spartanPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;

import static io.restassured.RestAssured.*;

public class P07_Extract {
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://18.212.176.61:8000";
    }
    @Test
    public void extractResponse(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 3)
                .when().get("/api/spartans/{id}")
                .then().assertThat()
                .statusCode(200)
                .body("id", Matchers.is(3))
                .extract().response();

        int id = response.path("id");
        System.out.println(response.path("name")+" id: "+id);

    }
    @Test
    public void extractJson(){
        JsonPath jsonpath = given().accept(ContentType.JSON)
                .pathParam("id", 3)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        System.out.println("jsonpath.getString(\"name\") = " + jsonpath.getString("name"));
    }
}
