package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.commons.io.FileUtils;

import javax.swing.plaf.PanelUI;
import java.io.File;

public class AdminUser {

//globale variable
    String  Baseurl ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" ;
    WebDriver driver;
    String actualtext;
    String expectedtest;


//beforetest
    @BeforeTest

    public void Beforetest(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();

    }

    //testcase
@Test(priority = 1)
public void  searchbyusername() throws Exception {
System.out.println(".......T001  Start");

    //login ti the system
    userlogin();

     //select admin menu
    adminclick();

    //send user name
    driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")).sendKeys("Admin");

    //click the search button
    Search();
    Thread.sleep(1000);

    //expected result
    expectedtest="Admin";
    actualtext= driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div/div/div/div/div[2]/div[1]/div/div[2]")).getText();

    System.out.println("this is actual result:"+actualtext);

    //Better way to implemenyt using assert

    if (actualtext.equals(expectedtest)){

        System.out.println("sussces test001");
        System.out.println("Admin Search for system user successful");
        takespanshot(driver,"C:\\Users\\melanid\\Downloads\\zoom\\practical\\screencorrect.png");
    }
    else{
        System.out.println("unsucces test001");
        takespanshot(driver,"C:\\Users\\melanid\\Downloads\\zoom\\practical\\unsucces.png");
    }
}
    @Test (priority = 2)
    public void searchByNonExistingUsername() throws Exception {


        resetbutton();


        System.out.println("---------------TC 002---------------");


        //send the username
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")).sendKeys("Chandana");


        //click the search button
        resetbutton();


        //Verify if the filter result is correct
        expectedtest="No Records Found";
        actualtext = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span")).getText();
        if (expectedtest.equals(actualtext))
        {
            System.out.println("TC 002: PASS");
            System.out.println("Admin search for system non existing user by username is done successfully");
            takespanshot(driver, "C:\\Users\\Dell\\Desktop\\New folder\\SearchByUsernameError.png");
        }
        else
        {
            System.out.println("TC 002: FAIL");
            System.out.println("TC 002: Screen Image Captured");
            takespanshot(driver, "C:\\Users\\Dell\\Desktop\\New folder\\SearchByNonExistingUsernameError.png");
        }
        System.out.println("------------------------------------");


        //Click reset button
        resetbutton();
    }


    //aftertest
    @AfterTest
public void Aftertest(){



}
//Supportive method

    public void userlogin() throws InterruptedException {
        //caling orangeHRM
        driver.get(Baseurl);
        Thread.sleep(3000);

        //Indentify the username text box and send value
        driver.findElement(By.name("username")).sendKeys("Admin");

        driver.findElement(By.name("password")).sendKeys("admin123");

        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();;

        Thread.sleep(3000);
    }

    public void adminclick(){

        driver.findElement(By.xpath("//*[@id=\\\"app\\\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a")).click();;
   expectedtest="System Users";
   actualtext=driver.findElement(By.xpath("//*[@id=\\\"app\\\"]/div[1]/div[2]/div[2]/div/div[1]/div[1]/div[1]/h5")).getText();

if (expectedtest.equals(actualtext)){
    System.out.println("user click on admin submenu");

}
else {
    System.out.println("unsuccesfull");

}
    }

    public void resetbutton() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[1]")).click();
        Thread.sleep(3000);
    }
    public void Userrolldownselect() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]"));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]"));
         Thread.sleep(1000);

    }

    public void Search(){

        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")).click();


    }
    public void  employeeename() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div/input")).sendKeys("Timothy Lewis Amiano");

        //Wait for drop down come
        Thread.sleep(1000);

        //select the name suggestion
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div/div[2]"));
        Thread.sleep(1000);


    }
    //status dropdown
    public void statusdropdown() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[1]"));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[2]"));
        Thread.sleep(1000);

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
