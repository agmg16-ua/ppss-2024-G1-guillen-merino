package Ejercicio1.sinPageObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class TestLogin {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions co = new ChromeOptions();
        co.setBrowserVersion("119");
        driver = new ChromeDriver(co);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://demo-store.seleniumacademy.com/");
    }

    @AfterEach
    public void toClose() {
        driver.quit();
    }

    @Test
    public void S2_scenario_loginOK_should_login_with_success_when_user_account_exists() {
        //Comprobar que la pagina ha cargado
        Assertions.assertTrue(driver.getTitle().contains("Madison Island"));

        //Acceder a la pagina de Log In
        WebElement account = driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a"));
        account.click();
        WebElement logIn = driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a"));
        logIn.click();

        //Verificar nombre de pagina Login
        Assertions.assertTrue(driver.getTitle().contains("Customer Login"));

        //Rellenar solo email y submit
        driver.findElement(By.name("login[username]")).sendKeys("agmg16@gcloud.ua.es");
        driver.findElement(By.cssSelector("#send2")).submit();

        //Comprobar mensaje de error
        Assertions.assertTrue(driver.findElement(By.cssSelector("#advice-required-entry-pass")).getText().contains("This is a required field."));

        //Introducir contraseña y reenviar
        driver.findElement(By.name("login[password]")).sendKeys("123456");
        driver.findElement(By.cssSelector("#send2")).submit();

        //Comprobar que la pagina es correcta
        Assertions.assertTrue(driver.getTitle().contains("My Account"));
    }

    @Test
    public void S3_scenario_loginFailed_should_fail_when_user_account_not_exists() {
        //Comprobar que la pagina ha cargado
        Assertions.assertTrue(driver.getTitle().contains("Madison Island"));

        //Acceder a la pagina de Log In
        WebElement account = driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a"));
        account.click();
        WebElement logIn = driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a"));
        logIn.click();

        //Verificar nombre de pagina Login
        Assertions.assertTrue(driver.getTitle().contains("Customer Login"));

        //Introducir contraseña erronea
        driver.findElement(By.name("login[username]")).sendKeys("agmg16@gcloud.ua.es");
        driver.findElement(By.name("login[password]")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#send2")).submit();

        //Verificar mensaje de error
        Assertions.assertTrue(driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > ul > li > ul > li > span")).getText().contains("Invalid login or password."));



    }

}
