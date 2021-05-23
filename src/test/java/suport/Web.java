package suport;

import actions.LoginActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObject.HomeObject;
import pageObject.LoginObject;

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
    public void fechar() {
        String screenshotArquivo = "C:\\Users\\USUARIO1\\Desktop\\ProjetoSeleniumAlureReport\\src\\screenshot\\" + "Screenshot"+ ".png";
        Screenshot.tirar(navegador, screenshotArquivo);

        navegador.quit();
    }


}
