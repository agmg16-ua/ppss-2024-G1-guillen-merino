package Ejercicio3.conPoyPFact;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class TestShoes {

    WebDriver driver;

    MyAccountPage poAccountPage;

    ShoesPage poShoesPage;

    ProductComparisonPage poComparisonPage;



    @BeforeAll
    public static void prepare() {
        Cookies.storeCookiesToFile("agmg16@gcloud.ua.es", "123456");
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions op = new ChromeOptions();
        op.setBrowserVersion("119");
        driver = new ChromeDriver(op);

        Cookies.loadCookiesFromFile(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        poAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    }

    @AfterEach
    public void toClose() {
        driver.quit();
    }

    @Test
    public void compareShoes() {
        //Comprobar nombre de la pagina
        Assertions.assertTrue(poAccountPage.getPageTitle().contains("My Account"));

        //Acceder a la pagina Shoes
        poShoesPage = poAccountPage.toShoesPage();

        //Verificar nombre de la pagina
        Assertions.assertTrue(poShoesPage.getPageTitle().contains("Shoes - Accessories"));

        //Añadir zapatos a la comparacion
        poShoesPage.selectShoeToCompare(5);
        poShoesPage.selectShoeToCompare(6);

        //Hacer click en el boton
        poComparisonPage = poShoesPage.compare();

        //Comprobar pagina nueva
        Assertions.assertTrue(poComparisonPage.getPageTitle().contains("Products Comparison List - Magento Commerce"));

        //Cerrar la ventana de comparacion
        poShoesPage = poComparisonPage.closeWindow();

        //Verificar que vuelvo a la pagina
        Assertions.assertTrue(poShoesPage.getPageTitle().contains("Shoes - Accessories"));

        //Eliminar objetos de la lista y eliminacion
        Alert alerta = poShoesPage.clearComparisonObjects();

        //Comprobar mensaje de alerta y aceptar alerta
        Assertions.assertTrue(alerta.getText().contains("Are you sure you would like to remove all products from your comparison?"));
        alerta.accept();

        //Comprobar que existe el mensaje de borrado
        Assertions.assertTrue(poShoesPage.getMensaje().contains("The comparison list was cleared."));

    }
}
