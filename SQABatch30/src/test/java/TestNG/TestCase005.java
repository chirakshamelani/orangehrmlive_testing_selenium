package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class TestCase005 {
    //globale variable
    String  Baseurl ="https://www.saucedemo.com/" ;
    WebDriver driver;
    String actualText;
    String expectedTest;

    @BeforeTest
    public void BeforeTest() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }
    @Test(priority = 5)
    public void enterinValidEmpty() throws InterruptedException {
        login();

// Navigate to the cart page and proceed to checkout step one
        commonlink();

        // Fill out the fields and proceed
        driver.findElement(By.id("first-name")).clear();
        driver.findElement(By.id("last-name")).clear();
        driver.findElement(By.id("postal-code")).clear();
        driver.findElement(By.id("continue")).click();

        // Verify error message
        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));

        if (errorMessage.isDisplayed()) {
            System.out.println("Validation Message: " + errorMessage.getText());
        } else {
            System.out.println("No validation message is displayed.");
        }

    }
    @AfterTest
    public void Aftertest(){

        // Close the browser
        driver.quit();
    }


    public void login() throws InterruptedException {

        driver.get(Baseurl);
        Thread.sleep(2000);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }


    public void commonlink() throws InterruptedException {

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(2000);
    }

    public void enterValidDetailsAndContinue()  {
        // Fill out the fields and proceed
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        System.out.println("Valid data are insterted");


    }
}
