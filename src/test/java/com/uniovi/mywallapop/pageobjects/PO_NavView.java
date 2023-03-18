package com.uniovi.mywallapop.pageobjects;

import com.uniovi.mywallapop.util.SeleniumUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PO_NavView extends PO_View {

    /**
     * Clic en una de las opciones principales (a href) y comprueba que se vaya a la vista con el elemento de
     tipo type con el texto Destino
     * @param driver: apuntando al navegador abierto actualmente.
     * @param textOption: Texto de la opción principal.
     * @param criterio: "id" or "class" or "text" or "@attribute" or "free". Si el valor de criterio es free es una
    expresion xpath completa.
     * @param targetText: texto correspondiente a la búsqueda de la página destino.
     */
    public static void clickOption(WebDriver driver, String textOption, String criterio, String targetText) {
        //CLickamos en la opción de registro y esperamos a que se cargue el enlace de Registro.
        List<WebElement> elements = SeleniumUtils.waitLoadElementsBy(driver, "@href", textOption,
                getTimeout());
        //Tiene que haber un sólo elemento.
        Assertions.assertEquals(1, elements.size());
        //Ahora lo clickamos
        elements.get(0).click();
        //Esperamos a que sea visible un elemento concreto
        elements = SeleniumUtils.waitLoadElementsBy(driver, criterio, targetText, getTimeout());
        //Tiene que haber un sólo elemento.
        Assertions.assertEquals(1, elements.size());
    }

    /**
     * Selecciona el enlace de idioma correspondiente al texto textLanguage
     * @param driver: apuntando al navegador abierto actualmente.
     * @param textLanguage: el texto que aparece en el enlace de idioma ("English" o "Spanish")
     */
    public static void changeLanguage(WebDriver driver, String textLanguage) {
        //clickamos la opción Idioma.
        List<WebElement> languageButton = SeleniumUtils.waitLoadElementsBy(driver, "id", "btnLanguage",
                getTimeout());
        languageButton.get(0).click();
        //Esperamos a que aparezca el menú de opciones.
        SeleniumUtils.waitLoadElementsBy(driver, "id", "languageDropdownMenuButton", getTimeout());
        //CLickamos la opción Inglés partiendo de la opción Español
        List<WebElement> Selectedlanguage = SeleniumUtils.waitLoadElementsBy(driver, "id", textLanguage,
                getTimeout());
        Selectedlanguage.get(0).click();
    }

    static public void fillFormAddOffer(WebDriver driver, String titlep,
            String descriptionp, String pricep) {

        //Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
        SeleniumUtils.waitSeconds(driver, 5);

        //Rellenemos el campo de titulo
        WebElement title = driver.findElement(By.name("title"));
        title.click();
        title.clear();
        title.sendKeys(titlep);

        // Rellenamos el campo de la descripcion
        WebElement description = driver.findElement(By.name("description"));
        description.click();
        description.clear();
        description.sendKeys(descriptionp);

        // Rellenamos el campo del precio
        WebElement price = driver.findElement(By.name("price"));
        price.click();
        price.clear();
        price.sendKeys(pricep);

        // Pulsamos el botón de envio
        By btn = By.className("btn");
        driver.findElement(btn).click();
    }
}

