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
    static String Geckodriver = "C:\\Users\\mario\\Desktop\\Dev\\tools\\geckodriver-v0.30.0-win64.exe";

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
    void PR22(){
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "miguel@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "buyButton3");
        elements.get(0).click();

        List<WebElement> result = PO_View.checkElementBy(driver, "text", "Comprada");
        Assertions.assertEquals("Comprada", result.get(0).getText());

    }

    @Test
    void PR23(){
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "miguel@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "buyButton3");
        elements.get(0).click();

        List<WebElement> result = PO_View.checkElementBy(driver, "text", "Comprada");
        Assertions.assertEquals("Comprada", result.get(0).getText());

    }

    @Test
    void PR24(){
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "miguel@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "buyButton3");
        elements.get(0).click();

        List<WebElement> result = PO_View.checkElementBy(driver, "text", "Comprar");
        Assertions.assertEquals("Comprar", result.get(0).getText());
        result = PO_View.checkElementBy(driver, "text", "No tienes suficiente saldo");
        Assertions.assertEquals("No tienes suficiente saldo", result.get(0).getText());
    }

    @Test
    void PR25(){
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "miguel@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "buyButton5");
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "purchasedOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = driver.findElements(By.xpath("//*[@id='tablePurchased']/tbody/tr"));

        // Comprobar que no hay ningun elemento el el cuerpo de la tabla
        Assertions.assertEquals(1, elements.size());
    }

    @Test
    void PR26(){
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "miguel@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "conversation3"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = driver.findElements(By.xpath("//*[@id='tableMessages']/tbody/tr"));

        // Comprobar que no hay ningun elemento el el cuerpo de la tabla
        Assertions.assertEquals(0, elements.size());

        PO_PrivateView.sendMessage(driver, "Mensaje de prueba");

        elements = driver.findElements(By.xpath("//*[@id='tableMessages']/tbody/tr"));

        // Comprobar que no hay ningun elemento el el cuerpo de la tabla
        Assertions.assertEquals(1, elements.size());
        List<WebElement> result = PO_View.checkElementBy(driver, "text", "Mensaje de prueba");
        Assertions.assertEquals("Mensaje de prueba", result.get(0).getText());
    }

    @Test
    void PR27(){
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "miguel@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "conversation3"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = driver.findElements(By.xpath("//*[@id='tableMessages']/tbody/tr"));

        // Comprobar que no hay ningun elemento el el cuerpo de la tabla
        Assertions.assertEquals(0, elements.size());

        PO_PrivateView.sendMessage(driver, "Mensaje de prueba");

        elements = driver.findElements(By.xpath("//*[@id='tableMessages']/tbody/tr"));

        // Comprobar que no hay ningun elemento el el cuerpo de la tabla
        Assertions.assertEquals(1, elements.size());
        List<WebElement> result = PO_View.checkElementBy(driver, "text", "Mensaje de prueba");
        Assertions.assertEquals("Mensaje de prueba", result.get(0).getText());

        // Pinchamos en el menu de Mis Ofertas
        elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "conversation3"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = driver.findElements(By.xpath("//*[@id='tableMessages']/tbody/tr"));

        // Comprobar que no hay ningun elemento el el cuerpo de la tabla
        Assertions.assertEquals(1, elements.size());
        result = PO_View.checkElementBy(driver, "text", "Mensaje de prueba");
        Assertions.assertEquals("Mensaje de prueba", result.get(0).getText());
    }

    @Test
    void PR28(){
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "miguel@mail.com", "123456");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "conversation3"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        // Pinchamos en el menu de Mis Ofertas
        elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "conversations"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = driver.findElements(By.xpath("//*[@id='tableConversations']/tbody/tr"));

        Assertions.assertEquals(1, elements.size());
    }




}
