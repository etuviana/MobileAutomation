package ex;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Description;
import object.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Dolphin {
    protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();


    @BeforeClass
    public void setUp() throws MalformedURLException {

        dc.setCapability(MobileCapabilityType.UDID, "8a11d9ca");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.maytronics.app");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".module.splash.SplashActivity");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
    }


    @Test(description = "Navigate to Home page APP")
    @Description("Open  APP , enter user Name and password click on Login")
    public void A01_Login() throws InterruptedException {

        Thread.sleep(10000);
        //clickOnLogin();
        driver.findElement(By.xpath("//*[@class='android.widget.FrameLayout']")).click();
        driver.findElement(By.xpath("//*[@id='custom_progress_textView']")).click();
        Thread.sleep(30000);
        driver.findElement(By.xpath("//*[@id='custom_progress_textView']")).click();
        driver.findElement(By.xpath("//*[@id='custom_progress_textView']")).click();

    }

    @AfterClass
    public void tearDown() {

        //driver.quit();
    }
}
