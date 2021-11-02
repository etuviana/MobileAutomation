package ex;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class InstallAppTest {

    protected AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeClass
    public void setUp() throws MalformedURLException {

        dc.setCapability(MobileCapabilityType.UDID, "8a11d9ca");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.shivgadhia.android.ukMortgageCalc");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,true);

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);

        if (driver.isAppInstalled("com.shivgadhia.android.ukMortgageCalc")){

            System.out.println("The app installed");
            //driver.removeApp("com.shivgadhia.android.ukMortgageCalc");
        }
        else {

            driver.installApp("C:\\Users\\etuviana\\IdeaProjects\\MobileAutomation\\src\\test\\java\\App\\ukMortgageCalc.apk");
            driver.launchApp();
        }


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





    @AfterClass
    public void tearDown() {
        driver.quit();

    }
}
