package test;

import io.qameta.allure.Description;
import object.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest{


    @Test(description = "Navigate to Home page APP Fail Test")
    @Description("Open ExperiBank APP , enter bad user Name and password click on Login , verify Error alert")
    public void A01_FailLogin(){

        HomePage homePage = new HomePage(driver);
        homePage.filllogin("coco","foco");
        homePage.clickOnLogin();
        Assert.assertEquals(homePage.getErrorTitleMessage(),"Error");
        Assert.assertEquals(homePage.getErrorMessage(),"Invalid username or password!");
        homePage.clickOnClose();

    }

    @Test(description = "Navigate to Home page APP")
    @Description("Open ExperiBank APP , enter user Name and password click on Login")
    public void A02_Login(){

        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getBuildVersion(),"Build Version : 1.0.465");
        System.out.println(homePage.getBuildVersion());
        homePage.filllogin(getData("USERNAME"),getData("PASSWORD"));
        homePage.clickOnLogin();
        homePage.clickOnLogout();

    }
}
