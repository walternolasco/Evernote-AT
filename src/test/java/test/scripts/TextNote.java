package test.scripts;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import static test.testRunner.driver;

/**
 *
 * @author elco45
 */
public class TextNote {

    private Wait<WebDriver> wait;
    private static Boolean created = false;

    @Before
    public void startApp() throws MalformedURLException {
        driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
        wait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
    }

    @Given("^A user that wants to ([^\"]*) a textnote$")
    public void A_user_that_wants_to_something_a_textnote(String arg) {
        if (!created) {
            driver.findElementById("com.evernote:id/main_fab_image_view").click();
            created = true;
        } else {
            driver.findElement(By.xpath("//android.widget.TextView[(@resource-id='com.evernote:id/title') and (@text='Meow')]")).click();
        }
    }

    @When("^I ([^\"]*) a textnote$")
    public void I_something_a_textnote(String arg) throws InterruptedException {
        if (arg.equals("create")) {
            driver.findElement(By.xpath("//android.widget.TextView[(@resource-id='com.evernote:id/msl_icon_tv') and (@text='A')]")).click();
            driver.findElement(By.xpath("//android.view.View[@resource-id='en-note']")).sendKeys("Hello world");
            driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.evernote:id/title']")).sendKeys("Meow");
            driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.evernote:id/check_mark']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.evernote:id/check_mark']")).click();
        } else if (arg.equals("edit")) {
            driver.findElement(By.xpath("//android.widget.TextView[@text='e']")).click();
            driver.findElement(By.xpath("//android.view.View[@resource-id='en-note']")).clear();
            driver.findElement(By.xpath("//android.view.View[@resource-id='en-note']")).sendKeys("Meow v2");
            driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.evernote:id/check_mark']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.evernote:id/check_mark']")).click();
        } else if (arg.equals("delete")) {
            driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.evernote:id/overflow_icon']")).click();
            driver.findElement(By.xpath("//android.widget.TextView[(@resource-id='com.evernote:id/text') and (@text='Delete note')]")).click();
            driver.findElementById("android:id/button1").click();
        }
    }

    @Then("^it will ([^\"]*) the textnote")
    public void it_will_something_the_textnote(String arg) {
        if (arg.equals("create")) {
            Assert.assertEquals(true, driver.findElement(By.xpath("//android.widget.TextView[(@resource-id='com.evernote:id/title') and (@text='Meow')]")).isDisplayed());
        } else if (arg.equals("edit")) {
            Assert.assertEquals(true, driver.findElement(By.xpath("//android.widget.TextView[(@resource-id='com.evernote:id/content') and contains(@text,'Meow v2')]")).isDisplayed());
        } else if (arg.equals("delete")) {
            Assert.assertEquals(true, driver.findElements(By.id("com.evernote:id/content")).isEmpty());
        }
    }
}
