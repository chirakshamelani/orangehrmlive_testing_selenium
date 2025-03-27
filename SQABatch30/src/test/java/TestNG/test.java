package TestNG;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class test {
    //globale variable
    String  Baseurl ="https://www.saucedemo.com/" ;
    WebDriver driver;
    String actualText;
    String expectedTest;

    @BeforeTest
    public void BeforeTest(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();

    }
    @Test
    public void testLogoutFunctionality() throws InterruptedException {
        // Step 1: Log in with valid credentials
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualedUrl=driver.getCurrentUrl();
        if (actualedUrl.equals(expectedUrl)){

            System.out.println("Login Was Successfull");
            Thread.sleep(1000);
        }
        else{

            System.out.println("Login was not successfull");
        }

        // Step 2: Verify user is on the Products page
        WebElement productsTitle = driver.findElement(By.className("title"));
        Assert.assertTrue(productsTitle.isDisplayed(), "Login failed, Products page not displayed!");

        // Step 3: Open the menu
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();

        // Step 4: Click on the Logout button
        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();

        // Step 5: Verify user is redirected to the login page
        WebElement loginPageButton = driver.findElement(By.id("login-button"));
        Assert.assertTrue(loginPageButton.isDisplayed(), "Logout failed, user not redirected to login page!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Incorrect redirection after logout!");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}

