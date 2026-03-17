package API_automation.PracticeTestNG3.src.test.java.sd;
import sd.UserRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.microsoft.playwright.options.HttpHeader;


public class CreatePutRequest {
    
// import com.microsoft.playwright.Assertions;

// import restassuredapitesting.src.test.java.tests.Test;
// import restassuredapitesting.src.test.java.tests.User;

    Playwright playwright;
    APIRequest request;
    APIRequestContext requestContext;

    static String emailId;
    // APIResponse apiResponse;
    static String id;
    @BeforeTest
    public void setup(){
        // APIResponse apiResponse = requestContext.get("https://gorest.co.in/public/v2/users");
        playwright = Playwright.create();
        request =  playwright.request();
        requestContext = request.newContext();
        System.out.println("--------API request and response setup completed........");
    }

    @AfterTest
    public void tearDown(){
        playwright.close();
        System.out.println("--------API request and response test is done........");

    }
    public static String getRandomEmail(){
        emailId = "testpwautomation"+ System.currentTimeMillis() + "@gmail.com";
        return emailId;
    }
    
    @Test
    public void CreateUserTest() throws IOException {
        UserRequest data = new UserRequest("Naveen", getRandomEmail(), "male", "active");
        // Map<String, Object> data = new HashMap<String, Object>();
        // data.put("name","uiyush");
        // data.put("email","uiyushshewale@gmail.com");
        // data.put("gender","male");
        // data.put("status","active");
            APIResponse apiPostResponse = requestContext.post("https://gorest.co.in/public/v2/users",
                            RequestOptions.create()
                                .setHeader("Content-Type", "application/json")
                                .setHeader("Authorization","Bearer f014dd853788ecab72e371f3ed515798fb8f803f0778c50fa9d601e1c59d4221")
                                .setData(data)
                    
                );
            //         .setExtraHTTPHeaders(Map.of(
            // "Content-Type", "application/json",
            // "Authorization", "Bearer f014dd853788ecab72e371f3ed515798fb8f803f0778c50fa9d601e1c59d4221"

        System.out.println(apiPostResponse.status());
        Assert.assertEquals(apiPostResponse.status(), 201);
        Assert.assertEquals(apiPostResponse.statusText(), "Created");
        System.out.println(apiPostResponse.text());
        System.err.println("------line 54-----------------");
        //Deserializing JSON response to Java Object

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(apiPostResponse.text());
            String responseText = apiPostResponse.text();
            UserRequest actUser = objectMapper.readValue(responseText, UserRequest.class);
            JsonNode PostResponse = objectMapper.readTree(apiPostResponse.body());
            System.out.println(PostResponse.toPrettyString());
            // id = jsonNode.get("id").asText();
            // System.out.println("Created user email id: " + id);
            System.out.println("actual user from the response---->");
            System.out.println(actUser);
            Assert.assertEquals(actUser.getName(), data.getName());
            Assert.assertEquals(actUser.getEmail(), data.getEmail());
            Assert.assertEquals(actUser.getStatus(), data.getStatus());
            Assert.assertEquals(actUser.getGender(), data.getGender());
            Assert.assertNotNull(actUser.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
