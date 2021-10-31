package ex;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MortgageCalc {


    protected AndroidDriver<AndroidElement> driver = null;
    private AppiumDriverLocalService service;


    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeClass
    public void setUp() throws MalformedURLException {

        dc.setCapability(MobileCapabilityType.UDID, "8a11d9ca");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.shivgadhia.android.ukMortgageCalc");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        //driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        AppiumServiceBuilder builder = new AppiumServiceBuilder().usingAnyFreePort();//if u want usingPort(4723);
        service = AppiumDriverLocalService.buildService(builder);
        driver = new AndroidDriver<>(builder, dc);

        service.start();
    }

    @Test(description = "Navigate to Home page APP Test")
    @Description("Open Mortgage Calc APP , enter Amount,Term ,Rate and click on Calculate ,print Repayment and InterestOnly")
    public void testUntitled_01() {
        driver.findElement(By.id("etAmount")).sendKeys("5000");
        driver.findElement(By.id("etTerm")).sendKeys("10");
        driver.findElement(By.id("etRate")).sendKeys("2.5");

        driver.findElement(By.id("btnCalculate")).click();

       AndroidElement Rep = driver.findElement(By.id("tvRepayment"));
       AndroidElement Inter = driver.findElement(By.id("tvInterestOnly"));

        System.out.println("Repayment is:" + Rep.getText() + "InterestOnly is:"+Inter.getText());
    }

    @Test(description = "Actions on the device and Print information to screen")
    @Description("Print to screen: Status ,DevicesTime ,RemoteAddress ,currentActivity, Orientation and is AppInstalled")
    public void testDevice_02() {
        System.out.println("getStatus is : "+driver.getStatus());
        System.out.println("getDevicesTime is : "+driver.getDeviceTime());
        System.out.println("getRemoteAddress is : "+driver.getRemoteAddress());
        System.out.println("currentActivity is : "+driver.currentActivity());
        System.out.println("getOrientation is : "+driver.getOrientation());
        System.out.println("is AppInstalled : "+ driver.isAppInstalled("com.shivgadhia.android.ukMortgageCalc"));
    }


    //@Test(description = "Actions on the device")
   // @Description("open Notifications and click on Back key")
    public void testDevice_03() throws InterruptedException {

        driver.openNotifications();
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    //@Test(description = "Actions on the device")
    // @Description("lock and unlock device")
    public void testDevice_04() {

        driver.lockDevice();
        driver.unlockDevice();
    }

    //@Test(description = "Actions on the device")
    // @Description("Replace Screen Orientation")
    public void testDevice_05() {

        System.out.println(driver.getOrientation());
        driver.rotate(ScreenOrientation.LANDSCAPE);

    }


    @AfterClass
    public void tearDown() {
        driver.quit();
        service.stop();
    }
}
