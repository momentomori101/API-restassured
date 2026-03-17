package sd.restassured;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;

public class ComplexResponse {


    @Test

    public static void main(String[] args) {
        // This class is intentionally left empty as it serves as a placeholder for the complex response structure.
        // The actual implementation will depend on the specific API response you are working with.

        RestAssured.baseURI = "https://api.example.com";

        ApiResponse apiResponse = 
        given()
        .when()
        .get("users/123")
        .then()
        .statusCode(200)
        .extract().as(ApiResponse.class);
        System.out.println(apiResponse.getData().getRoles().get(0));
        System.out.println(apiResponse.getMeta().getCode());
        //result will be list of roles assigned to the user with id 123

        // Now you can access the fields of the ApiResponse object
        //using JSON object mapper we can convert the response to pojo and then we can access the fields of the pojo
         //lets use json object for
        
        

    }





    
}
