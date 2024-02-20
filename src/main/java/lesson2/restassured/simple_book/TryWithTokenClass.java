package lesson2.restassured.simple_book;

import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TryWithTokenClass {

    public final static String token = "ad7fe33b7047d464c70647dc035adafd89ae40b514e73fe7f079396abd978513";

    public static void main(String[] args) {
        RestAssured.baseURI = "https://simple-books-api.glitch.me";
        String requestBody = "{\"bookId\": 1, \"customerName\": \"Hakan\"}";

        Response response = given()
                .header("Authorization", "Bearer " + token )
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/orders/");

        System.out.println(response.getBody().asString());

    }
}
