package test;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = "pretty",
        features = {"src/test/java/test/features/"}
)
public class testRunner {

    public static AndroidDriver driver;
    private static final String serverAddress = "127.0.0.1";//Used for starting the appium server
    private static final int port = 4723;//Used for starting the appium server

    @BeforeClass
    public static void startApp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "5.0.1"); //Replace this with your Android version
        caps.setCapability("deviceName", "Samsung Galaxy"); //Replace this with your simulator/device 
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString() + "/src/main/resources/Evernote.apk";
        caps.setCapability("app", path); //Replace this with app path in your system
        
//            caps.setCapability("appActivity", "com.evernote.ui.HomeActivity");
//            caps.setCapability("appPackage", "com.evernote");
//            appInstalled = true;

        AppiumServer server = new AppiumServer(serverAddress,port);//setup Appium server 
        server.startAppiumServer();//open Appium server automatically
        
        driver = new AndroidDriver(server.getService(),caps);
        driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
    }
    
    @AfterClass
    public static void endApp(){
        driver.quit();
    }
}
