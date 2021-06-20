package extentReportingDemo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NopCommerceTest {

    public WebDriver navegador;


    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeTest
    public void setExtent() {

        extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/myReport.html");
        extentSparkReporter.config().setDocumentTitle("Automation Report");//Title of the report
        extentSparkReporter.config().setReportName("Funtional Report");//Name of the report
        extentSparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();

        extent.attachReporter(extentSparkReporter);

        extent.setSystemInfo("Hostname", "LocalHost");
        extent.setSystemInfo("OS", "Windows10");
        extent.setSystemInfo("Tester Name", "Pavan");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @AfterTest
    public void endReport() {
        extent.flush();
    }

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\USUARIO1\\Desktop\\driver\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.get("http://demo.nopcommerce.com/");
    }

    @Test
    public void noCommerceTitleTest() {
        test = extent.createTest("noCommerceTitleTest");

        String title = navegador.getTitle();
        System.out.println(title);
        Assert.assertEquals(title,"nopCommerce demo store");
    }

    @Test
    public void noCommerceLogoTest() {
        test = extent.createTest("noCommerceLogoTest");

        Boolean status = navegador.findElement(By.xpath("//img[@alt=\"nopCommerce deo store\"]")).isDisplayed();
        Assert.assertTrue(status);

    }

    @Test
    public void noCommerceLoginTest() {
        test = extent.createTest("noCommerceLogoTest");
        Assert.assertTrue(true);
    }

    @AfterMethod
    public void TearDown(ITestResult result) throws IOException {

        String screenshotPath = NopCommerceTest.getScreenshot(navegador, result.getName());

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); //to add name in extent report
            test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); //to add error/exception in


           test.addScreenCaptureFromPath(screenshotPath);//adding scren shot

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case SkIPPED IS " + result.getName());
            test.addScreenCaptureFromPath(screenshotPath);//adding scren shot
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
            test.addScreenCaptureFromPath(screenshotPath);//adding scren shot
        }

        navegador.quit();
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String image = screenshotName + dateName + ".png";
        FileUtils.copyFile(source, new File("test-output\\"+image));
        return image;
    }


}






















