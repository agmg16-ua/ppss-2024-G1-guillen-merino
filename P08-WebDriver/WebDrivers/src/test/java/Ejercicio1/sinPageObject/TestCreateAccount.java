package Ejercicio1.sinPageObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.WebElement;

public class TestCreateAccount {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions co = new ChromeOptions();
        co.setBrowserVersion("119");
        driver = new ChromeDriver(co);
    }

    @AfterEach
    public void toClose() {
        driver.quit();
    }


    @Test
    @Tag("OnlyOnce")
    public void S1_scenario_createAccount_should_create_new_account_in_the_demo_store_when_this_account_does_not_exist() {
        driver.get("http://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Packt Publising");
        searchBox.submit();
    }
}
