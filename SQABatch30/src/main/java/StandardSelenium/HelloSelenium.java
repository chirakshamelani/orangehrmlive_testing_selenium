package StandardSelenium;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.edge.EdgeDriver;

public class HelloSelenium {
    public static void main(String[] args) {

        //1.create web driver for manage web browser.this is null response

        WebDriver driver;

        //2.we have to webdriver assighn to browser(chorme/wirefox)

        driver =new EdgeDriver();


        //fit the browser
        driver.manage().window().maximize();

        //calling the url
        driver.get("https://www.microsoft.com/en-us/sql-server/sql-server-2022");


    }
}
