package StandardSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InstergramLogin {

    private WebDriver driver;
    private final String baseUrl = "https://demoqa.com/automation-practice-form";


    @BeforeMethod
    public void BeforeTest(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Positive Test Case 1: Fill out the form with valid data and submit.
     */
    @Test
    public void testValidFormSubmission() {
        // Fill in student name
        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("lastName")).sendKeys("Doe");

        // Fill in email
        driver.findElement(By.id("userEmail")).sendKeys("john.doe@example.com");

        // Select gender
        driver.findElement(By.xpath("//label[text()='Male']")).click();

        // Fill in mobile number
        driver.findElement(By.id("userNumber")).sendKeys("9876543210");

        // Select date of birth
        driver.findElement(By.id("dateOfBirthInput")).click();
        new Select(driver.findElement(By.className("react-datepicker__month-select"))).selectByVisibleText("January");
        new Select(driver.findElement(By.className("react-datepicker__year-select"))).selectByVisibleText("1990");
        driver.findElement(By.xpath("//div[text()='10']")).click();

        // Enter subjects
        WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
        subjectsInput.sendKeys("Maths");
        subjectsInput.sendKeys("\n");

        // Select hobbies
        driver.findElement(By.xpath("//label[text()='Sports']")).click();

        // Upload file
        WebElement uploadButton = driver.findElement(By.id("uploadPicture"));
        uploadButton.sendKeys("C:\\path\\to\\sample-file.jpg");

        // Enter current address
        driver.findElement(By.id("currentAddress")).sendKeys("123 Main Street, Springfield");

        // Seect state and city
        Actions actions = new Actions(driver);
        driver.findElement(By.id("react-select-3-input")).sendKeys("NCR");
        actions.moveToElement(driver.findElement(By.id("react-select-3-input"))).sendKeys("\n").perform();
        driver.findElement(By.id("react-select-4-input")).sendKeys("Delhi");
        actions.moveToElement(driver.findElement(By.id("react-select-4-input"))).sendKeys("\n").perform();

        // Submit the form
        driver.findElement(By.id("submit")).click();

        // Validate the confirmation modal
        WebElement confirmationModal = driver.findElement(By.id("example-modal-sizes-title-lg"));
        Assert.assertTrue(confirmationModal.isDisplayed(), "Confirmation modal is not displayed.");
        Assert.assertEquals(confirmationModal.getText(), "Thanks for submitting the form");

        // Close the modal
        driver.findElement(By.id("closeLargeModal")).click();
    }
}



