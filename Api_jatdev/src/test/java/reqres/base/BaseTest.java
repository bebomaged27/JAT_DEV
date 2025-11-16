package reqres.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void setUpSuite() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "Test-Report-" + timeStamp + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./test-output/" + reportName);
        sparkReporter.config().setDocumentTitle("ReqRes API Automation Report");
        sparkReporter.config().setReportName("API Test Results");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "API Automation Team");
        extent.setSystemInfo("Environment", "ReqRes.in");
        extent.setSystemInfo("Application", "User Management API");
    }

    @BeforeMethod
    public void setUpTest(Method method) {
        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);
        logInfo("Starting test: " + method.getName());
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logError("Test FAILED: " + result.getThrowable().getMessage());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logPass("Test PASSED");
        } else if (result.getStatus() == ITestResult.SKIP) {
            logSkip("Test SKIPPED");
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
        }
    }

    protected void logInfo(String message) {
        test.get().log(Status.INFO, message);
    }

    protected void logPass(String message) {
        test.get().log(Status.PASS, message);
    }

    protected void logError(String message) {
        test.get().log(Status.FAIL, message);
    }

    protected void logSkip(String message) {
        test.get().log(Status.SKIP, message);
    }

    protected void logResponseDetails(String endpoint, String requestBody, String responseBody, long responseTime) {
        logInfo("API Endpoint: " + endpoint);
        logInfo("Request Payload: " + requestBody);
        logInfo("Response Body: " + responseBody);
        logInfo("Response Time: " + responseTime + "ms");
    }
}
