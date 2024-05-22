package Ejercicio3.conPoyPFact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    WebDriver driver;

    @FindBy(css="#nav > ol > li.level0.nav-3.parent > a") WebElement accesories;

    @FindBy(css="#nav > ol > li.level0.nav-3.parent > ul > li.level1.nav-3-3 > a") WebElement shoes;


    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://demo-store.seleniumacademy.com/customer/account/");
        this.driver.manage().window().maximize();
    }

    public ShoesPage toShoesPage() {
        Actions builder = new Actions(driver);
        builder.moveToElement(accesories);
        builder.perform();
        shoes.click();
        return PageFactory.initElements(driver, ShoesPage.class);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

}
