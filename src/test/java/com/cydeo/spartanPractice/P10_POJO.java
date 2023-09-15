package com.cydeo.spartanPractice;

import com.cydeo.utils.BaseUri;
import com.cydeo.utils.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class P10_POJO extends BaseUri {
    @Test
    public void oneObj() {
        JsonPath j = given().accept(ContentType.JSON)
                .pathParam("id", 1)
                .when().get("/api/spartans/{id}")
                .then()
                .body("name", Matchers.is("Meade"))
                .extract().jsonPath();
        Spartan m = j.getObject("", Spartan.class);
        System.out.println(m);
    }

    @Test
    public void customObject() {
        JsonPath j = given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();
        Spartan one = j.getObject("[0]", Spartan.class);
        List<Spartan> all = j.getList("", Spartan.class);

        System.out.println(one);
        for (Spartan spartan : all) {
            System.out.println(spartan);
        }


    }
}
