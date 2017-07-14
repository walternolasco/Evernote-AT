package test.scripts;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.TouchAction;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import static test.testRunner.driver;

/**
 *
 * @author elco45
 */
public class Handwriting {

    private Wait<WebDriver> wait;
    private static Boolean created = false;

    @Before
    public void startApp() throws MalformedURLException {
        driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
        wait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
    }

    @Given("^A user that wants to ([^\"]*) a handwriting$")
    public void A_user_that_wants_to_something_a_handwriting(String arg) {
        if (!created) {
            driver.findElementById("com.evernote:id/main_fab_image_view").click();
            created = true;
        } else {
            if (arg.equals("delete")) {
                TouchAction action = new TouchAction(driver);
                action.longPress(driver.findElement(By.xpath("//android.widget.TextView[(@resource-id='com.evernote:id/title') and (@text='Meow')]"))).release().perform();
            }else{
                driver.findElement(By.xpath("//android.widget.TextView[(@resource-id='com.evernote:id/title') and (@text='Meow')]")).click();
            }
            
        }
    }

    @When("^I ([^\"]*) a handwriting$")
    public void I_something_a_handwriting(String arg) throws InterruptedException {
        Dimension dimensions = driver.manage().window().getSize();
        int screenWidth = dimensions.getWidth();
        int screenHeight = dimensions.getHeight();
        if (arg.equals("create")) {
            driver.findElement(By.xpath("//android.widget.TextView[(@resource-id='com.evernote:id/msl_label') and (@text='Handwriting')]")).click();

            int widthSection = screenWidth / 5;
            int heightSection = screenHeight / 6;
            
            Thread.sleep(1000);
            TouchAction obj = new TouchAction(driver);
            obj.press(widthSection, heightSection*2).waitAction();
            obj.moveTo(widthSection*2, 0).waitAction().release().perform();
            
            obj.press(widthSection,0).waitAction();
            obj.moveTo(widthSection * -2, heightSection).waitAction().release().perform();
            
            obj.press(-widthSection,heightSection/2).waitAction();
            obj.moveTo(widthSection*2, 0).waitAction().release().perform();
            
            driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
            driver.findElement(By.xpath("//android.widget.Button[(@resource-id='com.evernote:id/action_button') and (@text='Got it!') ]")).click();
            driver.swipe(widthSection, heightSection * 2, widthSection, heightSection * 4, 2000);

            driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.evernote:id/title']")).sendKeys("Meow");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.evernote:id/check_mark']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.evernote:id/check_mark']")).click();
        } else if (arg.equals("edit")) {
            driver.findElement(By.xpath("//android.widget.Image")).click();
            
            int widthSection = screenWidth / 5;
            int heightSection = screenHeight / 6;
            
            Thread.sleep(1000);
            TouchAction obj = new TouchAction(driver);
            obj.press(widthSection, heightSection *2).waitAction();
            obj.moveTo(widthSection * 2, heightSection ).waitAction().release().perform();
            
            Thread.sleep(3000);
            driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
            
            driver.findElement(By.xpath("//android.widget.TextView[@text='e']")).click();
            
            //obj.press(driver.findElement(By.xpath("//android.view.View[@resource-id='en-note']"))).release().perform().press(driver.findElement(By.xpath("//android.view.View[@resource-id='en-note']"))).release().perform();
            driver.findElement(By.xpath("//android.view.View[@resource-id='en-note']")).sendKeys("Meow v2");
            
            Thread.sleep(2000);
            driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.evernote:id/check_mark']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.evernote:id/check_mark']")).click();
        } else if (arg.equals("delete")) {
            driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.evernote:id/search']")).click();
            driver.findElementById("android:id/button1").click();
        }
    }

    @Then("^it will ([^\"]*) the handwriting$")
    public void it_will_something_the_handwriting(String arg) {
        if (arg.equals("create")) {
            Assert.assertEquals(true, driver.findElement(By.xpath("//android.widget.TextView[(@resource-id='com.evernote:id/title') and (@text='Meow')]")).isDisplayed());
        } else if (arg.equals("edit")) {
            Assert.assertEquals(true, driver.findElement(By.xpath("//android.widget.TextView[(@resource-id='com.evernote:id/content') and contains(@text,'Meow v2')]")).isDisplayed());
        } else if (arg.equals("delete")) {
            Assert.assertEquals(true, driver.findElements(By.id("com.evernote:id/content")).isEmpty());
        }
    }
}
