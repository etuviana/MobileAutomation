package object;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class BalancePage extends BasePage{

    public BalancePage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath="//*[@text and @class='android.view.View']")
    private MobileElement txtBalance;
    @AndroidFindBy(id="makePaymentButton")
    private MobileElement btnMakePayment;
    @AndroidFindBy(id="mortageRequestButton")
    private MobileElement btnMortageRequest;
    @AndroidFindBy(id="expenseReportButton")
    private MobileElement btnExpenseReport;
    @AndroidFindBy(id="logoutButton")
    private MobileElement btnLogout;


    public void clickOnMakePayment(){
        btnMakePayment.click();
    }
    public void clickOnMortageRequest(){
        btnMortageRequest.click();
    }
    public void clickOnExpenseReport(){
        btnExpenseReport.click();
    }

    public void clickOnLogout(){
        btnLogout.click();
    }

    public String getBalance(){
        return txtBalance.getText();
    }

}
