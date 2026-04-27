import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ZipCodeTest {

     /*
     Прекондишен: Открыть браузер
      1) Открыть страницу https://sharelane.com/cgi-bin/register.py
      2) ввести в поле Zip Code значение 12345
      3) нажать кнопку Continue
      Ожидаемый результат: Мы оказались на странице с формой регистрации
      Посткондишен: Закрыть браузер
     */

    @Test
    public void checkZipCodeFieldWithValidValue() {
        //Инициализируем браузер
        WebDriver driver = new ChromeDriver();
        //Определяем время ожидания элемента
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Переходим на страницу https://sharelane.com/cgi-bin/register.py
        driver.get("https://sharelane.com/cgi-bin/register.py");
        //Вводим значение 12345 в поле zipcode
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        //нажимаем кнопку Continue
        driver.findElement(By.cssSelector("[value=Continue]")).click();

        //создам boolean переменную, которая определяет наличие кнопки Register на странице
        Boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        //Проверяем, что кнопка действительно есть
        Assert.assertTrue(isDisplayed);
        driver.close();
    }

    @Test
    public void checkZipCodeFieldWithLessDigitsThenFive(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("1234");
        driver.findElement(By.cssSelector("[value=Continue]")).click();

        Boolean isDisplayed = driver.findElement(By.cssSelector("[class=error_message]")).isDisplayed();
        Assert.assertTrue(isDisplayed);
        driver.close();
    }
}
