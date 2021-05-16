package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import suport.BasePage;

public class HomeObject extends BasePage {

    public HomeObject(WebDriver navegador) {
        super(navegador);
        PageFactory.initElements(navegador, this);
    }

    @FindBy(linkText = "Hi, Julio")
    private WebElement linkHiJulio;

    public WebElement getLinkHiJulio() {
        return linkHiJulio;
    }

    public void setLinkHiJulio(WebElement linkHiJulio) {
        this.linkHiJulio = linkHiJulio;
    }
}
