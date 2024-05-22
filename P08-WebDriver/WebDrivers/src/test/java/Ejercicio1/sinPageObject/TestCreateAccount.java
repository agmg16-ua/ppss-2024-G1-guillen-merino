package Ejercicio1.sinPageObject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCreateAccount {

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
    @Tag("OnlyOnce")
    public void S1_scenario_createAccount_should_create_new_account_in_the_demo_store_when_this_account_does_not_exist() {
        //Comprobar que el nombre de la pagina es correcto
        Assertions.assertTrue(driver.getTitle().contains("Madison Island"));

        //Acceder a Log In
        WebElement account = driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a"));
        account.click();
        WebElement login = driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a"));
        login.click();

        //Verificar nombre de pagina Login
        Assertions.assertTrue(driver.getTitle().contains("Customer Login"));

        //Acceder a Create Account
        WebElement createAccount = driver.findElement(By.cssSelector("#login-form > div > div.col-1.new-users > div.buttons-set > a"));
        createAccount.click();

        //Verificar pagina Create Account
        Assertions.assertTrue(driver.getTitle().contains("Create New Customer Account"));

        //Rellenar datos formulario
        driver.findElement(By.name("firstname")).sendKeys("Alejandro");
        driver.findElement(By.name("lastname")).sendKeys("Guillen Merino");
        driver.findElement(By.name("email")).sendKeys("agmg16@gcloud.ua.es");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button")).submit();

        //Verificar mensaje de error
        Assertions.assertTrue(driver.findElement(By.cssSelector("#advice-required-entry-confirmation")).getText().contains("This is a required field."));

        //Completar informacion
        driver.findElement(By.name("confirmation")).sendKeys("123456");
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button")).submit();

        //Volver a la pagina anterior
        driver.navigate().back();

        //Comprobar nombre de la pagina
        Assertions.assertTrue(driver.getTitle().contains("My Account"));
    }
}
