package StandardSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class WebElementsAutomation {

    //public web driver

    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("file:///C:/Users/melanid/Downloads/zoom/Web%20elements.html");

        //caling methods
        optionbutton();
        checkboxes();
        comboboxes();
        dropdownlist();
        submitbutton();
        textbox();
        linktext();


    }

    public static void optionbutton(){

           //identify webelement
        WebElement option1= driver.findElement(By.id("vfb-7-1"));
        WebElement option2= driver.findElement(By.id("vfb-7-2"));
        WebElement option3= driver.findElement(By.id("vfb-7-3"));


        option1.click();
        if (option1.isSelected()){
            System.out.println("selected is correct");

        }
        else {

            System.out.println("select a another option");
        }

    }

    public static void checkboxes(){

        WebElement check1=driver.findElement(By.id("vfb-6-0"));
        WebElement check2=driver.findElement(By.id("vfb-6-1"));
        WebElement check3=driver.findElement(By.id("vfb-6-2"));

        check1.click();
        check2.click();
        if (check1.isSelected() && check2.isSelected()){

            System.out.println("checkbox 1 and checkbox 2 is selected");
        }

        else {

            System.out.println("select another one");
        }
    }


    //single elemnt mulitple value in combobox

     public static void  comboboxes()
     {
         Select combobox_fruits = new Select(driver.findElement(By.id("fruits")));

         combobox_fruits.selectByVisibleText("Banana");
         System.out.println("yo selected banana");

         combobox_fruits.selectByIndex(2);
         System.out.println("you selected orange");

     }


    public static void  dropdownlist() throws InterruptedException {

        Select dropdown = new Select(driver.findElement(By.xpath("/html/body/select")));

        dropdown.selectByVisibleText("ANGOLA");
        System.out.println("yo selected ANGOLA");

        Thread.sleep(3000);

       dropdown.selectByIndex(14);
        System.out.println("you selected Australiya");
    }

    public  static void  submitbutton(){

        WebElement btn1 = driver.findElement(By.name("submit"));

        btn1.click();
        System.out.println("button submitted");
    }
    public  static void  textbox(){

        WebElement txt = driver.findElement(By.name("userName"));

        txt.sendKeys("melani");

        System.out.println("melani");
    }

    public static  void linktext(){

        driver.findElement(By.xpath("/html/body/a")).click();
        System.out.println("navigate the link");

    }
    }
