package StandardSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Headless_Execution {

    public static void main(String[] args) {
        WebDriver driver ;

        EdgeOptions options=new EdgeOptions();
        options.addArguments("--headless=new");

        driver=new EdgeDriver(options);
        driver.get("https://www.instagram.com/accounts/login/?hl=en");
        System.out.println("Title is  " +driver.getTitle());


    }
}
