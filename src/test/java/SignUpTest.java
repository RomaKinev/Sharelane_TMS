import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignUpTest {

    @Test
    public void checkSignUpFormWithValidData(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Continue]")).click();

        Boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        driver.findElement(By.name("first_name")).sendKeys("Test");
        driver.findElement(By.name("last_name")).sendKeys("Test");
        driver.findElement(By.name("email")).sendKeys("test@test.by");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.name("password2")).sendKeys("123456");
        driver.findElement(By.cssSelector("[value=Register]")).click();

        String actualMessage = driver.findElement(By.xpath("//span[contains(text(), 'Account is created')]")).getText();
        Assert.assertEquals("Account is created!", actualMessage);
        driver.close();
    }

    @Test
    public void checkSignUpFormWithoutData(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        Boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        driver.findElement(By.cssSelector("[value=Register]")).click();
        Boolean errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).isDisplayed();
        Assert.assertTrue(errorMessage);
        driver.close();
    }

    @Test
    public void checkSignUpFormWithoutConfirmPassword(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        Boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        driver.findElement(By.name("first_name")).sendKeys("Test");
        driver.findElement(By.name("last_name")).sendKeys("Test");
        driver.findElement(By.name("email")).sendKeys("test@test.by");
        driver.findElement(By.name("password1")).sendKeys("123456");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        Boolean errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).isDisplayed();
        Assert.assertTrue(errorMessage);
        driver.close();
    }
}
