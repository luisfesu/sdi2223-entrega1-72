package com.uniovi.mywallapop;

import com.uniovi.mywallapop.pageobjects.*;
import com.uniovi.mywallapop.util.SeleniumUtils;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.Utilities;
import java.util.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MywallapopApplicationTests {

    // Ruta de Firefox
    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";

    // Ruta de Gecko Driver
    static String Geckodriver = "C:\\Dev\\tools\\selenium\\geckodriver-v0.30.0-win64.exe";

    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }
    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }
    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {}
    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }

    @Test
    void contextLoads() {
    }

    @Test
    @Order(15)
    void Prueba15 () {
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "pedro@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='offerDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de añadir Oferta
        elements = PO_View.checkElementBy(driver, "id", "addOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        // Rellenamos el formulario
        String titleTest = "Oferta Nueva 1";
        String descriptionTest = "Descricion de la nueva oferta";
        String priceTest = "100.00";
        PO_PrivateView.fillFormAddOffer(driver,titleTest, descriptionTest, priceTest);

        // Esperamos 5 segundos para asegurar que la pagina funciona
        SeleniumUtils.waitSeconds(driver, 5);

        // Comprobamos que el texto de la nueva oferta está presente en la pagina
        SeleniumUtils.textIsPresentOnPage(driver, titleTest);
    }

    @Test
    @Order(16)
    void Prueba16() {
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "pedro@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='offerDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de añadir Oferta
        elements = PO_View.checkElementBy(driver, "id", "addOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        // Rellenamos el formulario
        String titleTest = "Oferta Nueva 1";
        String descriptionTest = "Descricion de la nueva oferta";
        String priceNegative = "-100.00";
        PO_PrivateView.fillFormAddOffer(driver,titleTest, descriptionTest, priceNegative);

        // comprobamos que aparece el mensaje de error en la pagina
        // mensaje de error: error.addOffer.price.value
        List<WebElement> result = PO_View.checkElementByKey(driver, "error.addOffer.price.value", PO_Properties.getSPANISH());
        String checkText = PO_HomeView.getP().getString("error.addOffer.price.value", PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(17)
    void Prueba17() {
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "miguel@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='offerDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "listOffers"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        // Contamos el número de filas de notas
        List<WebElement> offerList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());

        // El Usuario 2 (Miguel Perez) tiene 10 ofertas
        Assertions.assertEquals(10, offerList.size());
    }

    @Test
    @Order(18)
    void Prueba18() {
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "miguel@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='offerDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "listOffers"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        // Texto de la Oferta a comprobar - Primera Oferta
        String checkText = "oferta 4";

        // Seleccionamos el enlace para borrar en la fila de la nota 1
        elements = PO_View.checkElementBy(driver, "free", "//td[contains(text(), '" + checkText + "')]/following-sibling::*/a[contains(@href, 'offer/delete')]");
        elements.get(0).click();

        // Comprobamos que el texto de la oferta ya no está en la lista.
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, checkText, PO_View.getTimeout());
    }

    @Test
    @Order(19)
    void Prueba19() {
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "miguel@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='offerDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "listOffers"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        // Texto de la Oferta a comprobar - Ultima Oferta
        String checkText = "oferta 13";

        // Seleccionamos el enlace para borrar en la fila de la nota 1
        elements = PO_View.checkElementBy(driver, "free", "//td[contains(text(), '" + checkText + "')]/following-sibling::*/a[contains(@href, 'offer/delete')]");
        elements.get(0).click();

        // Comprobamos que el texto de la oferta ya no está en la lista.
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, checkText, PO_View.getTimeout());
    }

    @Test
    @Order(20)
    void Prueba20() {

        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "miguel@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        // Texto de la Oferta a buscar - Cadena Vacía
        String checkText = "";

        // Rellenamos el formulario de búsqueda
        PO_NavView.fillSearchForm(driver, checkText);

        // Comprobamos el primer elemento
        SeleniumUtils.textIsPresentOnPage(driver, "oferta 1"); // primera oferta en la aplicación

        // Vamos a la ultima página, Comprobamos el último elemento
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
        elements.get(3).click();
        SeleniumUtils.textIsPresentOnPage(driver, "oferta 12"); // última oferta en la aplicación
    }

    @Test
    @Order(21)
    void Prueba21() {
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "miguel@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        // Texto de la Oferta a buscar - Texto de Oferta inexistente
        String checkText = "Esta oferta no existe en la aplicacion";

        // Rellenamos el formulario de búsqueda
        PO_NavView.fillSearchForm(driver, checkText);

        // Obtener todos los elementos <td> dentro de un elemento <tr> (En este caso, no habrá ninguno)
        //elements = PO_View.checkElementBy(driver, "free", "//*[@id='tableOffers']/tbody/tr/");
        elements = driver.findElements(By.xpath("//*[@id='tableOffers']/tbody/tr"));

        // Comprobar que no hay ningun elemento el el cuerpo de la tabla
        Assertions.assertEquals(0, elements.size());
    }

}
