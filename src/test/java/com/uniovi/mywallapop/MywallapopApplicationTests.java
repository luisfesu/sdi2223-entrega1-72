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

        // Pinchamos en la opción de Añadir Oferta
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='offerDropdown']"); // //li/a
        elements.get(0).click();

        // Esperamos a que aparezca la opción de añadir Oferta
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
}
