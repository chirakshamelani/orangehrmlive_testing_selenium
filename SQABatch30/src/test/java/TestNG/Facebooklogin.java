package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;


public class Facebooklogin {

@Test
    public void facbooklogin() {
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");

       // WebElement email_txt= driver.findElement(By.id("email"));
       // WebElement pwd_txt= driver.findElement(By.id("pass"));
        //WebElement Login_btn= driver.findElement(By.id("u_0_5_Qk"));

    WebElement email_txt = driver.findElement(By.name("email"));
    WebElement pwd_txt = driver.findElement(By.name("pass"));
    WebElement login_btn = driver.findElement(By.cssSelector("button[name='login']"));

    email_txt.sendKeys("melani");
        pwd_txt.sendKeys("melani87654");
        login_btn.click();



    }
}
