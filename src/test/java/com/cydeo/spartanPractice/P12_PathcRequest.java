package com.cydeo.spartanPractice;

import com.cydeo.utils.BaseUri;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P12_PathcRequest extends BaseUri {
    @Test
    public void pathcPractice() {

        String str = "{\n" +
                "  \"name\": \"Mihal\",\n" +
                "  \"phone\": 3555426987\n" +
                "}";

        Map<String, Object> map = new HashMap<>(Map.of(
                "name", "Mihal"
                //"phone", 355426898
        ));

        JsonPath j = given()
                .contentType(ContentType.JSON)
                .pathParam("id", 101)
                .body(map)
                .when()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(204)
                .extract().jsonPath();



    }

    @Test
    void getNewSp() {
        Response r = given().accept(ContentType.JSON)
                .pathParam("id", 101)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .body("name", Matchers.is("Mihal"))
                .extract().response();

        System.out.println(r.asString());
    }
}
