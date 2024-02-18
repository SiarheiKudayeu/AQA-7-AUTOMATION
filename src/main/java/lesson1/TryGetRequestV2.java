package lesson1;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TryGetRequestV2 {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://reqres.in/";


        Response response = RestAssured.get("/api/users?page=2");
        //получаем тело ответа в виде строки
        String responseString = response.getBody().asString();
        System.out.println(responseString);

        //получаем тело ответа в виде JsonObject
        JsonObject jsonObjectResponse = JsonParser.parseString(responseString).getAsJsonObject();
        //получили значение по ключу
        int generalCountOfUsers = jsonObjectResponse.get("total").getAsInt();
        System.out.println("Amount of users is: " + generalCountOfUsers);

        //забираем массив data
        JsonArray arrayListOfUsers = jsonObjectResponse.get("data").getAsJsonArray();
        JsonObject user7 = arrayListOfUsers.get(0).getAsJsonObject();
        String firstName = user7.get("first_name").getAsString();
        String lastName = user7.get("last_name").getAsString();
        int id = user7.get("id").getAsInt();
        System.out.println("Id of user is: " + id);
        System.out.println("First name is " + firstName);
        System.out.println("Last name is " + lastName);

    }
}
