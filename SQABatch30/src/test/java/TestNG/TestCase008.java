package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestCase008

{

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
    @Test
    public void verifyLogout() throws InterruptedException {
        // verify login

        driver.get(Baseurl);
        Thread.sleep(3000);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Verify navigation to the product page
        expectedTest = "Products";
        actualText = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();


        if (expectedTest.equals(actualText)) {
            System.out.println("Navigate to the product page");

        } else {
            System.out.println("Not navigate to the correctlt for product page");

        }
        Thread.sleep(2000);
        //  Open the menu
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();

        //  Click on the Logout button
        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();

        // Verify user is redirected to the login page
        WebElement loginPageButton = driver.findElement(By.id("login-button"));

        if (loginPageButton.isDisplayed()){System.out.println("correctly  logout and navigate to login page");}


        Assert.assertTrue(loginPageButton.isDisplayed(), "Logout failed, user not redirected to login page!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Incorrect redirection after logout!");

    }
        @AfterTest
        public void Aftertest(){

            // Close the browser
            driver.quit();
        }




    }


