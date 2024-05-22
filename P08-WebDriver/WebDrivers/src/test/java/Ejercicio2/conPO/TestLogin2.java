package Ejercicio2.conPO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestLogin2 {
    WebDriver driver;

    HomePage poHomePage;

    CustomerLoginPage poLoginPage;

    MyAccountPage poAccountPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions co = new ChromeOptions();
        co.setBrowserVersion("119");
        driver = new ChromeDriver(co);
        poHomePage = new HomePage(driver);
    }

    @AfterEach
    public void toClose() {
        driver.quit();
    }

    @Test
    public void S4_scenario_PO_loginOK_should_login_with_success_when_user_account_exists() {
        String username = "agmg16@gcloud.ua.es";
        String password = "123456";
        //Comprobar nombre de la pagina
        Assertions.assertTrue(poHomePage.getPageTitle().contains("Madison Island"));

        //Acceder a la pagina de login
        poLoginPage = poHomePage.goToLogin();

        //Comprobar nombre de la pagina
        Assertions.assertTrue(poLoginPage.getPageTitle().contains("Customer Login"));

        //Hacer login correcto
        poAccountPage = poLoginPage.loginOk(username, password);

        //Comprobar nombre de la pagina
        Assertions.assertTrue(poAccountPage.getPageTitle().contains("My Account"));
    }

    @Test
    public void S5_scenario_PO_loginFailed_should_fail_when_user_account_not_exists() {
        String username = "error@email.com";
        String password = "123456";
        String mensajeError = "Invalid login or password.";

        //Comprobar nombre de la pagina
        Assertions.assertTrue(poHomePage.getPageTitle().contains("Madison Island"));

        //Acceder a la pagina de login
        poLoginPage = poHomePage.goToLogin();

        //Comprobar nombre de la pagina
        Assertions.assertTrue(poLoginPage.getPageTitle().contains("Customer Login"));

        //Hacer login erroneo
        String msgErrorRecibido = poLoginPage.loginFail(username, password);

        //Comprobar mensaje de error
        Assertions.assertTrue(mensajeError.contains(msgErrorRecibido));
    }
}
