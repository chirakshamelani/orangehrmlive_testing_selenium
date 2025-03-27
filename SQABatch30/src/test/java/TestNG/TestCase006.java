package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase006
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
    public void orview() throws InterruptedException {

        login();
        commonlink();
        enterValidDetailsAndContinue();
        // Verify navigation
        String actualUrl = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        String expectedUrl = "Checkout: Overview";

        if(actualUrl.equals(expectedUrl)){
            System.out.println("PASS: Checkout button navigated to the  orview  page");
        }
        else{
            System.out.println("navigate is incorrect");
        }
        // Verify the item name, quantity, and price
        ;
        WebElement itemTotal = driver.findElement(By.className("summary_subtotal_label"));
        WebElement tax = driver.findElement(By.className("summary_tax_label"));
        WebElement total = driver.findElement(By.className("summary_total_label"));

        Assert.assertTrue(itemTotal.isDisplayed(), "Item total is not displayed.");
        Assert.assertTrue(tax.isDisplayed(), "Tax is not displayed.");
        Assert.assertTrue(total.isDisplayed(), "Total price is not displayed.");


        // Extract item total, tax, and total price
        String itemTotalText = driver.findElement(By.className("summary_subtotal_label")).getText();
        String taxText = driver.findElement(By.className("summary_tax_label")).getText();
        String totalText = driver.findElement(By.className("summary_total_label")).getText();

        // Parse the numeric values
        double itemTotald = Double.parseDouble(itemTotalText.replace("Item total: $", ""));
        double tax1 = Double.parseDouble(taxText.replace("Tax: $", ""));
        double expectedTotal = itemTotald + tax1;

        double actualTotal = Double.parseDouble(totalText.replace("Total: $", ""));
        // Validate the total price
        Assert.assertEquals(actualTotal, expectedTotal, "Total price calculation is incorrect.");

        System.out.println("Total Price is correct" +total.getText());
        Thread.sleep(1000);
        driver.findElement(By.id("finish")).click();

        // Validate navigation to the checkout completion page
        String actualUrl1 = driver.getCurrentUrl();
        String expectedUrl1= "https://www.saucedemo.com/checkout-complete.html";
        if(actualUrl1.equals(expectedUrl1)){
            System.out.println("order succesfull finish");
        }
        Thread.sleep(2000);


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

    public void goToCart() {
        driver.findElement(By.className("//*[@id=\"shopping_cart_container\"]/a")).click();
    }

    public void snapshot(){

        driver.findElement(By.xpath(""));

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
