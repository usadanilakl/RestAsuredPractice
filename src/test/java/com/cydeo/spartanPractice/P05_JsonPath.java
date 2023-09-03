package com.cydeo.spartanPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P05_JsonPath {
    @BeforeAll
    public static void init() {
        baseURI = "http://18.212.176.61:8000";
    }

    @Test
    public void singleSpartanWithJsonPath() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 3)
                .when().get("/api/spartans/{id}");
        assertEquals(200, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());

        JsonPath jsonPath = response.jsonPath();
        System.out.println("jsonPath.getInt(\"id\") = " + jsonPath.getInt("id"));
        System.out.println("jsonPath.getString(\"name\") = " + jsonPath.getString("name"));
        System.out.println("jsonPath.getString(\"gender\") = " + jsonPath.getString("gender"));
        System.out.println("jsonPath.getLong(\"phone\") = " + jsonPath.getLong("phone"));
    }

    @Test
    void allSpartansWithJsonPath() {
        Response response = get("/api/spartans");
        JsonPath jsonPath = response.jsonPath();
        System.out.println("jsonPath.getInt(\"id[0]\") = " + jsonPath.getInt("id[0]"));
        System.out.println("jsonPath.getString(\"name[1]\") = " + jsonPath.getString("name[1]"));
        System.out.println("jsonPath.getString(\"gender[-1]\") = " + jsonPath.getString("gender[-1]"));
        System.out.println("jsonPath.getLong(\"phone[-2]\") = " + jsonPath.getLong("phone[-2]"));
        System.out.println("jsonPath.getString(\"name\") = " + jsonPath.getString("name"));
        List<Integer> ids = jsonPath.getList("id");
    }
}
