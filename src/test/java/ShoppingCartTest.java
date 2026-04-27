import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class ShoppingCartTest {

    WebDriver driver;


    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=2&zip_code=12345&first_name=test&last_name=test&email=test%40test.test" +
                "&password1=12345678&password2=12345678");
        String email = driver.findElement(By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b")).getText();
        String password = "1111";

        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value=Login]")).click();
        Boolean isDisplayed = driver.findElement(By.cssSelector(".user")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        driver.get("https://www.sharelane.com/cgi-bin/add_to_cart.py?book_id=1");
        driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
        driver.findElement(By.name("q")).clear();
    }

    @Test
    public void checkDiscountZero(){
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.name("q")).sendKeys("19");
        driver.findElement(By.cssSelector("[value=Update]")).click();

        String discountPercent = driver.findElement
                (By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$ = driver.findElement
                (By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String total = driver.findElement
                (By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();

        softAssert.assertEquals(discountPercent, "0");
        softAssert.assertEquals(discount$, "0.0");
        softAssert.assertEquals(total, "190.0");
        softAssert.assertAll();
    }

    @Test
    public void checkDiscountTwo(){
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.name("q")).sendKeys("30");
        driver.findElement(By.cssSelector("[value=Update]")).click();

        String discountPercent = driver.findElement
                (By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$ = driver.findElement
                (By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String total = driver.findElement
                (By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();

        softAssert.assertEquals(discountPercent, "2");
        softAssert.assertEquals(discount$, "6.0");
        softAssert.assertEquals(total, "294.0");
        softAssert.assertAll();
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
