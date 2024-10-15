package com.telerik.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import testframework.core.BaseWebPage;

public class TopicPage extends BaseWebPage {
    private static final By confirmationLocator = By.className("d-button-label");

    public TopicPage() {
        super("/topic-confirmation");
    }

    public boolean isTopicCreatedSuccessfully() {
        return driver().findElement(confirmationLocator).isDisplayed();
    }

    // Метод за навигация до конкретна тема по заглавие
    public void navigateToTopic(String topicTitle) {
        // Използвай локатор за намиране на темата по заглавие
        WebElement topicLink = driver().findElement(By.linkText(topicTitle));
        topicLink.click();
    }

    // Метод за валидиране на успешно публикуван коментар
    public boolean isCommentPostedSuccessfully(String expectedComment) {
        // Намери последния коментар и провери дали съдържа очаквания текст
        WebElement lastComment = driver().findElement(By.xpath("//div[@class='post-stream']//div[@class='regular contents']"));
        return lastComment.getText().contains(expectedComment);
    }

    @Override
    public String getBasePageUrl() {
        return "https://stage-forum.telerikacademy.com";
    }
}
