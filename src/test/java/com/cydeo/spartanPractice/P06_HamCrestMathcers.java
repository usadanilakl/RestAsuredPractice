package com.cydeo.spartanPractice;

import com.cydeo.utils.BaseUri;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


public class P06_HamCrestMathcers extends BaseUri {
    @Test
    public  void mathcerst(){
        given().accept(ContentType.JSON)
                .pathParam("id", 3)
                .when().get("api/spartans/{id}")
                .then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", Matchers.is(3))
                .body("name", Matchers.is("Fidole"))
                .body("gender", Matchers.is("Male"))
                .body("phone", Matchers.is(6105035231L));
    }
}
