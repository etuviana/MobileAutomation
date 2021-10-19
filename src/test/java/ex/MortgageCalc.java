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

   @Test
    public void testUntitled_01() {
        driver.findElement(By.id("etAmount")).sendKeys("5000");
        driver.findElement(By.id("etTerm")).sendKeys("10");
        driver.findElement(By.id("etRate")).sendKeys("2.5");

        driver.findElement(By.id("btnCalculate")).click();

       AndroidElement Rep = driver.findElement(By.id("tvRepayment"));
       AndroidElement Inter = driver.findElement(By.id("tvInterestOnly"));

        System.out.println("Repayment is:" + Rep.getText() + "InterestOnly is:"+Inter.getText());

    }

    //@Test
    public void testDevice_02() {
        //System.out.println(driver.getStatus());
        //System.out.println(driver.getDeviceTime());
        //System.out.println(driver.getRemoteAddress());
        //System.out.println(driver.currentActivity());
        //System.out.println(driver.getOrientation());
        //String pageSource = driver.getPageSource();
        //System.out.println(pageSource);
        //driver.openNotifications();
        //driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        //System.out.println( driver.isAppInstalled("com.shivgadhia.android.ukMortgageCalc"));
    }


   //@Test
    public void testDevice_03() throws InterruptedException {

        driver.openNotifications();
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    //@Test
    public void testDevice_04() {

        driver.lockDevice();
        driver.unlockDevice();
    }

    //@Test
    public void testDevice_05() {

        System.out.println(driver.getOrientation());

        driver.rotate(ScreenOrientation.LANDSCAPE);

    }


    @AfterClass
    public void tearDown() {

        //Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        driver.quit();
        service.stop();
    }
}
