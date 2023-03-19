package com.uniovi.mywallapop;

import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.pageobjects.*;
import com.uniovi.mywallapop.services.UsersService;
import com.uniovi.mywallapop.util.SeleniumUtils;
import com.uniovi.mywallapop.services.UsersService;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.Element;
import javax.swing.text.Utilities;
import java.util.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MywallapopApplicationTests {
    @Autowired
    private UsersService usersService;
    // Ruta de Firefox
    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";

    // Ruta de Gecko Driver
    //static String Geckodriver = "C:\\Users\\pablo\\Desktop\\uni\\2022-2023\\sdi\\SDI-2223-705-lab-spring\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

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
    @Order(1)
    void Prueba1(){
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillSingupForm(driver,"test@mail.com","testaitor","testeable","test123","test123");
        List<WebElement> elements = PO_View.checkElementBy(driver, "id", "home");
        elements.get(0).click();
        elements = PO_View.checkElementBy(driver, "id", "logout");
        elements.get(0).click();
        PO_LoginView.fillLoginForm(driver,"test@mail.com","test123");
        elements = PO_View.checkElementBy(driver, "id", "home");
        elements.get(0).click();
    }
    @Test
    @Order(2)
    void Prueba2(){
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillSingupForm(driver,"","","","test123","test123");
        List<WebElement> elements = PO_View.checkElementBy(driver, "class", "text-danger");
        Assertions.assertEquals(3,elements.size());

    }
    @Test
    @Order(3)
    void Prueba3(){
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillSingupForm(driver,"test2@mail.com","testaitor","testeable","test123","test1223");
        List<WebElement> elements = PO_View.checkElementBy(driver, "class", "text-danger");
        Assertions.assertEquals(1,elements.size());

    }
    @Test
    @Order(4)
    void Prueba4(){
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillSingupForm(driver,"admin@email.com","testaitor","testeable","test123","test123");
        List<WebElement> elements = PO_View.checkElementBy(driver, "class", "text-danger");
        Assertions.assertEquals(1,elements.size());

    }
    @Test
    @Order(5)
    void Prueba5() {
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

        // Comprobamos que aparezca el nombre de usuario
        SeleniumUtils.textIsPresentOnPage(driver, "admin@email.com");
    }

    @Test
    @Order(6)
    void Prueba6() {
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "pedro@mail.com", "123456");

        // Comprobamos que aparezca el nombre de usuario
        SeleniumUtils.textIsPresentOnPage(driver, "pedro@mail.com");
    }

    @Test
    @Order(7)
    void Prueba7() {
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "", ""); // Datos Invalidos, Vacios

        List<WebElement> elements = PO_View.checkElementBy(driver, "id", "username");

        Assertions.assertEquals(elements.isEmpty(), false);
    }

    @Test
    @Order(8)
    void Prueba8() {
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "pedro@mail.com", "contrasenaIncorrecta"); // Datos Validos, contraseña erronea

        List<WebElement> elements = PO_View.checkElementBy(driver, "id", "username");

        Assertions.assertEquals(elements.isEmpty(), false);
    }
    @Test
    @Order(9)
    void Prueba9(){
        Prueba6();
        List<WebElement> elements = PO_NavView.checkElementBy(driver,"id","logout");
        elements.get(0).click();

        SeleniumUtils.waitLoadElementsBy(driver,"id","username",1);
    }
    @Test
    @Order(10)
    void Prueba10(){

       // SeleniumUtils.waitLoadElementsBy(driver,"id","username",1);
        SeleniumUtils.waitSeconds(driver,1);
        List<WebElement> elements = driver.findElements(By.id("logout"));
       // PO_NavView.checkElementBy(driver,"id","logout");
        Assertions.assertEquals(0, elements.size());
    }
    @Test
    @Order(11)
    void Prueba11(){
        Prueba5();
        List<WebElement> elements =PO_NavView.checkElementBy(driver,"id","adminDropdown");
        elements.get(0).click();
        elements =PO_NavView.checkElementBy(driver,"id","adminUserListDropdown");
        elements.get(0).click();
        //elements =PO_NavView.checkElementBy(driver,"xpath","/html/body/nav/div/ul[1]/li[2]/div/a");
      //  elements.get(0).click();
        elements =PO_NavView.checkElementBy(driver,"free","/html/body/div/div/form/table/tbody/*/td[1]");
        //elements =PO_NavView.checkElementBy(driver,"name","email");
        List<User> list = usersService.getUsers();
        for (WebElement e:elements) {
           Assertions.assertTrue(list.stream().anyMatch(user -> e.getText().equals(user.getEmail())));
        }

    }

    List<WebElement> checkBoxes(int[] nums){
        List<WebElement> elements = new ArrayList<WebElement>();
        for (int n:nums) {
            elements.addAll(PO_NavView.checkElementBy(driver,"free"
                    ,"/html/body/div/div/form/table/tbody/tr["+n+"]/td[4]/div/input"));

        }

        return  elements;
    }
    @Test
    @Order(12)
    void Prueba12(){
        Prueba5();
        int sizePreDelete = usersService.getUsers().size();
        List<WebElement> elements =PO_NavView.checkElementBy(driver,"id","adminDropdown");
        elements.get(0).click();
        elements =PO_NavView.checkElementBy(driver,"id","adminUserListDropdown");
        elements.get(0).click();
        int[] temp = {1};
        elements = checkBoxes(temp);
        elements.stream().forEach(webElement -> webElement.click());
        elements =PO_NavView.checkElementBy(driver,"id",  "deleteButton");
        elements.get(0).click();
        int sizePostDelete = usersService.getUsers().size();

        Assertions.assertEquals(sizePreDelete-1,sizePostDelete);
        elements =PO_NavView.checkElementBy(driver,"free","/html/body/div/div/form/table/tbody/*/td[1]");
        Assertions.assertEquals(sizePostDelete,elements.size());

    }
    @Test
    @Order(13)
    void Prueba13(){
        Prueba5();
        int sizePreDelete = usersService.getUsers().size();
        List<WebElement> elements =PO_NavView.checkElementBy(driver,"id","adminDropdown");
        elements.get(0).click();
        elements =PO_NavView.checkElementBy(driver,"id","adminUserListDropdown");
        elements.get(0).click();

        elements =PO_NavView.checkElementBy(driver,"free","/html/body/div/div/form/table/tbody/*/td[1]");

        int[] temp = {elements.size()};

        elements = checkBoxes(temp);
        elements.stream().forEach(webElement -> webElement.click());
        elements =PO_NavView.checkElementBy(driver,"id",  "deleteButton");
        elements.get(0).click();
        int sizePostDelete = usersService.getUsers().size();
        //SeleniumUtils.waitSeconds(driver,5);
        Assertions.assertEquals(sizePreDelete-1,sizePostDelete);
        elements =PO_NavView.checkElementBy(driver,"free","/html/body/div/div/form/table/tbody/*/td[1]");
        Assertions.assertEquals(sizePostDelete,elements.size());

    }

    @Test
    @Order(14)
    void Prueba14(){
        Prueba5();
        int sizePreDelete = usersService.getUsers().size();
        List<WebElement> elements =PO_NavView.checkElementBy(driver,"id","adminDropdown");
        elements.get(0).click();
        elements =PO_NavView.checkElementBy(driver,"id","adminUserListDropdown");
        elements.get(0).click();

        elements =PO_NavView.checkElementBy(driver,"free","/html/body/div/div/form/table/tbody/*/td[1]");

        int[] temp = {2,4,7};
        elements = checkBoxes(temp);
        elements.stream().forEach(webElement -> webElement.click());
        elements =PO_NavView.checkElementBy(driver,"id",  "deleteButton");
        elements.get(0).click();
        int sizePostDelete = usersService.getUsers().size();

        Assertions.assertEquals(sizePreDelete-3,sizePostDelete);
        elements =PO_NavView.checkElementBy(driver,"free","/html/body/div/div/form/table/tbody/*/td[1]");
        Assertions.assertEquals(sizePostDelete,elements.size());

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
    @Order(29)
    void Prueba29() {
        // PAGINA 1: Comprobamos que se puede internacionalizar el Index (Home):
        PO_HomeView.checkChangeLanguage(driver, "btnSpanish", "btnEnglish",
                PO_Properties.getSPANISH(), PO_Properties.getENGLISH());

        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "pedro@mail.com", "123456");


        //PAGINA 2: Añadir Oferta
        // Vamos a la pantalla de añadir oferta
        PO_NavView.goToAddOffer(driver);

        // Comprobamos elementos
        PO_HomeView.changeLanguageToSpanish(driver); // 1. Español

        // 1. Titulo H2
        List<WebElement> elements = SeleniumUtils.waitLoadElementsBy(driver, "text",
                PO_View.getP().getString("offer.add.h2", PO_Properties.getSPANISH()), PO_View.getTimeout());
        Assertions.assertEquals(elements.get(0).getText(), "Crea una nueva oferta");

        // 2. Titulo Label Título
        elements = SeleniumUtils.waitLoadElementsBy(driver, "text",
                PO_View.getP().getString("offer.add.title.label", PO_Properties.getSPANISH()), PO_View.getTimeout());
        Assertions.assertEquals(elements.get(0).getText(), "Titulo de la oferta");

        PO_HomeView.changeLanguageToEnglish(driver); // 2. Inglés

        elements = SeleniumUtils.waitLoadElementsBy(driver, "text",
                PO_View.getP().getString("offer.add.h2", PO_Properties.getENGLISH()), PO_View.getTimeout());
        Assertions.assertEquals(elements.get(0).getText(), "Create new Offer");

        // 2. Titulo Label Título
        elements = SeleniumUtils.waitLoadElementsBy(driver, "text",
                PO_View.getP().getString("offer.add.title.label", PO_Properties.getENGLISH()), PO_View.getTimeout());
        Assertions.assertEquals(elements.get(0).getText(), "Offer Title");

        //PAGINA 3: Listar Oferta
        // Vamos a la pantalla de listar ofertas
        PO_NavView.goToListSelfOffers(driver);
        PO_HomeView.changeLanguageToSpanish(driver); // 1. Español

        // 1. párrafo de presentación
        elements = SeleniumUtils.waitLoadElementsBy(driver, "text",
                PO_View.getP().getString("offer.list.paragraph", PO_Properties.getSPANISH()), PO_View.getTimeout());
        Assertions.assertEquals(elements.get(0).getText(), "Estas son las ofertas que has creado en MyWallapop");

        // 2. Label de tabla Descripción
        elements = SeleniumUtils.waitLoadElementsBy(driver, "text",
                PO_View.getP().getString("offer.list.table.description", PO_Properties.getSPANISH()), PO_View.getTimeout());
        Assertions.assertEquals(elements.get(0).getText(), "Descripcion");

        PO_HomeView.changeLanguageToEnglish(driver); // 2. Inglés

        // 1. párrafo de presentación
        elements = SeleniumUtils.waitLoadElementsBy(driver, "text",
                PO_View.getP().getString("offer.list.paragraph", PO_Properties.getENGLISH()), PO_View.getTimeout());
        Assertions.assertEquals(elements.get(0).getText(), "These are the offers you have created on MyWallapop");

        // 2. Label de tabla Descripción
        elements = SeleniumUtils.waitLoadElementsBy(driver, "text",
                PO_View.getP().getString("offer.list.table.description", PO_Properties.getENGLISH()), PO_View.getTimeout());
        Assertions.assertEquals(elements.get(0).getText(), "Description");


        //PAGINA 4: Buscar Ofertas
        // Vamos a la pantalla de listar ofertas
        PO_NavView.goToListSelfOffers(driver);
        PO_HomeView.changeLanguageToSpanish(driver); // 1. Español

        // 1. párrafo de presentación
        elements = SeleniumUtils.waitLoadElementsBy(driver, "text",
                PO_View.getP().getString("offer.search.table.title", PO_Properties.getSPANISH()), PO_View.getTimeout());
        Assertions.assertEquals(elements.get(0).getText(), "Titulo");

        // 2. Label de tabla Descripción
        elements = SeleniumUtils.waitLoadElementsBy(driver, "text",
                PO_View.getP().getString("offer.search.table.price", PO_Properties.getSPANISH()), PO_View.getTimeout());
        Assertions.assertEquals(elements.get(0).getText(), "Precio");

        PO_HomeView.changeLanguageToEnglish(driver); // 2. Inglés

        // 1. párrafo de presentación
        elements = SeleniumUtils.waitLoadElementsBy(driver, "text",
                PO_View.getP().getString("offer.search.table.title", PO_Properties.getENGLISH()), PO_View.getTimeout());
        Assertions.assertEquals(elements.get(0).getText(), "Title");

        // 2. Label de tabla Descripción
        elements = SeleniumUtils.waitLoadElementsBy(driver, "text",
                PO_View.getP().getString("offer.search.table.price", PO_Properties.getENGLISH()), PO_View.getTimeout());
        Assertions.assertEquals(elements.get(0).getText(), "Price");

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

        elements = PO_View.checkElementBy(driver, "id", "buyButton2");
        elements.get(0).click();

        List<WebElement> result = PO_View.checkElementBy(driver, "free", "//*[@id='tableOffers']/tbody/tr/td/div/a");
        Assertions.assertEquals("Comprada", result.get(0).getText());
        result = PO_View.checkElementBy(driver, "text", "1.0");
        Assertions.assertEquals("1.0", result.get(0).getText());

    }

    @Test
    void PR23(){
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user03@email.com", "user03");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "buyButton3");
        elements.get(0).click();

        List<WebElement> result = PO_View.checkElementBy(driver, "free", "//*[@id='tableOffers']/tbody/tr/td/div/a");
        Assertions.assertEquals("Comprada", result.get(0).getText());
        result = PO_View.checkElementBy(driver, "free", "//*[@id='tableOffers']/p");
        Assertions.assertEquals("0.0", result.get(0).getText());

    }

    @Test
    void PR24(){
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user04@email.com", "user04");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "buyButton4");
        elements.get(0).click();

        List<WebElement> result = PO_View.checkElementBy(driver, "free", "//*[@id='tableOffers']/span");
        Assertions.assertEquals("Error al realizar la compra", result.get(0).getText());
        result = PO_View.checkElementBy(driver, "text", "100.0");
        Assertions.assertEquals("100.0", result.get(0).getText());
    }

    @Test
    void PR25(){
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user02@email.com", "user02");

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
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
        PO_LoginView.fillLoginForm(driver, "user02@email.com", "user02");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "conversation2"); // etiqueta <a/> que redirecciona a la creación de ofertas
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
        PO_LoginView.fillLoginForm(driver, "user02@email.com", "user02");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "searchOffer"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "id", "conversation2"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = driver.findElements(By.xpath("//*[@id='tableMessages']/tbody/tr"));

        // Comprobar que no hay ningun elemento el el cuerpo de la tabla
        Assertions.assertEquals(1, elements.size());
        List<WebElement> result = PO_View.checkElementBy(driver, "text", "Mensaje de prueba");
        Assertions.assertEquals("Mensaje de prueba", result.get(0).getText());
    }

    @Test
    void PR28(){
        // Iniciamos sesión en la aplicación
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user02@email.com", "user02");

        // Pinchamos en el menu de Mis Ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//*[@id='navbarDropdown']"); // //li/a
        elements.get(0).click();

        // Pinchamos en la opción de Ver Mis Ofertas
        elements = PO_View.checkElementBy(driver, "id", "conversations"); // etiqueta <a/> que redirecciona a la creación de ofertas
        elements.get(0).click();

        elements = driver.findElements(By.xpath("//*[@id='tableConversations']/tbody/tr"));

        Assertions.assertEquals(1, elements.size());
    }

}