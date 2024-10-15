package com.telerik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import testframework.core.BaseWebPage;

public class LoginPage extends BaseWebPage {
//    private static final By loginButtonLocator = By.cssSelector(".d-button-label");
    private static final By emailFieldLocator = By.id("Email");
    private static final By passwordFieldLocator = By.id("Password");
    private static final By loginLocator = By.id("next");

    public LoginPage() {
        super("/login");
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void submitLoginForm(String email, String password) {
        WebElement emailField = waitForElementToBeVisible(emailFieldLocator);
        emailField.click();
        emailField.sendKeys(email);

        WebElement passwordField = waitForElementToBeVisible(passwordFieldLocator);
        passwordField.click();
        passwordField.sendKeys(password);

        waitForElementToBeVisible(loginLocator).click();
    }

    @Override
    public String getBasePageUrl() {
        return "https://stage-forum.telerikacademy.com";
    }
}