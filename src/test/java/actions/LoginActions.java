package actions;

import org.openqa.selenium.WebDriver;
import pageObject.LoginObject;
import suport.BasePage;

public class LoginActions extends BasePage {

    static LoginObject loginObject;

    public LoginActions(WebDriver navegador) {
        super(navegador);
        loginObject = new LoginObject(navegador);
    }

    public HomeActions login(String login, String password) {
        loginObject.getLinkSignIn().click();
        loginObject.getInputLogin().sendKeys(login);
        loginObject.getInputPassword().sendKeys(password);
        loginObject.getLinkSigninLogin().click();

        return new HomeActions(navegador);

    }
}
