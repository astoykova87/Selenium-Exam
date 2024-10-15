package teleriktests;

import com.telerik.pages.LoginPage;
import com.telerik.pages.LoginHelper;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testframework.core.BaseWebTest;
import java.time.Duration;

public class DislikeTopicTest extends BaseWebTest {

    private static final By topicLinkLocator = By.cssSelector("a.raw-link.raw-topic-link[data-topic-id='11547']");
    private static final By likeButtonLocator = By.xpath("//button[@data-post-id='34574']");

    @BeforeAll
    public static void setUp() {
        LoginPage loginPage = new LoginPage();
        LoginHelper loginHelper = new LoginHelper(loginPage);

        loginHelper.loginWithValidCredentials("armine.stoykova.a61@learn.telerikacademy.com", "Katrin2018Dayana@");
    }

    @Test
    public void dislikeTopicTest() throws InterruptedException {
        driver().navigate().to("https://stage-forum.telerikacademy.com/c/alpha-preparation/15");

        WebElement topicLink = waitForElementToBeClickable(topicLinkLocator);
        topicLink.click();

        scrollUpInSteps();

        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(likeButtonLocator));

        WebElement likeButton = driver().findElement(likeButtonLocator);

        String titleValue = likeButton.getAttribute("title");

        if ("you've liked this post".equals(titleValue)) {
            likeButton.click();

            Thread.sleep(2000);

            titleValue = likeButton.getAttribute("title");
            Assertions.assertEquals("like this post", titleValue, "The thread was not disliked successfully.");

            System.out.println("The thread was disliked successfully.");
        } else {
            System.out.println("The thread is not liked, so no need to dislike it.");
        }
    }

    private WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void scrollUpInSteps() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver().getWebDriver();

        long currentScrollPosition = (long) js.executeScript("return window.scrollY;");

        while (currentScrollPosition > 0) {
            js.executeScript("window.scrollBy(0, -500);");
            Thread.sleep(2000);
            currentScrollPosition = (long) js.executeScript("return window.scrollY;");
        }
    }
}