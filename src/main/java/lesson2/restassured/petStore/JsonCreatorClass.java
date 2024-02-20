package lesson2.restassured.petStore;

import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class JsonCreatorClass {

    public static JSONObject generateFullBodyForPetCreation(int idOfPet, String nameOfPet, String status){
        JSONObject fullBody =new JSONObject();
        fullBody.put("id", idOfPet);

        JSONObject categoryObject = new JSONObject();
        categoryObject.put("id", 1234);
        categoryObject.put("name", "Dog");

        fullBody.put("category", categoryObject);
        fullBody.put("name",nameOfPet);

        JSONArray photoUrlArray = new JSONArray();
        photoUrlArray.add("url1");
        photoUrlArray.add("url2");

        fullBody.put("photoUrls", photoUrlArray);

        JSONArray arrayOfTags = new JSONArray();
        JSONObject tag1 = new JSONObject();
        tag1.put("id", 3);
        tag1.put("name", "tag1");
        JSONObject tag2 = new JSONObject();
        tag2.put("id", 4);
        tag2.put("name", "tag2");
        arrayOfTags.add(tag1);
        arrayOfTags.add(tag2);

        fullBody.put("tags", arrayOfTags);
        fullBody.put("status",status);
        return fullBody;
    }


    public static void main(String[] args) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        //add Pet
        Response response = given()
                .contentType(ContentType.JSON)
                .body(generateFullBodyForPetCreation(263, "Sharky", "available"))
                .when()
                .post("/pet");
        String responseBody = response.getBody().asString();
        int statusCode = response.getStatusCode();
        System.out.println(responseBody);
        System.out.println(statusCode);
        String name = JsonParser.parseString(response.getBody().asString()).getAsJsonObject().get("name").getAsString();
        System.out.println(name);

        //update Pet
        Response response1 = given()
                .contentType(ContentType.JSON)
                .body(generateFullBodyForPetCreation(263, "Sparky", "available"))
                .when()
                .put("/pet");
        String changedName = JsonParser.parseString(response1.getBody().asString()).getAsJsonObject().get("name").getAsString();
        System.out.println(changedName);
        int idOfPet = JsonParser.parseString(response1.getBody().asString()).getAsJsonObject().get("id").getAsInt();

        //delete Pet
        Response response2 = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/pet/" + idOfPet);
        System.out.println(response2.getStatusCode());

        //check by id deleted pet
        Response response3 = RestAssured.get("/pet/" + idOfPet);
        System.out.println(response3.getBody().asString());

    }
}
