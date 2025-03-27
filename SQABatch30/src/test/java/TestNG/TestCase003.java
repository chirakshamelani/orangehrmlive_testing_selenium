package TestNG;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
public class TestCase003 {

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
public void cart() throws InterruptedException {
        System.out.println(".......T003 Start");
  login();
// Add an item to the cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

    // Navigate to the cart page
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

    // Verify the cart page title
    String expectedTitle ="https://www.saucedemo.com/cart.html";
    String actualTitle = driver.getCurrentUrl();

        if (expectedTitle.equals(actualTitle)) {
        System.out.println("navigate to the your cart page");

    }
        else {
        System.out.println("unsuccesfull to  navigate to your cart page");
        System.out.println("Expected: " + expectedTitle);
        System.out.println("Actual: " + actualTitle);

    }
    ;
    //Verify Order Deatils
    List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        System.out.println("Total items in cart: " + cartItems.size());

        for (int i = 0; i < cartItems.size(); i++) {
        WebElement cartItem = cartItems.get(i);

        // Verify quantity
        WebElement qtyElement = cartItem.findElement(By.className("cart_quantity"));
        String qtyText = qtyElement.getText();
        if (qtyText.equals("1")) {
            System.out.println("Item " + (i + 1) + " quantity: " + qtyText + " - PASS");
        } else {
            System.out.println("Item " + (i + 1) + " quantity is incorrect: " + qtyText + " - FAIL");
        }
        // Verify description
        WebElement descriptionElement = cartItem.findElement(By.className("inventory_item_name"));
        String descriptionText = descriptionElement.getText();
        if (!descriptionText.isEmpty()) {
            System.out.println("Item " + (i + 1) + " description: " + descriptionText + " - PASS");
        } else {
            System.out.println("Item " + (i + 1) + " description missed - FAIL");
        }

        // Verify price
        WebElement priceElement = cartItem.findElement(By.className("inventory_item_price"));
        String priceText = priceElement.getText();
        if (priceText.matches("\\$\\d+\\.\\d{2}")) {
            // Validates price format
            System.out.println("Item " + (i + 1) + " price: " + priceText + " - PASS");
        }
        else {
            System.out.println("Item " + (i + 1) + " price is invalid: " + priceText + " - FAIL");

        }
    }

        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
    //  after clicking the "Continue Shopping" button
        driver.findElement(By.id("continue-shopping")).click();

    String actualUrl = driver.getCurrentUrl();
    String expectedUrl = "https://www.saucedemo.com/inventory.html";
        if (actualUrl.equals(expectedUrl)) {
        System.out.println("PASS:  button navigated to the shopping cart page");
    }
        else {
        System.out.println("FAIL:  button did not navigate to the shopping step one page");
        System.out.println("Expected: " + expectedUrl);
        System.out.println("Actual: " + actualUrl);

    }
    // Validate the URL after clicking the "Checkout" button
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
    String actualUrl1 = driver.getCurrentUrl();
    String expectedUrl1 = "https://www.saucedemo.com/checkout-step-one.html";

        if (actualUrl1.equals(expectedUrl1)) {
        System.out.println("PASS: Checkout button navigated to the checkout your information page");
    }
        else {
        System.out.println("FAIL: Checkout button did not navigate to the checkout step one page");
        System.out.println("Expected: " + expectedUrl1);
        System.out.println("Actual: " + actualUrl1);
    }
        System.out.println(".......T003 End");

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
    }}
