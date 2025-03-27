package TestNG;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase009 {

    //globale variable
    String  Baseurl ="https://www.saucedemo.com/" ;
    WebDriver driver;


    @BeforeTest
    public void BeforeTest(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();

    }
    @Test
    public void testInvalidUsernameAndPassword() {
        driver.get(Baseurl);
        // Enter invalid credentials
        driver.findElement(By.id("user-name")).sendKeys("invalid_user");
        driver.findElement(By.id("password")).sendKeys("invalid_password");

        // Click the login button
        driver.findElement(By.id("login-button")).click();

        // Verify the error message
        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        if (errorMessage.isDisplayed()){

            System.out.println("Error message is displayed for invalid credentials");
        }
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed for invalid credentials.");
        Assert.assertEquals(errorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

}
