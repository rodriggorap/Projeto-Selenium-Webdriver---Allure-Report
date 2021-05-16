package pageObject;

import suport.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginObject extends BasePage {

    public LoginObject(WebDriver navegador) {
        super(navegador);
        PageFactory.initElements(navegador, this);
    }

    @FindBy(linkText = "Sign in")
    private WebElement linkSignIn;

    @FindBy(linkText = "Task it!")
    private WebElement linkTaskit;

    @FindBy(xpath = "//input[@placeholder=\"Please, tell us your login\"]")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@placeholder=\"Excuse me, can you tell us a secret?\"]")
    private WebElement inputPassword;

    @FindBy(linkText = "SIGN IN")
    private WebElement linkSigninLogin;

    @FindBy(xpath = "//div[@id=\"toast-container\"]/div[@class=\"toast rounded\"]")
    private WebElement alert;

    public WebElement getAlert() {
        return alert;
    }

    public void setAlert(WebElement alert) {
        this.alert = alert;
    }

    public WebElement getLinkSignIn() {
        return linkSignIn;
    }

    public void setLinkSignIn(WebElement linkSignIn) {
        this.linkSignIn = linkSignIn;
    }

    public WebElement getLinkTaskit() {
        return linkTaskit;
    }

    public void setLinkTaskit(WebElement linkTaskit) {
        this.linkTaskit = linkTaskit;
    }

    public WebElement getInputLogin() {
        return inputLogin;
    }

    public void setInputLogin(WebElement inputLogin) {
        this.inputLogin = inputLogin;
    }

    public WebElement getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(WebElement inputPassword) {
        this.inputPassword = inputPassword;
    }

    public WebElement getLinkSigninLogin() {
        return linkSigninLogin;
    }

    public void setLinkSigninLogin(WebElement linkSigninLogin) {
        this.linkSigninLogin = linkSigninLogin;
    }
}
