package com.cydeo.spartanPractice;

import com.cydeo.utils.BaseUri;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;
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

    @Test
    public void mapJson() {
        JsonPath j = given().accept(ContentType.JSON)
                .when().get("api/spartans")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().jsonPath();

        List<Map<String, Object>> all = j.getList("");
        System.out.println(all.get(0).get("name"));

        Map<String, Object> one = j.getMap("[0]");
        System.out.println(one);
    }
}
