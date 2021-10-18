package test;

import io.qameta.allure.Description;
import object.BalancePage;
import object.BasePage;
import object.HomePage;
import object.MakePaymentPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BalanceTest extends BaseTest{


    @Test(description = "Balance test")
    @Description("check balance")
    public void A01_Balance(){

        HomePage homePage = new HomePage(driver);
        homePage.filllogin(getData("USERNAME"),getData("PASSWORD"));
        homePage.clickOnLogin();

        BalancePage balancePage = new BalancePage(driver);
        Assert.assertTrue(balancePage.getBalance().contains("Your balance is:"));
        balancePage.clickOnMakePayment();
    }

    @Test(description = "Balance test")
    @Description("check balance")
    public void A02_Pay(){

        MakePaymentPage makePaymentPage = new MakePaymentPage(driver);
        makePaymentPage.fillPayment("05654654","elad","20");
        makePaymentPage.clickOnSelect();
        makePaymentPage.selectFromList();
        makePaymentPage.clickOnSendPayment();
        makePaymentPage.clickOnYes();

    }

}
