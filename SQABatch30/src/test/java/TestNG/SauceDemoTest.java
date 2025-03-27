package TestNG;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.util.List;

public class SauceDemoTest {

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

    @Test(priority = 1)
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

    @Test(priority = 2)
    public void product() throws InterruptedException {
        System.out.println(".......T002  Start");
        login();
     // Verify navigation to the product page
    expectedTest = "Products";
    actualText = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();


    if (expectedTest.equals(actualText)) {
        System.out.println("Navigate to the product page");
        Thread.sleep(3000);

    } else {
        System.out.println("Not navigate to the correctlt for product page");
        Thread.sleep(3000);

    }

        // Locate all product containers
        List<WebElement> productContainers = driver.findElements(By.className("inventory_item"));
        Assert.assertTrue(productContainers.size() > 0, "No products found on the inventory page.");

        // Iterate through each product and verify details
        for (WebElement product : productContainers) {
            // Verify product name
            WebElement productName = product.findElement(By.className("inventory_item_name"));
            Assert.assertFalse(productName.getText().isEmpty(), "Product name is missing.");

            // Verify product description
            WebElement productDescription = product.findElement(By.className("inventory_item_desc"));
            Assert.assertFalse(productDescription.getText().isEmpty(), "Product description is missing.");

            // Verify product price
            WebElement productPrice = product.findElement(By.className("inventory_item_price"));
            Assert.assertTrue(productPrice.getText().startsWith("$"), "Product price is invalid.");

            // Print product details for debugging
            System.out.println("Product Name: " + productName.getText());
            System.out.println("Description: " + productDescription.getText());
            System.out.println("Price: " + productPrice.getText());
            System.out.println("--------------------------------");
            Thread.sleep(3000);
        }
        // Locate the first product's "Add to cart" button
        WebElement addToCartButton = driver.findElement(By.name("add-to-cart-sauce-labs-backpack"));
        String expectedInitialText = "Add to cart";
        String actualInitialText = addToCartButton.getText();
        if (actualInitialText.equals(expectedInitialText)) {
            System.out.println("  Button is correctly click Add to cart");

        } else {
            System.out.println(" Button  is incorrect. Expected: '" + expectedInitialText + "', Actual: '" + actualInitialText + "'");
        }

        //  Click the "Add to cart" button
        addToCartButton.click();

        // Verify the button  changes to "Remove"
        String expectedAfterClick = "Remove";
        String actualAfterClick = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]")).getText();
        if (actualAfterClick.equals(expectedAfterClick)) {
            System.out.println("After click: Button text is correctly change to Remove.");
        } else {
            System.out.println("After click: Button text is incorrect. Expected: '" + expectedAfterClick + "', Actual: '" + actualAfterClick + "'");
        }
        System.out.println(".......T002  End");
}

    @Test(priority = 3)
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

    @Test(priority = 4)
    public void  detailinfo() throws InterruptedException {

        login();
        commonlink();
        // Fill out the fields and proceed
        enterValidDetailsAndContinue();
        //Verify navigation back to the cart
    String actualUrl = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
    String expectedUrl = "Checkout: Overview";

    if(actualUrl.equals(expectedUrl)){
        System.out.println("PASS: Checkout button navigated to the  orview  page");

    }
    else{
        System.out.println("navigate is incorrect");
    }
    Thread.sleep(1000);
    }

    @Test(priority = 5)
    public void enterValidEmpty() throws InterruptedException {
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

    @Test(priority = 6)
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


    @Test(priority = 7)
    public void verifycancelbutton() throws InterruptedException {
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
        // Locate and click the Cancel button
        WebElement cancelButton = driver.findElement(By.id("cancel"));
        cancelButton.click();

        // Verify navigation back to the cart page
        String actualone = driver.getCurrentUrl();
        String expectone = "https://www.saucedemo.com/inventory.html";

        if (actualone.equals(expectone)){
            System.out.println("when click cancel button navigate to inventory page again");
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
    public static void takespanshot(WebDriver webdriver,String filWithPath )throws Exception{
        //copver webdriver object to take screenshot
        TakesScreenshot srcshot=((TakesScreenshot)webdriver);

        //call get screen shot to create file
        File srcfile =srcshot.getScreenshotAs(OutputType.FILE);

        //move image file destination path
        File desfile=new File(filWithPath);

        //copy file at destination path
        FileUtils.copyFile(srcfile,desfile);

    }
}
