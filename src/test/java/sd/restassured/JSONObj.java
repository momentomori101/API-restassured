package sd.restassured;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
// {
//   "name": "Apple MacBook Pro 16",
//   "data": {
//     "year": 2019,
//     "price": 1849.99,
//     "CPU model": "Intel Core i9",
//     "Hard disk size": "1 TB"
//   }
// }

public class JSONObj {
    
    @Test
    public void testPostRequest() {
        JsonObject product = new JsonObject();
        JsonObject data = new JsonObject();

        product.addProperty("name", "Apple MacBook Pro 16");
        data.addProperty("year", 2019);
        data.addProperty("price", 1849.99);
        data.addProperty("CPU model", "Intel Core i9");
        data.addProperty("Hard disk size", "1 TB");

        RestAssured.baseURI = "https://api.restful-api.dev"; 
        product.add("data", data);

        System.out.println(product.toString()); 
        //lets write rest assured code to send this json object as request body in post call
        Response response =
        given()
            .header("Content-Type", "application/json")
            .body(product.toString())
        .when()
            .post("/objects")
        .then()
            .statusCode(200)
            .extract()
            .response();

        System.out.println(response.asString());
        //assert response
        Assert.assertEquals(response.jsonPath().getString("name"), "Apple MacBook Pro 16");
        Assert.assertEquals(response.jsonPath().getInt("data.year"), 2019);
        Assert.assertEquals(response.jsonPath().getDouble("data.price"), 1849.99); 
    }


    @Test
    public void testPostRequestWithPojo() {
        Data data = new Data();
        data.setYear(2019);
        data.setPrice(1849.99);
        data.setCpuModel("Intel Core i9");
        data.setHardDiskSize("1 TB");

        Product product = new Product();
        product.setName("Apple MacBook Pro 16");
        product.setData(data);
        System.out.println("Product ID: " + product.getId());
        RestAssured.baseURI = "https://api.restful-api.dev"; 

        Product response =
         given()
            .header("Content-Type","application/json")
            .body(product)
        .when()
            .post("/objects")
        .then()
            .statusCode(200)
            .extract().as(Product.class);

        //print response
        System.out.println(response);
        System.out.println(response.getId());
        System.out.println(response.getName());
        System.out.println(response.getData().getCpuModel());

        // Now you can use this product object to send as request body in rest assured post call
    }

    @Test
    public void SchemaValidation() {
            RestAssured.baseURI = "https://api.restful-api.dev"; 
            given()
                .header("Content-Type","application/json")
            .when()
                .get("/objects/ff8081819cd4022c019cf7a6456d360d")
            .then()
                .statusCode(200)
                //validate response schema
                .body(matchesJsonSchemaInClasspath("responseSchema.json"));
    }
}
