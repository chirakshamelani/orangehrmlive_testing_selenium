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

public class TestCase002 {

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
    public void navigateproduct() throws InterruptedException {
        System.out.println(".......T002  Start");
        login();
        // Verify navigation to the product page
        expectedTest = "Products";
        actualText = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();


        if (expectedTest.equals(actualText)) {
            System.out.println("Navigate to the product page");

        } else {
            System.out.println("Not navigate to the correctlt for product page");

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



    public void snapshot(){

        driver.findElement(By.xpath(""));

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
