package ex;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class DemoAPI {
    private AndroidDriver<AndroidElement> driver = null;
    private final DesiredCapabilities dc = new DesiredCapabilities();
    private final String expectedHours = "9";
    private final String expectedMinutes = "45";
    private TouchAction action;

    @BeforeClass
    public void startSeesion() throws MalformedURLException {
        dc.setCapability(MobileCapabilityType.UDID, "8a11d9ca");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        action = new TouchAction(driver);
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }

    // @AfterMethod
    public void afterMethod() {
        driver.resetApp();
    }

    @Test
    public void test01_MobileGenstures() {
        driver.findElement(By.xpath("//*[@text='Views']")).click();
        driver.findElement(By.xpath("//*[@contentDescription='Date Widgets']")).click();
        driver.findElement(By.xpath("//*[@text='2. Inline']")).click();
        AndroidElement startHours = driver.findElement(By.xpath("//*[@contentDescription='12']"));
        AndroidElement finishHours = driver.findElement(By.xpath("//*[@contentDescription='9']"));
        dragAndDrop(startHours, finishHours);
        AndroidElement startMinutes = driver.findElement(By.xpath("//*[@contentDescription='15']"));
        AndroidElement finishMinutes = driver.findElement(By.xpath("//*[@contentDescription='45']"));
        dragAndDrop(startMinutes, finishMinutes);
        assertEquals(driver.findElement(By.id("hours")).getText(), expectedHours);
        assertEquals(driver.findElement(By.id("minutes")).getText(), expectedMinutes);
    }

    @Test
    public void test02_MobileGenstures() {
        driver.findElement(By.xpath("//*[@text='Views']")).click();
        driver.findElement(By.xpath("//*[@text='Expandable Lists']")).click();
        driver.findElement(By.xpath("//*[@text='1. Custom Adapter']")).click();
        longPress(driver.findElement(By.xpath("//*[@text='People Names']")), 2);
        assertEquals(driver.findElement(By.xpath("//*[@text='Sample action']")).getText(), "Sample action");
    }

    @Test
    public void test03_MobileGenstures() {
        driver.findElement(By.xpath("//*[@text='Views']")).click();
        verifyElementExists("WebView", Direction.UP, 5, true);
    }

    public void dragAndDrop(MobileElement start, MobileElement finish) {
        action.press(new ElementOption()
                        .withElement(start))
                .moveTo(new ElementOption().withElement(finish))
                .release()
                .perform();
    }

    public void longPress(MobileElement elem, int duration) {
        action.longPress(new LongPressOptions()
                        .withElement(ElementOption.element(elem))
                        .withDuration(Duration.ofSeconds(duration)))
                .perform();
    }

    public void verifyElementExists(String elemText, Direction dir, int rounds, boolean toClick) {
        boolean isExists = false;
        for(int i = 1; i <= rounds; i++) {
            if (driver.findElements(By.xpath("//*[@text='" + elemText + "']")).size() != 0) {
                isExists = true;
                if(toClick)
                    driver.findElements(By.xpath("//*[@text='" + elemText + "']")).get(0).click();
                break;
            }
            else {
                swipe(dir);
            }
        }
        if(!isExists)
            fail("Element with text: " + elemText + " was not found");
    }

    public void swipe(Direction dir) {
        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = driver.manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }
}