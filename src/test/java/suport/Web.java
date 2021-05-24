package suport;

import actions.LoginActions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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

    @BeforeMethod
    public void start() {
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
    public void fechar() throws IOException {
        //Tirar um screenshot
        TakesScreenshot ss = (TakesScreenshot) navegador;
        File arquivo = ss.getScreenshotAs(OutputType.FILE);

        //Formatar o arquivo para o tipo jpg

        BufferedImage bimage = ImageIO.read(arquivo);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write(bimage,"png",baos);
        byte [] finalshot = baos.toByteArray();
        //Adicionar o arquivo ao allure report
        io.qameta.allure.Allure.addAttachment("Evidência",new ByteArrayInputStream(finalshot));

        navegador.quit();
    }


}
