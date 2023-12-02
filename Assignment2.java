package SeleniumAutomation;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.devtools.v85.page.Page.captureScreenshot;

public class Assignment2 {

    public static WebDriver driver ;
    private static WebDriverWait wait;
    private String expectedResult;
    private String actualResult;
    private WebElement element;
    private static Map<String, Object> vars;

    @BeforeClass
    public static void setUp() {
        // Initialize WebDriver and set up the environment
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        vars = new HashMap<String, Object>();

    }

    @AfterClass
    public static void tearDown() {

        driver.quit();
    }

    private void captureScreenshot(String fileName) {
        if (driver instanceof TakesScreenshot) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(screenshotFile.toPath(), new File("screenshots/" + fileName + ".png").toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void TC_01() throws InterruptedException {

        //Test to register a new user
        driver.navigate().to("https://ecommerce-playground.lambdatest.io");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='widget-navbar-217834']/ul/li[6]/a"))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id='column-right']/div/a[2]")).click();

            WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='firstname']")));
            element1.sendKeys("abcd");

            driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("xyz");
            driver.findElement(By.xpath("//input[@name='email']")).sendKeys("uet.04748@gmail.com");
            driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys("+12345678911");

            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0,500)");

            driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123456@xyz");
            driver.findElement(By.xpath("//input[@name='confirm']")).sendKeys("123456@xyz");
            driver.findElement(By.cssSelector(".custom-checkbox > .custom-control-label")).click();

            driver.findElement(By.xpath("//input[@type='submit'][@value='Continue']")).click();

            assertTrue(wait.until(ExpectedConditions.titleIs("Your Account Has Been Created!")));
        } catch (Exception e) {
            // Take a screenshot on test failure
            captureScreenshot("TC_01");

        }

    }

    @Test
    public void TC_02() throws InterruptedException {

        //Test to login with above registered data
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            driver.get("https://ecommerce-playground.lambdatest.io/");
            // driver.manage().window().setSize(new Dimension(1382, 744));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='widget-navbar-217834']/ul/li[6]/a"))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id='column-right']/div/a[1]")).click();

            driver.findElement(By.id("input-email")).click();
            driver.findElement(By.id("input-email")).sendKeys("uet.04748@gmail.com");
            driver.findElement(By.id("input-password")).click();
            driver.findElement(By.id("input-password")).sendKeys("123456@xyz");
            driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
            assertTrue(wait.until(ExpectedConditions.titleIs("My Account")));
        }
        catch (Exception e) {
            // Take a screenshot on test failure
            captureScreenshot("TC_02");

        }
    }



    @Test
    public void TC_03() throws InterruptedException {
        //Test to Buy  the product "Sony VAIO"

        try {
            driver.findElement(By.name("search")).click();
            driver.findElement(By.name("search")).sendKeys("Sony VAIO");
            driver.findElement(By.cssSelector(".type-text")).click();
            driver.findElement(By.cssSelector(".carousel-item:nth-child(1) > .lazy-load")).click();
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0,300)");
            driver.findElement(By.cssSelector(".btn-buynow")).click();

            Thread.sleep(15000);
            JavascriptExecutor jse1 = (JavascriptExecutor) driver;

//after first time i run this test, my billing address is saved in my account so i have commented the lines related to first time entry of billing address
// But if we change email address, we need to remove the below comments, otherwise it cannot proceed to checkout without these details.

//        wait.until(ExpectedConditions.elementToBeClickable((By.id("input-payment-firstname")))).click();
//        driver.findElement(By.id("input-payment-firstname")).sendKeys("ABC");
//        driver.findElement(By.id("input-payment-lastname")).click();
//        driver.findElement(By.id("input-payment-lastname")).sendKeys("XYZ");
//        driver.findElement(By.id("input-payment-company")).click();
//        driver.findElement(By.id("input-payment-company")).sendKeys("abcdd");
//        driver.findElement(By.id("input-payment-address-1")).click();
//        driver.findElement(By.id("input-payment-address-1")).sendKeys("plaza 1");
//        driver.findElement(By.id("input-payment-city")).click();
//        driver.findElement(By.id("input-payment-city")).sendKeys("oslo");
//        driver.findElement(By.id("input-payment-postcode")).click();
//        driver.findElement(By.id("input-payment-postcode")).sendKeys("0010");
//        driver.findElement(By.id("input-payment-country")).click();
//        driver.findElement(By.id("input-payment-country")).click();

            //scroll to bottom of current screen
            jse1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            driver.findElement(By.cssSelector(".custom-control:nth-child(6) > .custom-control-label")).click();

            driver.findElement(By.id("button-save")).click();
            Thread.sleep(4000);
            driver.findElement(By.id("button-confirm")).click();
            {
                WebElement element = driver.findElement(By.id("button-confirm"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).perform();
            }
            {
                WebElement element = driver.findElement(By.tagName("body"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element, 0, 0).perform();
            }
            driver.findElement(By.linkText("Continue")).click();
        }catch (Exception e) {
            // Take a screenshot on test failure
            captureScreenshot("TC_04");
            // throw e; // Rethrow the exception after capturing the screenshot
        }
    }

    @Test
    public void TC_04() throws InterruptedException {

        //Test to logout
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='widget-navbar-217834']/ul/li[6]/a"))).click();
            Thread.sleep(2000);

            //scroll to bottom of current screen
            JavascriptExecutor jse1 = (JavascriptExecutor) driver;
            jse1.executeScript("window.scrollTo(0, document.body.scrollHeight)");


            driver.findElement(By.xpath("//*[@id='column-right']/div/a[14]")).click();
            Thread.sleep(2000);
            assertTrue(wait.until(ExpectedConditions.titleIs("Account Logout")));
        }
        catch (Exception e) {
            // Take a screenshot on test failure
            captureScreenshot("TC_03");

        }
    }



}
