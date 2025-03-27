package TestNG;

import org.testng.annotations.Test;

public class AnnotationPiority {

public void beforetestmethod(){

System.out.println("before test execution");

}
@Test(priority =3)
public  void test1(){
    System.out.println("Test case one execution");

}
@Test(priority =2)
    public  void test2(){
        System.out.println("Test case two execution");

    }
    @Test(priority =1)
    public  void test3(){
        System.out.println("Test case three execution");

    }


}
