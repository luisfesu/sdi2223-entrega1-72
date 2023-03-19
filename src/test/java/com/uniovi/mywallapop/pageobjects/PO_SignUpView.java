package com.uniovi.mywallapop.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_SignUpView extends  PO_NavView {
    static public void fillLoginForm(WebDriver driver, String emailu, String nameU
            , String lastNameU  , String pass, String passC){
        WebElement email = driver.findElement(By.name("username"));
        email.click();
        email.clear();
        email.sendKeys(emailu);

        WebElement name = driver.findElement(By.name("name"));
        name.click();
        name.clear();
        name.sendKeys(nameU);

        WebElement lastName = driver.findElement(By.name("lastName"));
        lastName.click();
        lastName.clear();
        lastName.sendKeys(lastNameU);

        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys(pass);

        WebElement passwordConf = driver.findElement(By.name("passwordConfirm"));
        passwordConf.click();
        passwordConf.clear();
        passwordConf.sendKeys(passC);
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }
}
