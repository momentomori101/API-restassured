package sd;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CreateUserPostCallTest {
    Playwright playwright;
    APIRequest request;
    APIRequestContext requestContext;
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
    
    @Test
    public void CreateUserTest() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name","uiyush");
        data.put("email","uiyushshewale@gmail.com");
        data.put("gender","male");
        data.put("status","active");
        APIResponse apiPostResponse = requestContext.post("https://gorest.co.in/public/v2/users",
                        RequestOptions.create()
                            .setHeader("Content-Type", "application/json")
                            .setHeader("Authorization","Bearer f014dd853788ecab72e371f3ed515798fb8f803f0778c50fa9d601e1c59d4221")
    			            .setData(data));
         System.out.println(apiPostResponse.status());
        Assert.assertEquals(apiPostResponse.status(), 201);
        Assert.assertEquals(apiPostResponse.statusText(), "Created");
        System.out.println(apiPostResponse.text());
        System.err.println("------line 54-----------------");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(apiPostResponse.text());
            JsonNode PostResponse = objectMapper.readTree(apiPostResponse.body());
            System.out.println(PostResponse.toPrettyString());
            id = jsonNode.get("id").asText();
            System.out.println("Created user email id: " + id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getHeadersTest(){
        APIResponse apiResponse = requestContext.get("https://gorest.co.in/public/v2/users"
        // +id,
        //         RequestOptions.create()
                    // .setHeader("Content-Type", "application/json")
                    // .setHeader("Authorization","Bearer f014dd853788ecab72e371f3ed515798fb8f803f0778c50fa9d601e1c59d4221")
        );
        int statusCode = apiResponse.status();
        System.out.println("response status code: " + statusCode);
        System.out.println("text response: " + apiResponse.text());
        Assert.assertEquals(statusCode, 200);

        //using map:
       Map<String, String> headersMap =  apiResponse.headers();
        headersMap.forEach((k,v) -> System.out.println(k + ":" + v));
        System.out.println("total response headers: " + headersMap.size());
        Assert.assertEquals(headersMap.get("server"), "cloudflare");
        Assert.assertEquals(headersMap.get("content-type"), "application/json; charset=utf-8");

        System.out.println("===============================");

        //using list:
       List<HttpHeader> headersList = apiResponse.headersArray();
        for(HttpHeader e : headersList){
            System.out.println(e.name + " : " + e.value);
        }
    }
}
