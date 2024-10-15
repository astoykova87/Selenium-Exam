package com.telerik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import testframework.core.BaseWebPage;

public class CreateTopicPage extends BaseWebPage {
    private static final By newTopicButtonLocator = By.cssSelector(".d-button-label");
    private static final By titleFieldLocator = By.id("reply-title");
    private static final By descriptionFieldLocator = By.cssSelector(".ember-text-area.ember-view");
    private static final By createTopicButtonLocator = By.cssSelector("button.btn.btn-primary.create");
    private static final By learningPlatformLinkLocator = By.cssSelector(".custom-header-links");

    public CreateTopicPage() {
        super("/");
    }

    // Специфичен метод за проверка дали елементът "LEARNING PLATFORM" е видим
    public boolean isLearningPlatformLinkVisible() {
        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(10));
        try {
            WebElement learningPlatformLink = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(learningPlatformLinkLocator));
            return learningPlatformLink.isDisplayed();
        } catch (Exception e) {
            return false; // Ако елементът не е намерен или не е видим
        }
    }

    public void typeSlowly(WebElement element, String text) {
        for (char character : text.toCharArray()) {
            element.sendKeys(String.valueOf(character));
            try {
                Thread.sleep(100); // Wait 100ms between each character
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void createNewTopic(String title, String description) {
        WebElement newTopicButton = waitForElementToBeVisible(newTopicButtonLocator);
        newTopicButton.click();

        WebElement titleField = waitForElementToBeVisible(titleFieldLocator);
        titleField.click();
        // titleField.sendKeys(title);
        typeSlowly(titleField, title);

        WebElement descriptionField = waitForElementToBeVisible(descriptionFieldLocator);
        descriptionField.click();
        // descriptionField.sendKeys(description);
        typeSlowly(descriptionField, description);

        WebElement createTopicButton = waitForElementToBeVisible(createTopicButtonLocator);
        createTopicButton.click();
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @Override
    public String getBasePageUrl() {
        return "https://stage-forum.telerikacademy.com";
    }
}