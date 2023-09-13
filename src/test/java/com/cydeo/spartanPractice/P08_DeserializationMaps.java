package com.cydeo.spartanPractice;

import com.cydeo.utils.BaseUri;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class P08_DeserializationMaps extends BaseUri {
    @Test
    public void getMap(){
        Map<String,Object> one = given().accept(ContentType.JSON).pathParam("id",3)
                .when().get("/api/spartans/{id}")
                .then().assertThat()
                .body("name", Matchers.is("Fidole"))
                .extract().as(Map.class);

        System.out.println(one);
        System.out.println("one.get(\"name\") = " + one.get("name"));
        System.out.println("one.get(\"id\") = " + one.get("id"));
    }
}
