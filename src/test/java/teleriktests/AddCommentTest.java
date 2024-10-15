package teleriktests;

import com.telerik.pages.CreateTopicPage;
import com.telerik.pages.LoginPage;
import com.telerik.pages.TopicPage;
import com.telerik.pages.LoginHelper;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testframework.core.BaseWebTest;

public class AddCommentTest extends BaseWebTest {
    private static LoginPage loginPage;
    private static CreateTopicPage createTopicPage;
    private static TopicPage topicPage;
    private static LoginHelper loginHelper;

    private static final By commentTextAreaLocator = By.cssSelector(".d-editor-input");
    private static final By replyButtonOpenLocator = By
            .xpath("//span[contains(@class, 'd-button-label') and text()='Reply']");
    private static final By replyButtonSendLocator = By
            .cssSelector("div.save-or-cancel button.btn.btn-icon-text.btn-primary.create");
    private static final String TOPIC_URL = "https://stage-forum.telerikacademy.com/t/alpha-50-qa-we-are-awesome-and-great/11098";

    @BeforeAll
    public static void setUpAll() {
        loginPage = new LoginPage();
        createTopicPage = new CreateTopicPage();
        topicPage = new TopicPage();
        loginHelper = new LoginHelper(loginPage);

        loginHelper.loginWithValidCredentials("armine.stoykova.a61@learn.telerikacademy.com", "Katrin2018Dayana@");
    }

    @Test
    public void addCommentWithEmojiNames() throws InterruptedException {
        driver().navigate().to(TOPIC_URL);
        // driver().manage().deleteAllCookies();
        Thread.sleep(3000);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(replyButtonOpenLocator));
        Thread.sleep(3000);
        // driverWait().until(ExpectedConditions.elementToBeClickable(replyButtonOpenLocator)).click();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(replyButtonOpenLocator)).click();
        // driverWait().until(ExpectedConditions.elementToBeClickable(replyButtonOpenLocator)).click();

        driverWait().until(ExpectedConditions.visibilityOfElementLocated(commentTextAreaLocator));
        WebElement commentTextArea = driverWait()
                .until(ExpectedConditions.elementToBeClickable(commentTextAreaLocator));

        String commentText = "Hi, I am Armine, and I did it 😄 🎉 🍻 🙈 🙉 🙊 ❤️";

        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver().getWebDriver();
        js.executeScript("arguments[0].value = arguments[1];", commentTextArea, commentText);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", commentTextArea);
        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", commentTextArea);

        Thread.sleep(3000);
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(replyButtonSendLocator));
        driverWait().until(ExpectedConditions.elementToBeClickable(replyButtonSendLocator)).click();
        // Assertions.assertTrue(topicPage.isCommentPostedSuccessfully("Hi, I am
        // Armine"), "Comment was not posted successfully.");
        Thread.sleep(10000);
    }
}