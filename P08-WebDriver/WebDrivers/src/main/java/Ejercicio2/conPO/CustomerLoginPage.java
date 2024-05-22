package Ejercicio2.conPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class CustomerLoginPage {

    WebDriver driver;

    WebElement email;

    WebElement password;

    WebElement boton;

    WebElement msgError;

    String pageTitle;

    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
        this.email = driver.findElement(By.name("login[username]"));
        this.password = driver.findElement(By.name("login[password]"));
        this.boton = driver.findElement(By.cssSelector("#send2"));
        this.pageTitle = driver.getTitle();
    }

    public MyAccountPage loginOk(String username, String password) {
        this.email.sendKeys(username);
        this.password.sendKeys(password);
        this.boton.submit();
        return new MyAccountPage(driver);
    }

    public String loginFail(String username, String password) {
        this.email.sendKeys(username);
        this.password.sendKeys(password);
        this.boton.submit();
        this.msgError = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > ul > li > ul > li > span"));
        return this.msgError.getText();
    }

    public String getPageTitle() {
        return pageTitle;
    }
}
