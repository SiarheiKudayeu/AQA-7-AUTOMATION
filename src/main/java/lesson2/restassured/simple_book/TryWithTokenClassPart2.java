package lesson2.restassured.simple_book;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TryWithTokenClassPart2 {

    public final static String token = "ad7fe33b7047d464c70647dc035adafd89ae40b514e73fe7f079396abd978513";

    public static void main(String[] args) {
        RestAssured.baseURI = "https://simple-books-api.glitch.me";

        Response response = given()
                .header("Authorization", "Bearer " + token )
                .when()
                .get("/orders/");

        String responseString = response.getBody().asString();
        System.out.println(responseString);

        //получаем тело ответа в виде JsonArray
        JsonArray jsonObjectResponse = JsonParser.parseString(responseString).getAsJsonArray();
        JsonObject order1 = jsonObjectResponse.get(0).getAsJsonObject();
        System.out.println(order1.get("id").getAsString());

    }
}
