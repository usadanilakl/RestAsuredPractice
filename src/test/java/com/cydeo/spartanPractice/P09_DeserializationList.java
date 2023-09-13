package com.cydeo.spartanPractice;

import com.cydeo.utils.BaseUri;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class P09_DeserializationList {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://18.212.176.61:8000";
    }
    @Test
    public void makeList(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().assertThat().statusCode(200)
                .extract().response();

        List<Map<String,Object>> allSparts = response.as(List.class);
        System.out.println("allSparts.get(2).get(\"name\") = " + allSparts.get(2).get("name"));

    }
}
