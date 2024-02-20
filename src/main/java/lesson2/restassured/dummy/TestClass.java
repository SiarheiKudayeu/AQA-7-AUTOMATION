package lesson2.restassured.dummy;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestClass {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
        Response response = RestAssured.get("/employees");
        System.out.println(response.getBody().asString());
    }
}
