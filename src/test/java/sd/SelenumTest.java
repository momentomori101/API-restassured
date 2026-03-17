package sd;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.SelectOption;

import io.github.bonigarcia.wdm.WebDriverManager;

//add listener to the test class
import org.testng.annotations.Listeners;
@Listeners(sd.Listner.class)
//reportng listener

public class SelenumTest {

    WebDriver driver;
    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
         WebDriverManager.chromedriver().setup();  // <-- REQUIRED
        // WebDriver driver = new ChromeDriver();
        //lets run in headless mode
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        // driver = new ChromeDriver(options);
        driver = new ChromeDriver(options);
    }
    
    @AfterTest
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(enabled = false)
    public void selectDropdownValue() {
        
        // WebDriverManager.chromedriver().setup();  // <-- REQUIRED
        // // WebDriver driver = new ChromeDriver();
        // //lets run in headless mode
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        // WebDriver driver = new ChromeDriver(options);
        

        try {
            driver.get("https://the-internet.herokuapp.com/windows");
            //print url
            String currentUrl = driver.getCurrentUrl();
            String title = driver.getTitle();
            System.out.println("Current URL: " + currentUrl + " Title: " + title);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            WebElement dropdown = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("dropdown"))
            );

            Select select = new Select(dropdown);
            select.selectByVisibleText("Option 2");
            String frdtelt =select.getFirstSelectedOption().getText();
            System.out.println(frdtelt);

            Assert.assertEquals(
                    select.getFirstSelectedOption().getText(),
                    "Option 2",
                    "Dropdown selection failed"
            );

        } finally {
            driver.quit();
        }
    }
    @Test(enabled = false)
    public void selectDropdownValue2() {
        
        WebDriverManager.chromedriver().setup();  // <-- REQUIRED
        // WebDriver driver = new ChromeDriver();
        //lets run in headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        

        try {
            driver.get("https://the-internet.herokuapp.com/dropdown");
            //print url
            String currentUrl = driver.getCurrentUrl();
            String title = driver.getTitle();
            System.out.println("Current URL: " + currentUrl + " Title: " + title);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            WebElement dropdown = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("dropdown"))
            );

            Select select = new Select(dropdown);
            select.selectByVisibleText("Option 2");
            String frdtelt =select.getFirstSelectedOption().getText();
            System.out.println(frdtelt);

            Assert.assertEquals(
                    select.getFirstSelectedOption().getText(),
                    "Option 2",
                    "Dropdown selection failed"
            );

        } finally {
            driver.quit();
        }
    }

    @Test(enabled = false)
    public void testMethod2() {

        driver.get("https://the-internet.herokuapp.com/windows");
        String parentWindow = driver.getWindowHandle();

        // Open 3 additional windows
        for (int i = 0; i < 3; i++) {
            //try different locators for click here link
            // driver.findElement(By.linkText("Click Here")).click();
            driver.findElement(By.partialLinkText("Click")).click();
            // driver.findElement(By.xpath("//a[text()^='Click h']")).click();

            // Thread.sleep(1000);
        }
        // Get all window handles
        // Set<String> allWindows = driver.getWindowHandles();
        Set<String> allWindows= driver.getWindowHandles();
        System.out.println("Total Windows: " + allWindows.size());
        // Iterate and switch
        for (String window : allWindows) {
            driver.switchTo().window(window);
            String uploadedFileName = driver.findElement(By.id("uploaded-files")).getText();
            String title= driver.getTitle();
            if (title.equals("The Internet")) {
                System.out.println("Correct window found.");
                break;
            }
            System.out.println("Found Window With Title: " + title);
            //apply soft assertion to check if the link is displayed in the current window
            System.out.println("Switched to window with title: " + title);
            System.out.println("trimmed"+title.trim());
            // Skip blank windows
            if (title != null && !title.trim().isEmpty()) {
                Boolean value=title.trim().isEmpty();
                System.out.println("isEmpty: " + value);
                // Example validation
                if (title.equals("")) {
                    System.out.println("Correct window found.");
                    break;
                }
            }
            
        }
        driver.switchTo().window(parentWindow);
        
        System.out.println("shdgvhj"+driver.getTitle());
        System.out.println( "shdgc"+driver.findElement(ByClassName.className("example")).getText());



        // driver.quit();
    }

