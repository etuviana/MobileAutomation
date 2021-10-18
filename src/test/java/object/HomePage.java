package object;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;

public class HomePage extends BasePage{

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id="usernameTextField")
    private MobileElement fdUserName;
    @AndroidFindBy(id="passwordTextField")
    private MobileElement fdPassword;
    @AndroidFindBy(id="loginButton")
    private MobileElement btnLogin;
    @AndroidFindBy(id="logoutButton")
    private MobileElement btnLogout;
    @AndroidFindBy(id="title")
    private MobileElement txtBuildVersion;
    @AndroidFindBy(id="alertTitle")
    private MobileElement txtAlertTitle;
    @AndroidFindBy(id="message")
    private MobileElement txtAlertmessage;
    @AndroidFindBy(id="button3")
    private MobileElement btnClose;




    public void filllogin(String user,String password) {
       fdUserName.sendKeys(user);
       fdPassword.sendKeys(password);
    }
    public void clickOnLogin(){
        btnLogin.click();
    }

    public void clickOnLogout(){
        btnLogout.click();
    }

    public String getBuildVersion(){
        return txtBuildVersion.getText();
    }
    public String getErrorTitleMessage(){
        return txtAlertTitle.getText();
    }

    public String getErrorMessage(){
        return txtAlertmessage.getText();
    }

    public void clickOnClose(){
        btnClose.click();
    }

}
