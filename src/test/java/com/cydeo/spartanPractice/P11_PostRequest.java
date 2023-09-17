package com.cydeo.spartanPractice;

import com.cydeo.utils.BaseUri;
import com.cydeo.utils.Spartan;
import groovy.json.JsonOutput;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P11_PostRequest extends BaseUri {

    @Test
    public void createWithString() {
        String req = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Dan\",\n" +
                "  \"phone\": 12111125140\n" +
                "}";
        JsonPath j = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(req)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .extract().jsonPath();
        String a = j.getString("");
        System.out.println(a);
        Assertions.assertEquals("A Spartan is Born!",j.getString("success"));
        Assertions.assertEquals(104, j.getInt("data.id"));
        Assertions.assertEquals(j.getString("data.gender"),"Male");
    }

    @Test
    public void createWithMap() {
        Map<String, Object> sp = new HashMap<>(Map.of(
                "gender", "Female",
                "name", "Dana",
                "phone", "021344576467"));

        JsonPath j = given().accept(ContentType.JSON)
                .contentType("application/json")
                .body(sp)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .extract().jsonPath();
       // Assertions.assertEquals(105, j.getInt("data.id"));
        System.out.println(j.getString(""));

    }

    @Test
    public void createWithPOJO() {
        Spartan sp = new Spartan();
        sp.setName("Kolya");
        sp.setGender("Male");
        sp.setPhone(12345676891l);

        JsonPath j = given().accept(ContentType.JSON)
                .body(sp)
                .contentType(ContentType.JSON)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .extract().jsonPath();
        System.out.println(j.getString(""));

    }
    @Test
    public void getNewSpartans(){
        Response r = given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .extract().response();

        System.out.println(r.path("[99]").toString());
        System.out.println(r.path("[100]").toString());
        System.out.println(r.path("[101]").toString());
        System.out.println(r.path("[102]").toString());
        System.out.println(r.path("[103]").toString());
        System.out.println(r.path("[104]").toString());
        //System.out.println(r.path("[105]").toString());
    }
}
