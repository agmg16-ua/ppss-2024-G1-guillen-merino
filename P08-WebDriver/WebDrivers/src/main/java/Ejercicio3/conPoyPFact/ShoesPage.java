package Ejercicio3.conPoyPFact;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class ShoesPage {
    WebDriver driver;

    String myHandleId;

    String[] handleIds;

    @FindBy(css="body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(5) > div > div.actions > ul > li:nth-child(2) > a") WebElement oxford;

    @FindBy(css="body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(6) > div > div.actions > ul > li:nth-child(2) > a") WebElement navy;

    @FindBy(css="body > div > div.page > div.main-container.col3-layout > div > div.col-right.sidebar > div > div.block-content > div > button") WebElement botonCompare;

    @FindBy(linkText = "Clear All") WebElement clearAll;

    @FindBy(className = "messages") WebElement mensaje;

    public ShoesPage(WebDriver driver) {
        this.driver = driver;
        myHandleId = driver.getWindowHandle();
    };

    public void selectShoeToCompare(int number) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        switch (number) {
            case 5:
                jse.executeScript("arguments[0].scrollIntoView();", oxford);
                oxford.click();
                break;
            case 6:
                jse.executeScript("arguments[0].scrollIntoView();", navy);
                navy.click();
                break;
        }
    }

    public ProductComparisonPage compare() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", botonCompare);

        botonCompare.click();

        Set<String> setIds = driver.getWindowHandles();
        handleIds = setIds.toArray(new String[setIds.size()]);

        ProductComparisonPage comparisonPage = PageFactory.initElements(driver, ProductComparisonPage.class);
        comparisonPage.setMyHandleIdFrom(handleIds[0]);
        driver.switchTo().window(handleIds[1]);

        return comparisonPage;
    }

    public Alert clearComparisonObjects() {
        clearAll.click();
        return driver.switchTo().alert();
    }

    public String getMensaje() {
        return mensaje.getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

}
