package ex;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.*;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static io.appium.java_client.service.local.AppiumDriverLocalService.buildService;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

public abstract class ServiceTest {
    protected AppiumDriver<MobileElement> driver;
    private AppiumDriverLocalService service;

    @BeforeSuite
    public void startService() {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder
                .usingAnyFreePort()
                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                .withAppiumJS(
                        new File("C:\\Users\\User\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withArgument(SESSION_OVERRIDE)
                .withArgument(LOG_LEVEL, "info")
                .withLogFile(new File(System.getProperty("user.dir") + "/target/AppiumServerLogs.txt"));
        service = buildService(serviceBuilder);
        service.start();
    }

    @AfterClass
    public void closeApp() {
        if (driver != null) {
            driver.closeApp();
        }
    }

    @AfterSuite
    public void stopService() {
        service.stop();
    }

    @Parameters({"emulator"})
    @BeforeClass
    public void startActivity(ITestContext testContext, @Optional("true") boolean emulator) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(PLATFORM_NAME, Platform.ANDROID.name());
        capabilities.setCapability(NEW_COMMAND_TIMEOUT, 25);
        capabilities.setCapability(AUTOMATION_NAME, ANDROID_UIAUTOMATOR2);
        capabilities.setCapability(UNICODE_KEYBOARD, true);
        if (emulator) {
            capabilities.setCapability(AVD, "Nexus_5X_API_28");
        }
        capabilities.setCapability(RESET_KEYBOARD, true);
        capabilities.setCapability(AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability(APP, System.getProperty("user.dir") + "/src/test/resources/mobile/calculator.apk");
        capabilities.setCapability(APP_PACKAGE, "com.google.android.calculator");
        capabilities.setCapability(APP_ACTIVITY, "com.android.calculator2.Calculator");
        capabilities.setCapability(NO_RESET, false);
        driver = new AndroidDriver<>(service.getUrl(), capabilities);
        testContext.setAttribute("WebDriver", driver);
    }
}
