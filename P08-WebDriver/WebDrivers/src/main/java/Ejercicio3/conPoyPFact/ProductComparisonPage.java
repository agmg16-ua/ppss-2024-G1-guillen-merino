package Ejercicio3.conPoyPFact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductComparisonPage {

    WebDriver driver;

    String myHandleId;

    String myHandleIdFrom;

    @FindBy(css="body > div > div.buttons-set > button") WebElement buttonClose;

    public ProductComparisonPage(WebDriver driver) {

        this.driver = driver;
    }

    public void setMyHandleIdFrom(String myHandleIdFrom) {
        this.myHandleIdFrom = myHandleIdFrom;
    }

    public ShoesPage closeWindow() {
        buttonClose.click();
        driver.switchTo().window(myHandleIdFrom);
        return PageFactory.initElements(driver, ShoesPage.class);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
