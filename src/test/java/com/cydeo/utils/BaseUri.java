package com.cydeo.utils;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseUri {
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI="http://18.212.176.61:8000";
    }
}
