package Ejercicio2.conPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class HomePage {
    WebDriver driver;
    WebElement account;
    WebElement login;
    String pageTitle;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://demo-store.seleniumacademy.com/");
        account = driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a"));
        pageTitle = driver.getTitle();
    }

    public CustomerLoginPage goToLogin() {
        this.account.click();
        this.login = driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a"));
        this.login.click();
        return new CustomerLoginPage(driver);
    }

    public String getPageTitle() {
        return pageTitle;
    }
}
