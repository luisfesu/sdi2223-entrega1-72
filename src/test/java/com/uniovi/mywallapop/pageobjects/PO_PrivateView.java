package com.uniovi.mywallapop.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_PrivateView extends PO_NavView {
    public static void sendMessage(WebDriver driver, String message){
        WebElement title = driver.findElement(By.name("text"));
        title.click();
        title.clear();
        title.sendKeys(message);

        By btn = By.className("btn");
        driver.findElement(btn).click();
    }
}
