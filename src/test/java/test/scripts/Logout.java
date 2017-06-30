package test.scripts;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import test.testRunner;

public class Logout {

    private AndroidDriver driver;
    private Wait<WebDriver> wait;
    private static Boolean firstTime = true;

    @Before
    public void startApp() throws MalformedURLException {
        driver = testRunner.driver;
        wait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
    }

    @Given("^a user already logged in$")
    public void user_logged_in() {

    }

    @When("^I go to settings$")
    public void I_go_to_settings() {
        //Locator settings Home PAge
        driver.findElementById("com.evernote:id/overflow_icon").click();

        //Locator de Ajustes menu
        driver.findElement(By.xpath("//android.widget.TextView[(@resource-id='com.evernote:id/title') and (@text='Settings')]")).click();
    }

    @And("^go to account information$")
    public void I_go_to_account() {
        //Locator de account info menu
        driver.findElement(By.xpath("//android.widget.TextView[(@resource-id='com.evernote:id/title') and (@text='Account Info')]")).click();

    }

    @And("^tap logout")
    public void logout_button() {
        List<MobileElement> elements = driver.findElements(By.id("android:id/title"));
        try {
            while (true) {
                for (MobileElement element : elements) {
                    if (element.getText().equals("Sign Out")) {
                        element.click();
                        break;
                    }
                }
                driver.swipe(500, 1900, 500, 200, 5000);
            }
        } catch (Throwable t) {
        }

    }

    @Then("^I can logout successfully")
    public void I_logout() {
        //Confirm logout
        driver.findElementById("android:id/button1").click();
    }
}
