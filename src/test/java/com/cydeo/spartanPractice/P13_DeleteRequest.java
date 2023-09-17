package com.cydeo.spartanPractice;

import com.cydeo.utils.BaseUri;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P13_DeleteRequest extends BaseUri   {
    @Test
    void deletePractice() {
        given().pathParam("id", 105)
                .when()
                .delete("/api/spartans/{id}")
                .then().statusCode(204);

        JsonPath j = given().accept(ContentType.JSON)
                .pathParam("id",105)
                .when().get("/api/spartans/{id}")
                .then().extract().jsonPath();
        System.out.println(j.getString(""));
    }
}
