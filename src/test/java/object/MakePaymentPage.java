package object;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MakePaymentPage extends BasePage{

    public MakePaymentPage(AppiumDriver driver) {
        super(driver);
    }


    @AndroidFindBy(id="phoneTextField")
    private MobileElement fdPhone;
    @AndroidFindBy(id="nameTextField")
    private MobileElement fdName;
    @AndroidFindBy(id="amountTextField")
    private MobileElement fdAmount;
    @AndroidFindBy(id="countryButton")
    private MobileElement btnSelect;
    @AndroidFindBy(id="sendPaymentButton")
    private MobileElement btnPayment;
    @AndroidFindBy(id="cancelButton")
    private MobileElement btnCancel;
    @AndroidFindBy(xpath="//*[@text='Philippines']")
    private MobileElement txtPhilippines;
    @AndroidFindBy(id="button1")
    private MobileElement btnYes;
    @AndroidFindBy(id="button2")
    private MobileElement btnNo;




    public void clickOnSendPayment(){
        btnPayment.click();
    }
    public void clickOnCancel(){
        btnCancel.click();
    }

    //makePay
    public void fillPayment(String phone,String name,String amount) {
        fdPhone.sendKeys(phone);
        fdName.sendKeys(name);
        fdAmount.sendKeys(amount);
    }

    public void clickOnSelect(){
        btnSelect.click();
    }

    public void selectFromList(){
        swipeScreen(Direction.UP);
        txtPhilippines.click();
    }

    public void clickOnYes(){
        btnYes.click();
    }


    //send pay disable



}
