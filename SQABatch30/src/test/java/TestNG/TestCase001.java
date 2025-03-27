package TestNG;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase001 {


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
    public void verifyLoginPage() throws InterruptedException {
        // successful login
        login();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualedUrl=driver.getCurrentUrl();
        if (actualedUrl.equals(expectedUrl)){

            System.out.println("Login Was Successfull");
            Thread.sleep(1000);
        }
        else{

            System.out.println("Login was not successfull");
        }
    }
    @AfterTest
    public void Aftertest(){

        // Close the browser
        driver.quit();
    }


    public void login() throws InterruptedException {

        driver.get(Baseurl);
        Thread.sleep(3000);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }





}
