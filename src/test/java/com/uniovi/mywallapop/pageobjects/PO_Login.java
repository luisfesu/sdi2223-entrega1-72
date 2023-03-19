package com.uniovi.mywallapop.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Login extends  PO_NavView {
    static public void fillLoginForm(WebDriver driver, String emailu, String pass){
        WebElement email = driver.findElement(By.name("username"));
        email.click();
        email.clear();
        email.sendKeys(emailu);
        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys(pass);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }
}
