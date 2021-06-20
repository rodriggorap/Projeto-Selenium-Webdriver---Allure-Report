package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import suport.Web;

public class LoginTests2 extends Web {

    @Test
    public void deveFazerLogin2() {

        test = extent.createTest("deveFazerLogin2");

        String login = "julio0001";
        String password = "123456";

        loginAction.login(login,password);
        Assert.assertEquals(homeObject.getLinkHiJulio().getText(),"Hi, Julio");
    }
}
