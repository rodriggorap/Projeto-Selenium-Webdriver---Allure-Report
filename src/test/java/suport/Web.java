package suport;

import actions.LoginActions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import extentReportingDemo.NopCommerceTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pageObject.HomeObject;
import pageObject.LoginObject;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Web {

    private WebDriver navegador;
    protected static LoginActions loginAction;
    protected static LoginObject loginObject;
    protected static HomeObject homeObject;

    File arquivo;

    protected static ExtentSparkReporter extentSparkReporter;
    protected static ExtentReports extent;
    protected static ExtentTest test;


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
    public void start()  {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\USUARIO1\\Desktop\\driver\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

        navegador.get("http://www.juliodelima.com.br/taskit/");


        loginObject = new LoginObject(navegador);
        loginAction = new LoginActions(navegador);
        homeObject = new HomeObject(navegador);



    }


    @AfterMethod
    public void fechar(ITestResult result) throws IOException {

        //Tirar um screenshot
        TakesScreenshot ss = (TakesScreenshot) navegador;
        arquivo = ss.getScreenshotAs(OutputType.FILE);

        //Formatar o arquivo para o tipo jpg

        BufferedImage bimage = ImageIO.read(arquivo);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write(bimage, "png", baos);
        byte[] finalshot = baos.toByteArray();
        //Adicionar o arquivo ao allure report

        io.qameta.allure.Allure.addAttachment("Evidência", new ByteArrayInputStream(finalshot));

        /*--------------*/
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

}










