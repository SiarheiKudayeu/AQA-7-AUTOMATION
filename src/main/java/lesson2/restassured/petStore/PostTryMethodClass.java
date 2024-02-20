package lesson2.restassured.petStore;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostTryMethodClass {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://reqres.in/";
        String requestBody = "{\"name\": \"Sam\", \"job\": \"leader\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("api/users");

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        JsonObject responseObject = JsonParser.parseString(responseBody).getAsJsonObject();

        String dataOfCreation = responseObject.get("createdAt").getAsString();
        System.out.println("Date of creation is: " + dataOfCreation);
    }









}
