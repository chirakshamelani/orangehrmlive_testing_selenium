package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestCase007 {


    //globale variable
    String Baseurl = "https://www.saucedemo.com/";
    WebDriver driver;
    String actualText;
    String expectedTest;

    @BeforeTest
    public void BeforeTest() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }


    @Test(priority = 7)
    public void verifycancelbutton() throws InterruptedException {
        login();
        commonlink();
        enterValidDetailsAndContinue();
// Verify navigation
        String actualUrl = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        String expectedUrl = "Checkout: Overview";

        if (actualUrl.equals(expectedUrl)) {
            System.out.println("PASS: Checkout button navigated to the  orview  page");

        } else {
            System.out.println("navigate is incorrect");
        }
        // Locate and click the Cancel button
        WebElement cancelButton = driver.findElement(By.id("cancel"));
        cancelButton.click();

        // Verify navigation back to the cart page
        String actualone = driver.getCurrentUrl();
        String expectone = "https://www.saucedemo.com/inventory.html";

        if (actualone.equals(expectone)) {
            System.out.println("when click cancel button navigate to inventory page again");
        }}
        @AfterTest
        public void Aftertest () {

            // Close the browser
            driver.quit();
        }


        public void login () throws InterruptedException {

            driver.get(Baseurl);
            Thread.sleep(2000);

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
        }



        public void commonlink () throws InterruptedException {

            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
            driver.findElement(By.className("shopping_cart_link")).click();
            driver.findElement(By.id("checkout")).click();
            Thread.sleep(2000);
        }

        public void enterValidDetailsAndContinue () {
            // Fill out the fields and proceed
            driver.findElement(By.id("first-name")).sendKeys("John");
            driver.findElement(By.id("last-name")).sendKeys("Doe");
            driver.findElement(By.id("postal-code")).sendKeys("12345");
            driver.findElement(By.id("continue")).click();
            System.out.println("Valid data are insterted");


        }
    }
