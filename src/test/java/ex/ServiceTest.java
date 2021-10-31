package ex;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class ServiceTest {
    /*
        Starting an Appium Server Programmatically Using AppiumServiceBuilder
     */

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

    @AfterClass
    public void tearDown() {

        //Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        driver.quit();
        service.stop();
    }
}
