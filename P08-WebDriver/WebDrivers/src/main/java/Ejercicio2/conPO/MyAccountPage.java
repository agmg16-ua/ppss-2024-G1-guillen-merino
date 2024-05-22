package Ejercicio2.conPO;

import org.openqa.selenium.WebDriver;

public class MyAccountPage {

    WebDriver driver;

    String pageTitle;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        pageTitle = driver.getTitle();
    }

    public String getPageTitle() {
        return pageTitle;
    }
}