//execute test case withing specified limits. 
//timeout is used
    @Test(enabled = true,timeOut = 10000, expectedExceptions = {org.openqa.selenium.NoSuchElementException.class})
    public void Locator() {
        driver.get("https://the-internet.herokuapp.com/windows");

        SoftAssert softAssert = new SoftAssert();

        String title = driver.getTitle();
        try {
            //Xpath syntax
            //tagname[@attribute='value' and @attribute2='value2']
            //tags like div, span, a, input, button
            //attributes like id, class, name, href
            //css selector syntax
            //tagname[attribute='value']
            //.classname
            //id#value

            //using partial link text locator to find the link and assert if it is displayed
            //by css selector
            // WebElement link =driver.findElement(By.cssSelector("#content").getAttribute("id"));
            // WebElement css =driver.findElement(By.cssSelector("#target"));
            // WebElement css2 =driver.findElement(By.cssSelector(".example"));
            WebElement css2 =driver.findElement(By.cssSelector("div[class^='exam']"));
            List<WebElement> css3 =driver.findElements(By.cssSelector("div.example"));
            System.out.println("css3"+css3.get(0).getText());
            System.out.println("css2"+css2.getText());
            
            
            // System.out.println(css.getText());
            System.out.println(css2.getText());
            WebElement link =driver.findElement(By.xpath("//a[contains(text(),'Elemental Selenium')]"));
            //start with locator

            WebElement link1 =driver.findElement(By.xpath( "//a[text()='Elemental Selenium']"));
            WebElement link2 =driver.findElement(By.xpath("//a[text()='Elemental Selenium']"));
            WebElement link3 =driver.findElement(By.linkText("Elemental Selenium"));
            WebElement link4=driver.findElement(By.partialLinkText("Elemental"));
            WebElement link6=driver.findElement(By.xpath("//a[starts-with(text(),'Elemental') and contains(text(),'Selenium')]"));
            try{
                if (link.isDisplayed()|| link1.isDisplayed() || link2.isDisplayed() || link3.isDisplayed() || link4.isDisplayed()) {
                    System.out.println("Link text is: " + link3.getText());
                    System.out.println("Link text is: " + link4.getText());
                    System.out.println("Link text is: " + link6.getText());
                    System.out.println("Link is displayed");
                } else {
                    System.out.println("Link is not displayed");
                }
            } catch (Exception e) {
                System.err.println("Error finding link: " + e.getMessage());

            }


            //using xpath locator to find the link and assert if it is displayed
        
        } catch (AssertionError e) {
            System.err.println("<<<<failed to find: " + e.getMessage());
        }
        
        System.out.println("Test continues even if assertion fails");

        softAssert.assertAll();
    }

    @Test(enabled = false)
    public void ListElements() {
        //find elements 
        // driver.get("https://the-internet.herokuapp.com/windows");

        driver.get("https://www.w3schools.com/java/default.asp");
        List<WebElement> menuItems =driver.findElements(By.xpath("//div[@id='leftmenuinnerinner']//a[contains(text(),'Java S')]"));
        // System.out.println(driver.findElement(By.cssSelector(null)));
        //by css id  selector
        // List<WebElement> menuItems =driver.findElements(By.cssSelector("#leftmenuinnerinner a"));
        System.out.println("Total menu items: " + menuItems.size());
        for (WebElement item : menuItems) {
            //if item is null or empty, dont print
                String text = item.getText().trim();

                if (text == null || text.trim().isEmpty()) {
                    continue; // skip empty menu items
                }
                //create list of menu items and print them
                //print the menu items before finding the java scope link

                if(text.trim().equals("Java Scope")) {
                    System.out.println("Found Java Scope link, clicking it.");
                    item.click();
                    break;
                }
                //lets use xpath and span to select specific menu item and click on it

                System.out.println("Menu item: " + text);
        }


    }
     

    @Test(enabled = false)
    public void uploadfile(){
        driver.get("https://the-internet.herokuapp.com/upload");
        WebElement uploadElement = driver.findElement(By.id("file-upload"));
        uploadElement.sendKeys("C:/Users/pshewale/Downloads/New Text Document (3).txt");
        driver.findElement(By.id("file-submit")).click();
        //assert the file is uploaded successfully
        String uploadedFileName = driver.findElement(By.id("uploaded-files")).getText();
        Assert.assertEquals(uploadedFileName, "New Text Document (3).txt", "File upload failed");
    }

    @Test(enabled = false)
    public void uploadMultiplefilses(){
        driver.get("https://the-internet.herokuapp.com/upload");
        WebElement uploadElement = driver.findElement(By.id("file-upload"));
        //upload multiple files
        uploadElement.sendKeys("C:/Users/pshewale/Downloads/New Text Document (3).txt\nC:/Users/pshewale/Downloads/New Text Document (4).txt");
        //select all files in the files in foder and upload
        uploadElement.sendKeys("C:/Users/pshewale/Downloads/*"); 

        driver.findElement(By.id("file-submit")).click();
        //assert the files are uploaded successfully
        String uploadedFileName = driver.findElement(By.id("uploaded-files")).getText();
        Assert.assertTrue(uploadedFileName.contains("New Text Document (3).txt"), "First file upload failed");
        Assert.assertTrue(uploadedFileName.contains("New Text Document (4).txt"), "Second file upload failed");

    }


    @Test(enabled = true)
    public void MouseAction(){
        driver.get("https://the-internet.herokuapp.com/hovers");
        WebElement figure = driver.findElement(By.className("figure"));
        //hover over the element
        Actions actions = new Actions(driver);
        actions.moveToElement(figure).perform();
        //assert the caption is displayed
        WebElement caption = figure.findElement(By.className("figcaption"));
        Assert.assertTrue(caption.isDisplayed(), "Caption is not displayed on hover");



    }


}

//     @Test
//     public void testMethod2() {

//         Playwright playwright = Playwright.create();
//         com.microsoft.playwright.Browser browser = playwright.chromium().launch(new com.microsoft.playwright.BrowserType.LaunchOptions()
//                 .setHeadless(false));
//         com.microsoft.playwright.BrowserContext context = browser.newContext();
//         com.microsoft.playwright.Page page = context.newPage();
//         page.navigate("https://the-internet.herokuapp.com/dropdown");
//         try {
//             page.locator(".dropdown").click();

//             Locator option = page.locator("//li[text()='Option 2']");
//             option.waitFor();
//             option.click();

//         } catch (TimeoutError e) {
//             System.err.println("Dropdown option not found in time");
//         } catch (PlaywrightException e) {
//             System.err.println("Playwright error occurred: " + e.getMessage());
// }