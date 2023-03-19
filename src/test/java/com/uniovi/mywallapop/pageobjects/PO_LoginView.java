package com.uniovi.mywallapop.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView{
    static public void fillLoginForm(WebDriver driver, String emailp, String passwordp) {

        WebElement email = driver.findElement(By.name("username"));
        email.click();
        email.clear();
        email.sendKeys(emailp);

        WebElement passwd = driver.findElement(By.name("password"));
        passwd.click();
        passwd.clear();

        passwd.sendKeys(passwordp);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }
}
