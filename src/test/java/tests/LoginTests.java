package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import suport.Web;

public class LoginTests extends Web{


    @DataProvider(name = "login-alerts")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"rodrigo", "123456", "Maybe you brain dropped the password or login in some place!"},
                {"rodrigo", "", "Maybe you brain dropped the password or login in some place!"},
                {"", "125454", "Maybe you brain dropped the password or login in some place!"},
                {"", "", "Maybe you brain dropped the password or login in some place!"},

        };
    }


    @Test
    public void deveFazerLogin() {

        test = extent.createTest("deveFazerLogin");

        String login = "julio0001";
        String password = "12356";

        loginAction.login(login,password);
        Assert.assertEquals(homeObject.getLinkHiJulio().getText(),"Hi, Julio");
    }

    @Test(dataProvider = "login-alerts")
    public void naoDeveLogar(String login, String password, String alert) {

        test = extent.createTest("naoDeveLogar");

        loginAction.login(login,password);
        Assert.assertEquals(loginObject.getAlert().getText(),alert);
    }
}
