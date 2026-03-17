package sd;
import sd.UserRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
// import com.microsoft.playwright.Locator;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;
import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.xml.sax.Locator;
//selenium imports
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;
import static org.testng.Assert.assertTrue;

import com.microsoft.playwright.options.RequestOptions;

// import restassuredapitesting.src.test.java.tests.Test;
// import restassuredapitesting.src.test.java.tests.User;

public class CreatePostCalljson {
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
         Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
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
        UserRequest data = new UserRequest("Nave", getRandomEmail(), "male", "active");
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
        //assert content type
        // Assert.assertEquals(apiPostResponse.headerValue("Content-Type"), "application/json; charset=utf-8");
        assertTrue(!apiPostResponse.headers().get("content-type").contains("application/json"));
        //assert not application json

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
    
    // @Test(priority = 2)
//     public void getbookincart(){
//         try (Playwright playwright = Playwright.create()) {
//       Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
//         .setHeadless(false));
//       BrowserContext context = browser.newContext();
//       Page page = context.newPage();
//       page.navigate("https://www.amazon.in/");
//       page.getByPlaceholder("Search Amazon.in").fill("simsen book");
//       page.getByPlaceholder("Search Amazon.in").press("Enter");
//       page.getByRole(AriaRole.SLIDER, new Page.GetByRoleOptions().setName("Maximum price")).fill("9");
//       page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Apply the filter English to narrow results")).click();
//       page.getByText("35% Off or more").click();
//       page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("See more, Seller")).click();
//       page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Apply the filter BookAdda to narrow results")).click();
//       Page page1 = page.waitForPopup(() -> {
//         page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("The Man Eating Leopard of Rudraprayag")).click();
//       });
//       page1.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart").setExact(true)).click();
//       // Locator cartLink= page.locator("#nav-cart").click();
//       // Locator cartLink= page.getByLabel("Cart").click();
// //       Locator cartLink = page1.getByRole(
// //         AriaRole.LINK,
// //         new Page.GetByRoleOptions().setName("1 item in cart")
// // ); 
//     page1.navigate("https://www.amazon.in/cart/smart-wagon?newItems=0ffddb22-ceea-457c-a3c5-44156590a0a9,1&ref_=sw_refresh");
//     // assertThat(cartLink).isVisible();
//       //aseert item in cart is same as selected item

//     page1.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Added to cart")).click();}

//     }
  // @Test
  // public void gettodyadeal(){
  //       try (Playwright playwright = Playwright.create()) {
  //     Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
  //       .setHeadless(false));
  //     BrowserContext context = browser.newContext();
  //     Page page = context.newPage();
  //     page.navigate("https://www.amazon.in/");
  //     page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Today's Deals")).click();
  //     page.getByTestId("filter-bubble-deals-collection-electronics").click();
  //     page.getByTestId("filter-brands-42717|Noise").locator("i").click();
  //     page.getByTestId("filter-brands-253649|OnePlus").locator("i").click();
  //     page.getByTestId("filter-brands-851753|Fire-Boltt").locator("i").click();
  //     page.getByTestId("filter-brands-237204|Sony").locator("i").click();
  //     page.locator("div:nth-child(6) > .RangeSlider-module__wrapper_hcr2zWkjZwsep0dDM7Jg > .RangeSlider-module__controlWrapper_RNdhesKFIAtpFNyDyKk2 > .RangeSlider-module__rail_RtOIcoB2yoDIkG95tCiw > .RangeSlider-module__innerRail_wVwEU9CXxXuaTzpf8Luh").click();
  //   }
  // }

//   @Test(priority = 1)
//   public void selectDropdownValue() {
//         WebDriver driver = new ChromeDriver();

//         try {
//             driver.get("https://the-internet.herokuapp.com/dropdown");

//             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//             WebElement dropdownElement = wait.until(
//                     ExpectedConditions.visibilityOfElementLocated(By.id("Dropdown"))
//             );

//             Select select = new Select(dropdownElement);

//             // Select by visible text
//             select.selectByVisibleText("Option 2");

//             // Assertion-style validation (optional)
//             String selectedValue = select.getFirstSelectedOption().getText();
//             System.out.println("Selected value: " + selectedValue);

//         } catch (NoSuchElementException e) {
//             System.err.println("Dropdown or option not found: " + e.getMessage());

//         } catch (TimeoutException e) {
//             System.err.println("Dropdown did not load within timeout");

//         } catch (Exception e) {
//             System.err.println("Unexpected error occurred: " + e.getMessage());

//         } finally {
//             driver.quit();
//         }
//     }
}
