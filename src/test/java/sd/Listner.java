package sd;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

//pom.xml for extent reports
/* 
<dependency>
      <groupId>com.aventstack</groupId>
      <artifactId>extentreports</artifactId>
      <version>5.0.9</version>
    </dependency>
    
    <dependency>
      <groupId>com.aventstack</groupId>
      <artifactId>extentreports-spark</artifactId>
      <version>5.0.9</version>
    </dependency>

*/


//lets implimebt extend the ITestListener interface to create a listener class for TestNG



public class Listner implements ITestListener {
    // This is a sample listener class for TestNG
    // You can implement methods to listen to test events such as onTestStart, onTest
    // Success, onTestFailure, etc.
    // For example, you can log the test results or take screenshots on failure
    ExtentReports report;
    ExtentSparkReporter reporter;
    ExtentTest test;



    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        report = new ExtentReports();
        reporter = new ExtentSparkReporter("test-output/extentReport.html");  //file location
        report.attachReporter(reporter); //report name
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
        test = report.createTest(result.getName());
    }

    
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        test.log(Status.PASS, result.getName() + " is passed FROM LISTENER");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        // test.fail(result.getThrowable()); //log the failure in extent report
        test.log(Status.FAIL, result.getName() + " is failed FROM LISTENER");
//add logs in report
        test.log(Status.INFO, "This is additional information about the failure.");
        test.fail(result.getThrowable());
        
            // You can add code here to take a screenshot or log additional information
        // You can add code here to take a screenshot or log additional information
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        //flush
        report.flush();
        System.out.println("All tests finished.");
    }
}
